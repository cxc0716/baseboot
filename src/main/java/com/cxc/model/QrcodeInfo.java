package com.cxc.model;

/**
 * author:chenxinchao date:2017-06-11 18:58 desc:com.cxc.model.weixin
 */
public class QrcodeInfo {
    private String qrcode;

    private String uuid;

    private Integer contentId;

    private Integer timeSec;

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {

        this.contentId = contentId;
    }

    public Integer getTimeSec() {
        return timeSec;
    }

    public void setTimeSec(Integer timeSec) {
        this.timeSec = timeSec;
    }
}
