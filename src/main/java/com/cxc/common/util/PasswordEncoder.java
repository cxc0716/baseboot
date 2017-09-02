package com.cxc.common.util;

import org.acegisecurity.providers.encoding.Md5PasswordEncoder;

public class PasswordEncoder {

    public static String encodeMd5Password(String password) {
        Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
        String newPwd = md5PasswordEncoder.encodePassword(password,
            (Object) null);
        return newPwd;
    }
}
