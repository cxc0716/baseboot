package com.cxc.controller;

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
 * author:chenxinchao
 * date:2016-09-21 16:49
 * desc:com.cxc.controller
 */
@RestController
//@Controller
public class UserController extends BaseController{

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/user/updatePwd")
	public AjaxResult queryUserInfo(String newPassword,String oldPassword,HttpServletRequest request,HttpServletResponse response){
		return initSuccessResult();
	}

}
