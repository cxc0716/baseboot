package com.cxc.service;

import com.cxc.BaseTest;
import com.cxc.domain.HiUser;
import com.cxc.mapper.UserMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * author:chenxinchao
 * date:2016-09-22 15:51
 * desc:com.cxc.service
 */
public class UserTest extends BaseTest {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserService userService;

	@Test
	public void update(){
		HiUser user = new HiUser();
		user.setId(1);
		user.setName("陈新超");

//		userMapper.updateUserInfo(user);
		userService.updateUserInfo(user);

	}

}
