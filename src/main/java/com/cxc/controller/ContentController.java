/**
 * @(#)ContentController.java, 2017/6/18.
 * 
 * Copyright 2017 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cxc.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cxc.domain.Content;
import com.cxc.service.ContentService;
import com.cxc.service.WeixinService;
import com.cxc.vo.AjaxResult;
import com.cxc.vo.UserSimpleInfo;
import com.google.common.collect.Lists;

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
    public String list(HttpServletRequest request,Model model) {
        try {
            List<Content> contents = contentService
                .queryListByUid(getUserId(request));
            model.addAttribute("list", contents);
        } catch (Exception e) {
            logger.error("[content:list]",e);
            model.addAttribute("list", Lists.newArrayList());
        }
        return "main";
    }

    @RequestMapping("/content/upsert")
    @ResponseBody
    public AjaxResult upsert(@Valid Content content,
        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return initFailureResult(
                bindingResult.getFieldError().getDefaultMessage());
        }
        try {
            if (content.getId() == null) {
                contentService.save(content);
                return initSuccessResult("保存成功");
            } else {
                contentService.update(content);
                return initSuccessResult("更新成功");
            }
        } catch (Exception e) {
            return initFailureResult(e.getMessage());
        }
    }

    @RequestMapping("/content/upload")
    @ResponseBody
    public AjaxResult uploadPic(
        @RequestParam(value = "file", required = false) MultipartFile file,
        HttpServletRequest request) {
        try {
            String datetime = DateFormatUtils.format(new Date(),
                "yyyyMMddHHmmss");
            String path = getUserId(request) + datetime
                + getExt(file.getOriginalFilename());
            file.transferTo(new File(filePath + "/" + path));
            return initSuccessResult(path);
        } catch (IOException e) {
            return initFailureResult(e.getMessage());
        }
    }

    @RequestMapping("/content/delete")
    @ResponseBody
    public AjaxResult delete(Integer id) {
        if (id == null) {
            return initFailureResult("id cannot be null");
        }
        try {
            contentService.deleteById(id);
            return initSuccessResult("删除成功");
        } catch (Exception e) {
            logger.error("[content:delete]",e);
            return initFailureResult(e.getMessage());
        }
    }

    @RequestMapping("/content/edit")
    public String toEdit(Integer id, HttpServletRequest request,Model model) {
        if (id != null) {
            Content content = contentService.getById(id);
            if (content != null) {
                model.addAttribute("data", content);
            }
        }
        return "edit";
    }

    @RequestMapping("/content/qrcode")
    public String toQrcodePage(Integer id, HttpServletRequest request,Model model) {
        model.addAttribute("id", id);
        return "qrcode";
    }

    private String getExt(String fileName) {
        int i = fileName.lastIndexOf(".");
        return fileName.substring(i + 1, fileName.length());
    }
}
