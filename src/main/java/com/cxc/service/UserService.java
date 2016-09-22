package com.cxc.service;

import com.cxc.domain.User;

/**
 * author:chenxinchao
 * date:2016-09-22 15:54
 * desc:com.cxc.service
 */
public interface UserService {

	public User queryUserById(int id);

	public void updateUserInfo(User user);

}
