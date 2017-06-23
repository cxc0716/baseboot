package com.cxc.service.impl;

import java.io.File;
import java.util.Date;
import java.util.Locale;

import com.cxc.common.util.HttpClientTemplate;
import com.cxc.domain.Content;
import com.cxc.domain.HiUser;
import com.cxc.mapper.UserMapper;
import com.cxc.model.QrcodeInfo;
import com.cxc.service.UserService;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
