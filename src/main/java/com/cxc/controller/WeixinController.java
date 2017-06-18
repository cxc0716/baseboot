/**
 * @(#)WeixinServiceController.java, 2017/6/18.
 * 
 * Copyright 2017 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cxc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.cxc.service.WeixinService;

/**
 * @author 陈新超(hzchenxinchao@corp.netease.com)
 */
@Controller
public class WeixinController extends BaseController {

    @Autowired
    private WeixinService weixinService;


}
