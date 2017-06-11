package com.cxc.service;

import com.cxc.domain.Content;
import com.cxc.model.QrcodeInfo;

import java.io.IOException;

/**
 * author:chenxinchao
 * date:2017-06-10 21:06
 * desc:com.cxc.service
 */
public interface WeixinService {

	public QrcodeInfo getQrcodeInfo();

	public Boolean sendMsg(String uuid,Content content) throws IOException;
}
