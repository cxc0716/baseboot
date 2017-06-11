/**
 * @(#)Response.java, 2017/6/9.
 * 
 * Copyright 2017 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cxc.model.weixin;

/**
 * @author 陈新超(hzchenxinchao@corp.netease.com)
 */
public class BaseResponse {
    private int Ret;
    private String ErrMsg;

    public int getRet() {
        return Ret;
    }

    public void setRet(int ret) {
        Ret = ret;
    }

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }
}
