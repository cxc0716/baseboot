package com.cxc.service;

import com.cxc.BaseTest;
import com.cxc.common.util.JacksonUtil;
import com.cxc.common.util.PasswordEncoder;
import com.cxc.domain.HiUser;
import com.cxc.mapper.UserMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * author:chenxinchao date:2016-09-22 15:51 desc:com.cxc.service
 */
public class UserTest extends BaseTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Test
    public void update() {
        HiUser hiUser1 = new HiUser();
        hiUser1.setUserName("sa");
        hiUser1.setPassword(PasswordEncoder.encodeMd5Password("sa"));
        HiUser hiUser = userService.queryByUsernameAndPassword(hiUser1);
        System.out.println(JacksonUtil.write(hiUser));
    }

}
