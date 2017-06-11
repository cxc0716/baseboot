/**
 * @(#)WebAppConfigration.java, 2017/6/10.
 * 
 * Copyright 2017 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cxc.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.cxc.interceptor.LoginInterceptor;

/**
 * @author 陈新超(hzchenxinchao@corp.netease.com)
 */

@Configuration
public class WebAppConfiguration extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
            .excludePathPatterns("/login", "/logout","/static/**");
        super.addInterceptors(registry);
    }
}
