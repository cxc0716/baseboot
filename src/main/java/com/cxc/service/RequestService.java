/**
 * @(#)RequestService.java, 2018/1/29.
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cxc.service;

import com.cxc.model.SubmitParamBean;

/**
 * @author 陈新超(hzchenxinchao@corp.netease.com)
 */
public interface RequestService {

    public boolean submit(SubmitParamBean param);

}
