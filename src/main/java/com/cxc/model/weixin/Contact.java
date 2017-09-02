package com.cxc.model.weixin;

public class Contact {
    //用户名
    private String UserName;

    //备注名
    private String RemarkName;

    //0未知 1男 2女
    private int Sex;

    //成员数
    private int MemberCount;

    //自己 >0
    private long Uin;

    private int ContactFlag;

    //判断是否为公众号
    private int VerifyFlag;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public int getSex() {
        return Sex;
    }

    public void setSex(int sex) {
        Sex = sex;
    }

    public int getMemberCount() {
        return MemberCount;
    }

    public void setMemberCount(int memberCount) {
        MemberCount = memberCount;
    }

    public long getUin() {
        return Uin;
    }

    public void setUin(long uin) {
        Uin = uin;
    }

    public int getContactFlag() {
        return ContactFlag;
    }

    public void setContactFlag(int contactFlag) {
        ContactFlag = contactFlag;
    }

    public String getRemarkName() {
        return RemarkName;
    }

    public void setRemarkName(String remarkName) {
        RemarkName = remarkName;
    }

    public int getVerifyFlag() {
        return VerifyFlag;
    }

    public void setVerifyFlag(int verifyFlag) {
        VerifyFlag = verifyFlag;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Contact{");
        sb.append("UserName='").append(UserName).append('\'');
        sb.append(", RemarkName='").append(RemarkName).append('\'');
        sb.append(", Sex=").append(Sex);
        sb.append(", MemberCount=").append(MemberCount);
        sb.append(", Uin=").append(Uin);
        sb.append(", ContactFlag=").append(ContactFlag);
        sb.append(", VerifyFlag=").append(VerifyFlag);
        sb.append('}');
        return sb.toString();
    }
}
