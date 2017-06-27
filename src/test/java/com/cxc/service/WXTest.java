/**
 * @(#)WXTest.java, 2017/6/27.
 * 
 * Copyright 2017 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cxc.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cxc.BaseTest;
import com.cxc.domain.Content;
import com.cxc.model.QrcodeInfo;

/**
 * @author 陈新超(hzchenxinchao@corp.netease.com)
 */
public class WXTest extends BaseTest {
    @Autowired
    private WeixinService weixinService;

    @Test
    public void test(){
        QrcodeInfo qrcodeInfo = weixinService.getQrcodeInfo();
        System.out.println(qrcodeInfo.getQrcode());
        Content content = new Content();
        content.setText("hello");
        content.setPicUrl("test.png");
        try {
            weixinService.sendMsg(qrcodeInfo.getUuid(),content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
