package com.cxc.service.impl;

import com.cxc.common.util.HttpClientTemplate;
import com.cxc.domain.Content;
import com.cxc.domain.HiUser;
import com.cxc.mapper.UserMapper;
import com.cxc.model.QrcodeInfo;
import com.cxc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * author:chenxinchao
 * date:2016-09-22 15:55
 * desc:com.cxc.service.impl
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override public HiUser queryUserById(int id) {
		return null;
	}

	@Transactional
	@Override public void updateUserInfo(HiUser user) {
		userMapper.updateUserInfo(user);

		HiUser user1 = userMapper.queryUserById(user.getId());
//		user1 = null;
		System.out.println(user1.getUserName());
	}

	public static void main(String[] args) {
        HttpClientTemplate httpClientTemplate = new HttpClientTemplate();
        httpClientTemplate.init();

        WeixinServiceImpl weixinService = new WeixinServiceImpl();
        weixinService.setHttpClientTemplate(httpClientTemplate);
        QrcodeInfo qrcodeInfo = weixinService.getQrcodeInfo();
        System.out.println("qrcode--->"+qrcodeInfo.getQrcode());
        Content content = new Content();
        content.setText("hello~~");
        try {
            Boolean aBoolean = weixinService
                .sendMsg(qrcodeInfo.getUuid(), content);
            System.out.println("result-->"+aBoolean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
