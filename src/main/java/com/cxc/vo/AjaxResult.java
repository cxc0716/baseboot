/**
 * @(#)AjaxResult.java, 2015年8月20日.
 *
 * Copyright 2015 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cxc.vo;

/**
 * ajax请求返回结果封装
 */
public class AjaxResult extends JsonResult {
    /**
     * 
     */
    private static final long serialVersionUID = 5451316194035153776L;

    public AjaxResult() {
        super();
    }

    public AjaxResult(ResponseCode responseCode) {
        super(responseCode);
    }

    public AjaxResult(ResponseCode responseCode, String errorCode) {
        super(responseCode, errorCode);
    }

}
