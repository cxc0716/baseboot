/**
 * @(#)LoginInterceptor.java, 2017/6/10.
 * 
 * Copyright 2017 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cxc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @author 陈新超(hzchenxinchao@corp.netease.com)
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
        HttpServletResponse response, Object handler) throws Exception {
        // 其他情况判断session中是否有key，有的话继续用户的操作
        if (request.getSession().getAttribute("user") != null) {
            return true;
        }
        // 最后的情况就是进入登录页面
        response.sendRedirect(request.getContextPath() + "/login");
        return false;
    }
}
