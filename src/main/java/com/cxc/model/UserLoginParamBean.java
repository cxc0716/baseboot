/**
 * @(#)UserLoginParamBean.java, 2018/1/30.
 * 
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cxc.model;

/**
 * @author 陈新超(hzchenxinchao@corp.netease.com)
 */
public class UserLoginParamBean {

    private String username;

    private String password;

    private String code;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
