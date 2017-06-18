/**
 * @(#)ContentService.java, 2017/6/18.
 * Copyright 2017 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cxc.service;

import java.util.List;

import com.cxc.domain.Content;

/**
 * @author 陈新超(hzchenxinchao@corp.netease.com)
 */
public interface ContentService {
    boolean save(Content content);

    List<Content> queryListByUid(Integer uid);

    boolean deleteById(Integer id);

    boolean update(Content content);
}
