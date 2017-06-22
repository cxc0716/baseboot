package com.cxc.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cxc.common.util.PasswordEncoder;
import com.cxc.domain.HiUser;
import com.cxc.service.UserService;
import com.cxc.vo.AjaxResult;
import com.cxc.vo.UserSimpleInfo;

/**
 * author:chenxinchao date:2016-09-22 13:06 desc:com.cxc.controller
 */
@Controller
public class IndexController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index(HttpServletRequest request,
        HttpServletResponse response) {
        return "main";
    }

    @RequestMapping("/")
    public String defaultPage(HttpServletRequest request,
        HttpServletResponse response) {
        return "forward:/login";
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public AjaxResult doLogin(HiUser user, HttpServletRequest request,
        HttpServletResponse response, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return initFailureResult(
                bindingResult.getFieldError().getDefaultMessage());
        }
        user.setPassword(PasswordEncoder.encodeMd5Password(user.getPassword()));
        HiUser hiUser = userService.queryByUsernameAndPassword(user);
        if (hiUser == null) {
            return initFailureResult("用户名或密码不正确");
        } else {
            Integer deleted = hiUser.getDeleted();
            if (deleted != null && deleted.intValue() == 1) {
                return initFailureResult("用户已被删除");
            }
            Integer accountLocked = hiUser.getAccountLocked();
            if (accountLocked != null && accountLocked.intValue() == 1) {
                return initFailureResult("帐号被锁定，请联系管理员");
            }
            Integer accountEnabled = hiUser.getAccountEnabled();
            if (accountEnabled != null && accountEnabled.intValue() == 0) {
                return initFailureResult("帐号不可用，请联系管理员");
            }
            Date expiredDate = hiUser.getExpiredDate();
            Date date = new Date();
            if (expiredDate != null && date.compareTo(expiredDate) > 0) {
                return initFailureResult("帐号已过期，请联系管理员");
            }
            UserSimpleInfo userSimpleInfo = new UserSimpleInfo();
            userSimpleInfo.setId(hiUser.getId());
            userSimpleInfo.setUserName(hiUser.getUserName());
            userSimpleInfo.setNote(hiUser.getDescription());
            HttpSession session = request.getSession();
            session.setAttribute("user", userSimpleInfo);
            return initSuccessResult();
        }
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request,
        HttpServletResponse response) {
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request,
        HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        request.getSession().invalidate();
        return "login";
    }
}
