/**
 * @(#)BaseController.java, 2017/6/18.
 * 
 * Copyright 2017 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cxc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cxc.vo.AjaxResult;
import com.cxc.vo.ResponseCode;

/**
 * @author 陈新超(hzchenxinchao@corp.netease.com)
 */
public class BaseController {

    private static final Logger logger = LoggerFactory
        .getLogger(BaseController.class);

    /**
     * 初始化操作成功的AjaxResult
     * 
     * @return
     */
    public AjaxResult initSuccessResult() {
        return new AjaxResult(ResponseCode.OK);
    }

    /**
     * 初始化操作成功的AjaxResult
     *
     * @param data
     * @return
     */
    public AjaxResult initSuccessResult(Object data) {
        AjaxResult ajaxResult = new AjaxResult(ResponseCode.OK);
        ajaxResult.setData(data);
        return ajaxResult;
    }

    /**
     * 初始化操作失败的AjaxResult
     *
     * @return
     */
    public AjaxResult initFailureResult() {
        return new AjaxResult(ResponseCode.FAIL);
    }

    /**
     * 初始化操作失败的AjaxResult
     *
     * @param code
     * @return
     */
    public AjaxResult initFailureResult(ResponseCode code) {
        return new AjaxResult(code);
    }

    /**
     * 初始化操作失败的AjaxResult
     *
     * @param code
     * @param errorCode
     * @return
     */
    public AjaxResult initFailureResult(ResponseCode code, String errorCode) {
        AjaxResult ajaxResult = new AjaxResult(code);
        ajaxResult.setErrorCode(errorCode);
        return ajaxResult;
    }

    /**
     * 初始化操作失败的AjaxResult
     *
     * @param code
     * @param errorCode
     * @param data
     * @return
     */
    public AjaxResult initFailureResult(ResponseCode code, String errorCode,
        Object data) {
        AjaxResult ajaxResult = new AjaxResult(code);
        ajaxResult.setErrorCode(errorCode);
        return ajaxResult;
    }

    /**
     * 初始化操作失败的AjaxResult
     *
     * @param errorCode
     *            错误码
     * @return
     */
    public AjaxResult initFailureResult(String errorCode) {
        AjaxResult ajaxResult = new AjaxResult(ResponseCode.FAIL);
        ajaxResult.setErrorCode(errorCode);
        return ajaxResult;
    }

    /**
     * 初始化操作失败的AjaxResult
     *
     * @param errorCode
     *            错误码
     * @param data
     *            数据
     * @return
     */
    public AjaxResult initFailureResult(String errorCode, Object data) {
        AjaxResult ajaxResult = new AjaxResult(ResponseCode.FAIL);
        ajaxResult.setErrorCode(errorCode);
        return ajaxResult;
    }

    /**
     * 初始化包含响应码的AjaxResult
     *
     * @param result
     *            响应码
     * @return
     */
    public AjaxResult initResult(boolean result) {
        return initResult(result, null);
    }

    /**
     * 初始化包含响应码和错误码的AjaxResult
     *
     * @param result
     *            响应码
     * @param errorCode
     *            错误码
     * @return
     */
    public AjaxResult initResult(boolean result, String errorCode) {
        return initResult(result, errorCode, null);
    }

    /**
     * 初始化包含响应码，错误码和数据的AjaxResult
     *
     * @param result
     *            响应码
     * @param errorCode
     *            错误码
     * @param data
     *            数据
     * @return
     */
    public AjaxResult initResult(boolean result, String errorCode,
        Object data) {
        AjaxResult ajaxResult = null;
        if (result)
            ajaxResult = new AjaxResult(ResponseCode.OK);
        else
            ajaxResult = new AjaxResult(ResponseCode.FAIL);

        if (errorCode != null)
            ajaxResult.setErrorCode(errorCode);
        return ajaxResult;
    }

    /**
     * 异常处理<br>
     * ajax请求catch所有异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    public AjaxResult exceptionHandler(Exception e) {
        logger.error("api exception:", e);
        return new AjaxResult(ResponseCode.FAIL, e.getMessage());
    }
}
