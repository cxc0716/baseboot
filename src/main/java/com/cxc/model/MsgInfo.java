package com.cxc.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * author:chenxinchao
 * date:2017-06-11 19:39
 * desc:com.cxc.model
 */
public class MsgInfo {

	public static final int TYPE_TEXT = 1;
	public static final int TYPE_TEXT_FILE = 2;
	public static final int TYPE_FILE = 3;


	@JsonProperty(value = "FromUserName")
	private String FromUserName;
	@JsonProperty(value = "ToUserName")
	private String ToUserName;
	@JsonProperty(value = "Content")
	private String Content;
	@JsonProperty(value = "Type")
	private Integer Type = 1;
	@JsonProperty(value = "LocalID")
	private String LocalID = System.currentTimeMillis() + "";
	@JsonProperty(value = "ClientMsgId")
	private String ClientMsgId = LocalID;
	@JsonProperty(value = "MediaId")
	private String MediaId;

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public Integer getType() {
		return Type;
	}

	public void setType(Integer type) {
		Type = type;
	}

	public String getLocalID() {
		return LocalID;
	}

	public void setLocalID(String localID) {
		LocalID = localID;
	}

	public String getClientMsgId() {
		return ClientMsgId;
	}

	public void setClientMsgId(String clientMsgId) {
		ClientMsgId = clientMsgId;
	}
}
