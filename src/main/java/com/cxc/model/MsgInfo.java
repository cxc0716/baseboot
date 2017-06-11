package com.cxc.model;

/**
 * author:chenxinchao
 * date:2017-06-11 19:39
 * desc:com.cxc.model
 */
public class MsgInfo {
	private String FromUserName;
	private String ToUserName;
	private String Content;
	private Integer Type;
	private String LocalID;
	private String ClientMsgId;

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
