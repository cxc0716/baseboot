package com.cxc.model.weixin;

import java.util.List;

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
