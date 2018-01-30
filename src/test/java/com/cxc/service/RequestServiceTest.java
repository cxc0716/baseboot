package com.cxc.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cxc.BaseTest;
import com.cxc.model.UserLoginParamBean;

public class RequestServiceTest extends BaseTest {

    @Autowired
    private RequestService requestCb868Service;

    @Test
    public void loginTest(){
        UserLoginParamBean userLoginParamBean = new UserLoginParamBean();
        userLoginParamBean.setUsername("");
        userLoginParamBean.setPassword("");
        userLoginParamBean.setCode("");
        requestCb868Service.login();

    }


}
