package com.cxc.controller;

import com.cxc.common.util.PasswordEncoder;
import com.cxc.domain.HiUser;
import com.cxc.mapper.UserMapper;
import com.cxc.service.UserService;
import com.cxc.vo.AjaxResult;
import com.fasterxml.jackson.databind.deser.Deserializers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * author:chenxinchao date:2016-09-21 16:49 desc:com.cxc.controller
 */
@RestController
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/updatePwd")
    public AjaxResult updateUserInfo(String newPassword, String oldPassword,
        HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer userId = getUserId(request);
            HiUser hiUser = userService.queryUserById(userId);
            if(hiUser  == null){
                return initFailureResult("用户不存在");
            }
            String password = hiUser.getPassword();
            oldPassword = PasswordEncoder.encodeMd5Password(oldPassword);
            if(!oldPassword.equals(password)){
                return initFailureResult("旧密码不正确");
            }
            hiUser.setPassword(PasswordEncoder.encodeMd5Password(newPassword));
            userService.updateUserInfo(hiUser);
            return initSuccessResult();
        } catch (Exception e) {
            logger.error("[updatePwd]:",e);
            return initFailureResult("修改密码失败");
        }
    }

}
