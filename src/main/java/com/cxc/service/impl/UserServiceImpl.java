package com.cxc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cxc.domain.HiUser;
import com.cxc.mapper.UserMapper;
import com.cxc.service.UserService;

/**
 * author:chenxinchao date:2016-09-22 15:55 desc:com.cxc.service.impl
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public HiUser queryUserById(int id) {
        return userMapper.queryUserById(id);
    }

    @Override
    public boolean updateUserInfo(HiUser user) {
        int ret = userMapper.updatePassword(user);
        return (ret > 0) ? true : false;

    }

    @Override
    public HiUser queryByUsernameAndPassword(HiUser user) {
        return userMapper.queryByUsernameAndPassword(user);
    }
}
