package com.cxc.model.weixin;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SendMsgResponse {
    @JsonProperty(value = "BaseResponse")
    private BaseResponse baseResponse;
    @JsonProperty(value = "MsgID")
    private String msgID;
    @JsonProperty(value = "LocalID")
    private String localID;

    public BaseResponse getBaseResponse() {
        return baseResponse;
    }

    public void setBaseResponse(BaseResponse baseResponse) {
        this.baseResponse = baseResponse;
    }

    public String getMsgID() {
        return msgID;
    }

    public void setMsgID(String msgID) {
        this.msgID = msgID;
    }

    public String getLocalID() {
        return localID;
    }

    public void setLocalID(String localID) {
        this.localID = localID;
    }
}
