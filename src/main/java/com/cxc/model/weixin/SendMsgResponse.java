/**
 * @(#)SendMsgResponse.java, 2017/7/15.
 * 
 * Copyright 2017 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cxc.model.weixin;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author 陈新超(hzchenxinchao@corp.netease.com)
 */
public class SendMsgResponse {
    @JsonProperty(value = "BaseResponse")
    private BaseResponse baseResponse;

    public BaseResponse getBaseResponse() {
        return baseResponse;
    }

    public void setBaseResponse(BaseResponse baseResponse) {
        this.baseResponse = baseResponse;
    }
}
