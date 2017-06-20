/**
 * @(#)ContentMapper.java, 2017/6/18.
 * Copyright 2017 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cxc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cxc.domain.Content;

/**
 * @author 陈新超(hzchenxinchao@corp.netease.com)
 */
@Mapper
public interface ContentMapper {
    void save(Content content);

    List<Content> queryListByUid(Integer uid);

    int deleteById(Integer id);

    int update(Content content);

    Content getById(Integer id);
}
