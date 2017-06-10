/**
 * @(#)PasswordEncoder.java, 2017/6/10.
 * 
 * Copyright 2017 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cxc.common.util;

import org.acegisecurity.providers.encoding.Md5PasswordEncoder;

/**
 * @author 陈新超(hzchenxinchao@corp.netease.com)
 */
public class PasswordEncoder {

    public static String encodeMd5Password(String password) {
        Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
        String newPwd = md5PasswordEncoder.encodePassword(password,
            (Object) null);
        return newPwd;
    }
}
