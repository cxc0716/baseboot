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
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import com.cxc.domain.Content;
import com.cxc.service.ContentService;
import com.cxc.vo.AjaxResult;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @author 陈新超(hzchenxinchao@corp.netease.com)
 */
@Controller
public class ContentController extends BaseController {
    @Autowired
    private ContentService contentService;

    @Value("${upload.file.path}")
    private String filePath;

    @Value("${upload.file.url.prefix}")
    private String urlPrefix;

    @RequestMapping("/content/list")
    public String list(HttpServletRequest request, Model model) {
        try {
            List<Content> contents = contentService
                .queryListByUid(getUserId(request));
            for (Content content: contents) {
                String format = DateFormatUtils.format(
                    content.getCreateTime().getTime(), "yyyy-MM-dd HH:mm:ss");
                content.setTimeStr(format);
            }
            model.addAttribute("list", contents);
        } catch (Exception e) {
            logger.error("[content:list]", e);
            model.addAttribute("list", Lists.newArrayList());
        }
        return "main";
    }

    @RequestMapping("/content/upsert")
    @ResponseBody
    public AjaxResult upsert(@Valid Content content, HttpServletRequest request,
        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return initFailureResult(
                bindingResult.getFieldError().getDefaultMessage());
        }
        try {
            content.setCreator(getUserId(request));
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
        @RequestParam(required = true) MultipartFile file,
        HttpServletRequest request) throws MultipartException{
        try {
            String allowExt = ".JPEG/.JPG/.TIFF/.RAW/.BMP/.GIF/.PNG";
            String ext = getExt(file.getOriginalFilename());
            if (allowExt.indexOf(ext.toUpperCase()) == -1) {
                return initFailureResult("图片格式不正确");
            }
            String datetime = DateFormatUtils.format(new Date(),
                "yyyyMMddHHmmss");
            String path = getUserId(request) + datetime + ext;
            File file1 = new File(filePath);
            if (!file1.exists()) {
                file1.mkdirs();
            }
            file.transferTo(new File(filePath + path));
            Map<String, String> map = Maps.newHashMap();
            map.put("picUrl", path);
            map.put("imgUrl", urlPrefix + path);
            return initSuccessResult(map);
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
            logger.error("[content:delete]", e);
            return initFailureResult(e.getMessage());
        }
    }

    @RequestMapping("/content/edit")
    public String toEdit(@RequestParam(required = false) Integer id,
        HttpServletRequest request, Model model) {
        if (id != null) {
            Content content = contentService.getById(id);
            if (content != null) {
                model.addAttribute("data", content);
                model.addAttribute("picUrl", urlPrefix + content.getPicUrl());
            }
        }
        return "edit";
    }

    @RequestMapping("/content/qrcode")
    public String toQrcodePage(Integer id, HttpServletRequest request,
        Model model) {
        model.addAttribute("id", id);
        return "qrcode";
    }

    private String getExt(String fileName) {
        int i = fileName.lastIndexOf(".");
        if(i == -1){
            return "";
        }
        return fileName.substring(i, fileName.length());
    }
}
