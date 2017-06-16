package com.cxc.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cxc.common.util.HttpClientTemplate;
import com.cxc.common.util.JacksonUtil;
import com.cxc.domain.Content;
import com.cxc.model.MsgInfo;
import com.cxc.model.PostBody;
import com.cxc.model.QrcodeInfo;
import com.cxc.model.TokenInfo;
import com.cxc.model.weixin.Contact;
import com.cxc.model.weixin.ContactResponse;
import com.cxc.service.WeixinService;
import com.google.common.collect.Lists;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author:chenxinchao date:2017-06-10 21:07 desc:com.cxc.service.impl
 */
public class WeixinServiceImpl implements WeixinService {

    private static final Logger logger = LoggerFactory
        .getLogger(WeixinServiceImpl.class);

    @Autowired
    private HttpClientTemplate httpClientTemplate;

    @Override
    public QrcodeInfo getQrcodeInfo() {
        String uuidUrl = "https://login.wx.qq.com/jslogin?appid=wx782c26e4c19acffb&"
            + "redirect_uri=https%3A%2F%2Fwx.qq.com%2Fcgi-bin%2Fmmwebwx-bin%2Fwebwxnewloginpage&"
            + "fun=new&lang=zh_CN&_=" + System.currentTimeMillis();
        try {
            String uuidResult = httpClientTemplate.executeGet(uuidUrl);
            String uuid = uuidResult.substring(uuidResult.indexOf("\"") + 1,
                uuidResult.lastIndexOf("\""));
            String qrcodeUrl = "https://login.weixin.qq.com/qrcode/" + uuid;
            QrcodeInfo qrcodeInfo = new QrcodeInfo();
            qrcodeInfo.setUuid(uuid);
            qrcodeInfo.setQrcode(qrcodeUrl);
            if (logger.isDebugEnabled()) {
                logger.debug("qrcode request:{}",
                    JacksonUtil.write(qrcodeInfo));
            }
            return qrcodeInfo;
        } catch (IOException e) {
            logger.error("qrcode request error:", e);
            return null;
        }
    }

    @Override
    public Boolean sendMsg(String uuid, Content content) throws Exception {
        String ticket = waitAndGetTicket(uuid);
        if (StringUtils.isBlank(ticket)) {
            throw new Exception("扫描超时");
        }
        TokenInfo loginInitInfo = getLoginInitInfo(uuid, ticket);

        String userName = getInitInfo(loginInitInfo);
        List<Contact> contactList = getContactList(loginInitInfo);
        List<Contact> contacts = filterContact(contactList, content);
        if (!CollectionUtils.isEmpty(contacts)) {
            for (Contact contact : contacts) {
                PostBody postBody = new PostBody();
                postBody.setBaseRequest(loginInitInfo);
                MsgInfo msgInfo = new MsgInfo();
                msgInfo.setFromUserName(userName);
                msgInfo.setToUserName(contact.getUserName());
                msgInfo.setContent(content.getText());
                postBody.setMsg(msgInfo);
                postBody.setScene(0);
                sendSingleMsg(loginInitInfo.getPassTicket(),postBody);
            }
        }
        return true;
    }

    private String waitAndGetTicket(String uuid) {
        String ticket = "";
        try {
            int cnt = 0;
            long time = System.currentTimeMillis();
            while (cnt++ < 5) {
                String tLogin = "https://login.wx.qq.com/cgi-bin/mmwebwx-bin/login?loginicon=true&tip=%s&r=2109427913&_=%s&uuid=%s";
                String login = String.format(tLogin, 1, time, uuid);
                String s1 = httpClientTemplate.executeGet(login);
                if (s1.contains("window.code=408")) {
                    continue;
                }
                login = String.format(tLogin, 0, time + 1, uuid);
                String s2 = httpClientTemplate.executeGet(login);
                if (s1.contains("window.code=408")) {
                    continue;
                }
                String redirectUrl = s2.substring(s2.indexOf("\"") + 1,
                    s2.lastIndexOf("\""));
                ticket = redirectUrl.substring(
                    redirectUrl.indexOf("ticket=") + 7,
                    redirectUrl.indexOf("&uuid"));
                if (StringUtils.isNotBlank(ticket)) {
                    break;
                }
            }
            return ticket;
        } catch (Exception e) {
            logger.error("[op:waitAndGetTicket] error:", e);
            return null;
        }
    }

