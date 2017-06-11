/**
 * @(#)ContactResponse.java, 2017/6/9.
 * 
 * Copyright 2017 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cxc.model.weixin;

import java.util.List;

/**
 * @author 陈新超(hzchenxinchao@corp.netease.com)
 */
public class ContactResponse {

    private BaseResponse BaseResponse;

    private long MemberCount;

    private List<Contact> MemberList;

    public BaseResponse getBaseResponse() {
        return BaseResponse;
    }

    public void setBaseResponse(BaseResponse baseResponse) {
        BaseResponse = baseResponse;
    }

    public long getMemberCount() {
        return MemberCount;
    }

    public void setMemberCount(long memberCount) {
        MemberCount = memberCount;
    }

    public List<Contact> getMemberList() {
        return MemberList;
    }

    public void setMemberList(List<Contact> memberList) {
        MemberList = memberList;
    }
}
