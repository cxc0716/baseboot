package com.cxc.controller;

import com.cxc.domain.HiUser;
import com.cxc.mapper.UserMapper;
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
public class UserController {

	@Autowired
	private UserMapper userMapper;

	@RequestMapping(value = "/user")
	public HiUser queryUserInfo(Integer userid,HttpServletRequest request,HttpServletResponse response){
		HiUser user = userMapper.queryUserById(userid);
		return user;
	}

}
