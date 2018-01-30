/**
 * @(#)RequestService.java, 2018/1/29.
 * Copyright 2018 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cxc.service;

import java.math.BigDecimal;

import com.cxc.model.SubmitParamBean;
import com.cxc.model.UserLoginParamBean;

/**
 * @author 陈新超(hzchenxinchao@corp.netease.com)
 */
public interface RequestService {

    public boolean submit(SubmitParamBean param);

    public boolean login();

    public BigDecimal getResultRefund();

}
