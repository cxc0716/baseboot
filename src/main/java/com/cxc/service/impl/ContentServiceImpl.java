/**
 * @(#)ContentServiceImpl.java, 2017/6/18.
 * 
 * Copyright 2017 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cxc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cxc.domain.Content;
import com.cxc.mapper.ContentMapper;
import com.cxc.service.ContentService;

/**
 * @author 陈新超(hzchenxinchao@corp.netease.com)
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public boolean save(Content content) {
        contentMapper.save(content);
        return true;
    }

    @Override
    public List<Content> queryListByUid(Integer uid) {
        return contentMapper.queryListByUid(uid);
    }

    @Override
    public boolean deleteById(Integer id) {
        contentMapper.deleteById(id);
        return true;
    }

    @Override
    public boolean update(Content content) {
        contentMapper.update(content);
        return true;
    }
}
