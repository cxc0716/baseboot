package com.cxc.service;

import com.cxc.domain.HiUser;

/**
 * author:chenxinchao
 * date:2016-09-22 15:54
 * desc:com.cxc.service
 */
public interface UserService {

	public HiUser queryUserById(int id);

	public void updateUserInfo(HiUser user);

}
