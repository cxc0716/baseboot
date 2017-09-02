package com.cxc.vo;

/**
 * 返回的code
 */
public enum ResponseCode {
    OK("200"), // 移动端成功响应
    FAIL("400"), // 移动端请求失败
    EXPIRE("600"); // 移动端需要登录

    private String code;

    private ResponseCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code;
    }
}
