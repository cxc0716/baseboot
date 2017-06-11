package com.cxc.common.exception;

/**
 * author:chenxinchao
 * date:2017-06-11 19:05
 * desc:com.cxc.common.exception
 */
public class WeixinServiceException extends RuntimeException {

	public WeixinServiceException() {
	}

	public WeixinServiceException(String message) {
		super(message);
	}

	public WeixinServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public WeixinServiceException(Throwable cause) {
		super(cause);
	}
}
