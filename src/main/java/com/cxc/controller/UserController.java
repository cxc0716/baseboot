package com.cxc.controller;

import com.cxc.domain.User;
import com.cxc.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
	public User queryUserInfo(Integer userid,HttpServletRequest request,HttpServletResponse response){
		User user = userMapper.queryUserById(userid);
		return user;
	}

}