    private TokenInfo getLoginInitInfo(String uuid, String ticket)
        throws IOException {
        String loginPage = "https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxnewloginpage?ticket=%s&uuid=%s&lang=zh_CN&scan=1496898816&fun=new&version=v2&lang=zh_CN";
        String loginPage2 = String.format(loginPage, ticket, uuid);
        String s3 = httpClientTemplate.executeGet(loginPage2);
        //解析skey
        String skey = s3.substring(s3.indexOf("<skey>") + 6,
            s3.indexOf("</skey>"));
        String sid = s3.substring(s3.indexOf("<wxsid>") + 7,
            s3.indexOf("</wxsid>"));
        String uin = s3.substring(s3.indexOf("<wxuin>") + 7,
            s3.indexOf("</wxuin>"));
        String passTicket = s3.substring(s3.indexOf("<pass_ticket>") + 13,
            s3.indexOf("</pass_ticket>"));
        String deviceID = "e" + System.currentTimeMillis();
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setSkey(skey);
        tokenInfo.setSid(sid);
        tokenInfo.setUin(uin);
        tokenInfo.setPassTicket(passTicket);
        tokenInfo.setDeviceID(deviceID);
        //todo 获取cookie信息


        return tokenInfo;

    }

    private String getInitInfo(TokenInfo loginInitInfo) throws IOException {
        String initUrl = "https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxinit?r=2059340119&pass_ticket=%s";
        initUrl = String.format(initUrl, loginInitInfo.getPassTicket());
        PostBody postBody = new PostBody();
        postBody.setBaseRequest(loginInitInfo);
        String body = JacksonUtil.write(postBody);
        String s4 = httpClientTemplate.executePost(initUrl, body, "utf-8");
        System.out.println("s4--->"+s4);
        JSONObject jsonObject = JSON.parseObject(s4);
        String fromUsername = jsonObject.getJSONObject("User").get("UserName")
            .toString();
        return fromUsername;
    }

    private List<Contact> getContactList(TokenInfo loginInitInfo)
        throws IOException {
        String contact = "https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxgetcontact?pass_ticket=%s&r=%s&seq=0&skey=%s";
        contact = String.format(contact, loginInitInfo.getPassTicket(),
            System.currentTimeMillis(), loginInitInfo.getSkey());
        String s5 = httpClientTemplate.executeGet(contact);
        ContactResponse contactResponse = JSON.parseObject(s5,
            ContactResponse.class);
        int ret = contactResponse.getBaseResponse().getRet();
        if (ret == 0) {
            return contactResponse.getMemberList();
        }
        return Lists.newArrayList();
    }

    private List<Contact> filterContact(List<Contact> contacts,
        Content content) {
        List<Contact> list = Lists.newArrayList();
        for (Contact contact: contacts) {
            if ("徐文".equalsIgnoreCase(contact.getRemarkName())) {
                list.add(contact);
            }
        }
        return list;
    }


    private boolean sendSingleMsg(String passTicket, PostBody body) {
        try {
            String sendMsg = "https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxsendmsg?lang=zh_CN&pass_ticket=%s";
            sendMsg = String.format(sendMsg, passTicket);
            String s6 = httpClientTemplate.executePost(sendMsg,
                JacksonUtil.write(body), "utf-8");
            logger.info("sendmsg result:{}", s6);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private boolean uploadFile() throws IOException {
        String uploadUrl = "https://file.wx.qq.com/cgi-bin/mmwebwx-bin/webwxuploadmedia?f=json";
        List<NameValuePair> parameters = Lists.newArrayList();
        parameters.add(new BasicNameValuePair("",""));
        httpClientTemplate.executeFilePost(uploadUrl,null,new File("D://a.jpeg"));
        return true;
    }

    public HttpClientTemplate getHttpClientTemplate() {
        return httpClientTemplate;
    }

    public void setHttpClientTemplate(HttpClientTemplate httpClientTemplate) {
        this.httpClientTemplate = httpClientTemplate;
    }
}
