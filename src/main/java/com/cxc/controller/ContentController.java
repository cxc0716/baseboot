/**
 * @(#)ContentController.java, 2017/6/18.
 * 
 * Copyright 2017 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cxc.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cxc.domain.Content;
import com.cxc.service.ContentService;
import com.cxc.service.WeixinService;
import com.cxc.vo.AjaxResult;
import com.cxc.vo.UserSimpleInfo;

/**
 * @author 陈新超(hzchenxinchao@corp.netease.com)
 */
@Controller
public class ContentController extends BaseController {
    @Autowired
    private ContentService contentService;
    @Value("${upload.file.path}")
    private String filePath;

    @RequestMapping("/content/list")
    public AjaxResult list(HttpServletRequest request) {
        List<Content> contents = contentService.queryListByUid(getUserId(request));
        return initSuccessResult();
    }

    @RequestMapping("/content/upsert")
    public AjaxResult upsert(@Valid Content content,
        BindingResult bindingResult) {
        return initSuccessResult();
    }

    @RequestMapping("/content/upload")
    public AjaxResult uploadPic(@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request){
        String path = "";
        String format = DateFormatUtils.format(new Date(), "");
        path = getUserId(request)+format;
        return initSuccessResult(path);
    }

    private String getExt(String fileName){
        int i = fileName.lastIndexOf(".");
        return fileName.substring(i+1,fileName.length());
    }


    @RequestMapping("/content/delete")
    public AjaxResult delete(Integer id){
        return initSuccessResult();
    }

}
