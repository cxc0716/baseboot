package com.cxc.service.impl;

import com.cxc.domain.User;
import com.cxc.mapper.UserMapper;
import com.cxc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * author:chenxinchao
 * date:2016-09-22 15:55
 * desc:com.cxc.service.impl
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override public User queryUserById(int id) {
		return null;
	}

	@Transactional
	@Override public void updateUserInfo(User user) {
		userMapper.updateUserInfo(user);

		User user1 = userMapper.queryUserById(user.getId());
//		user1 = null;
		System.out.println(user1.getName());
	}
}
