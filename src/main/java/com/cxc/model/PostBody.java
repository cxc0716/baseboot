package com.cxc.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * author:chenxinchao
 * date:2017-06-11 19:45
 * desc:com.cxc.model
 */
public class PostBody {
	@JsonProperty(value = "BaseRequest")
	private TokenInfo BaseRequest;
	@JsonProperty(value = "Msg")
	private MsgInfo Msg;
	@JsonProperty(value = "Scene")
	private Integer Scene;

	public TokenInfo getBaseRequest() {
		return BaseRequest;
	}

	public void setBaseRequest(TokenInfo baseRequest) {
		BaseRequest = baseRequest;
	}

	public MsgInfo getMsg() {
		return Msg;
	}

	public void setMsg(MsgInfo msg) {
		Msg = msg;
	}

	public Integer getScene() {
		return Scene;
	}

	public void setScene(Integer scene) {
		Scene = scene;
	}
}
