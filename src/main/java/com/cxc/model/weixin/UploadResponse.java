/**
 * @(#)UploadResponse.java, 2017/6/18.
 * 
 * Copyright 2017 Netease, Inc. All rights reserved.
 * NETEASE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.cxc.model.weixin;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author 陈新超(hzchenxinchao@corp.netease.com)
 */
public class UploadResponse {
    /*{
        "BaseResponse": {
        "Ret": 0,
            "ErrMsg": ""
    }
,
        "MediaId": "@crypt_41f427c7_8e6bece625bab804e1004635b89466fbb5b25198dbf98f8e575a87b2409bb2296d34ab87c29c16c5645efe9a839d502f8c8bbac9d732bc2c8b1bc4f3fab0149db01ccd3655a64e09fc66b791e8e5ecdef97fb4b445cd2a1b5c02ad00d796f8794fdd94730ea6d9f9a5098706fb6334ccb59c4162b9cb1bc6cd0e2d2f7a59defcf40e01ea83f4cef51d746f67003e0a7557a2b37f8ff491d24fc74bf13c985810906f086c61c312e5ab4018c3e477c99e1eb2a0195801e6e1c1c1e391634d10a4e1ebabbe9434addb434e63eecbd9541aaf102895aa85ec1b76f7b4248caa89f8b2fdf1eb65053849df11a22b4b185a63cdde042a05a00940f3f80c471bdafaf86f4240612fd68eb0d545cf2213d54406ce8b98744bdafb4fc58b9a0aba7b1e8b3ef6c3403b170acfb219d66a4c4b2966",
        "StartPos": 63931,
        "CDNThumbImgHeight": 72,
        "CDNThumbImgWidth": 100
    }*/
    @JsonProperty(value = "BaseResponse")
    private BaseResponse baseResponse;
    @JsonProperty(value = "MediaId")
    private String mediaId;

    public BaseResponse getBaseResponse() {
        return baseResponse;
    }

    public void setBaseResponse(BaseResponse baseResponse) {
        this.baseResponse = baseResponse;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
