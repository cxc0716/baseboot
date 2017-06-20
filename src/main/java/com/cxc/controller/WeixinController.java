/**
 * @(#)WeixinServiceController.java, 2017/6/18.
 * 
 * Copyright 2017 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cxc.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cxc.common.constant.CommonConstant;
import com.cxc.domain.Content;
import com.cxc.model.QrcodeInfo;
import com.cxc.service.ContentService;
import com.cxc.service.WeixinService;
import com.cxc.vo.AjaxResult;

/**
 * @author 陈新超(hzchenxinchao@corp.netease.com)
 */
@RestController
@RequestMapping("/wx")
public class WeixinController extends BaseController {

    @Autowired
    private WeixinService weixinService;
    @Autowired
    private ContentService contentService;

    @RequestMapping("/qrcode")
    public AjaxResult getQrcode(Integer id, HttpServletRequest request) {
        try {
            QrcodeInfo qrcodeInfo = weixinService.getQrcodeInfo();
            qrcodeInfo.setContentId(id);
            qrcodeInfo.setTimeSec(CommonConstant.VALID_TIME_SEC);
            qrcodeInfo.setCurrentTime(System.currentTimeMillis());
            return initSuccessResult(qrcodeInfo);
        } catch (Exception e) {
            logger.error("[qrcode]", e);
            return initFailureResult(e.getMessage());
        }
    }

    @RequestMapping("request")
    public AjaxResult waitAndSendMsg(String uuid,Integer id,Long time,HttpServletRequest request){
        try {
            if(id == null || StringUtils.isBlank(uuid) || time == null){
                return initFailureResult("param is null");
            }
            logger.debug("[op:waitAndSendMsg] uuid:{},contentId:{}",uuid,id);
            Content content = contentService.getById(id);
            if(content == null){
                return initFailureResult("id not exsist");
            }
            Boolean ret = weixinService.sendMsg(uuid, content);
            return initSuccessResult("发送成功");
        } catch (Exception e) {
            logger.error("[op:waitAndSendMsg]",e);
            return initFailureResult(e.getMessage());
        }
    }
}
