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

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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
        if(StringUtils.isBlank(ticket)){
        	throw  new Exception("扫描超时");
		}
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

        String initUrl = "https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxinit?r=2059340119&pass_ticket=%s";
        initUrl = String.format(initUrl, passTicket);

        Map<String, String> bodyMap = new HashMap<String, String>();
        bodyMap.put("Uin", uin);
        bodyMap.put("Sid", sid);
        bodyMap.put("Skey", skey);
        bodyMap.put("DeviceID", "e" + System.currentTimeMillis());
        Map<String, Object> baseRequest = new HashMap<String, Object>();
        baseRequest.put("BaseRequest", bodyMap);
        String body = JSON.toJSONString(baseRequest);
        String s4 = httpClientTemplate.executePost(initUrl, body, "utf-8");
        JSONObject jsonObject = JSON.parseObject(s4);
        String fromUsername = jsonObject.getJSONObject("User").get("UserName")
            .toString();
        String contact = "https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxgetcontact?pass_ticket=%s&r=%s&seq=0&skey=%s";
        contact = String.format(contact, passTicket, System.currentTimeMillis(),
            skey);
        String s5 = httpClientTemplate.executeGet(contact);
        ContactResponse contactResponse = JSON.parseObject(s5,
            ContactResponse.class);
        int ret = contactResponse.getBaseResponse().getRet();
        if (ret == 0) {
            List<Contact> memberList = contactResponse.getMemberList();
            memberList = filterContact(memberList, content);
            for (Contact contact1: memberList) {
                sendSingleMsg(passTicket, new PostBody());
            }
        }
        return null;
    }

    private String  waitAndGetTicket(String uuid) {
    	String ticket = "";
        try {
			int cnt = 0;
            long time = System.currentTimeMillis();
            while (cnt++ < 5) {
                String tLogin = "https://login.wx.qq.com/cgi-bin/mmwebwx-bin/login?loginicon=true&tip=%s&r=2109427913&_=%s&uuid=%s";
                String login = String.format(tLogin, 1, time, uuid);
                String s1 = httpClientTemplate.executeGet(login);
				if(s1.contains("window.code=408")){
					continue;
				}
                login = String.format(tLogin, 0, time + 1, uuid);
                String s2 = httpClientTemplate.executeGet(login);
                if(s1.contains("window.code=408")){
                	continue;
				}
                String redirectUrl = s2.substring(s2.indexOf("\"") + 1,
                    s2.lastIndexOf("\""));
                 ticket = redirectUrl.substring(
                    redirectUrl.indexOf("ticket=") + 7,
                    redirectUrl.indexOf("&uuid"));
				 if(StringUtils.isNotBlank(ticket)){
				 	break;
				 }
            }
			return ticket;
        } catch (Exception e) {
        	logger.error("[op:waitAndGetTicket] error:",e);
			return null;
        }
    }

    private TokenInfo getLoginInitInfo(String uuid,String ticket) throws IOException{
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
		TokenInfo tokenInfo = new TokenInfo();
		return tokenInfo;

	}



    private List<Contact> filterContact(List<Contact> contacts,
        Content content) {

        return contacts;
    }

    public static void main(String[] args) {
        HttpClientTemplate httpClientTemplate = new HttpClientTemplate();
        httpClientTemplate.init();
        String uuidUrl = "https://login.wx.qq.com/jslogin?appid=wx782c26e4c19acffb&redirect_uri=https%3A%2F%2Fwx.qq.com%2Fcgi-bin%2Fmmwebwx-bin%2Fwebwxnewloginpage&fun=new&lang=zh_CN&_=1496833841341";
        try {
            String s = httpClientTemplate.executeGet(uuidUrl);
            String uuid = s.substring(s.indexOf("\"") + 1, s.lastIndexOf("\""));
            String qrcodeUrl = "https://login.weixin.qq.com/qrcode/" + uuid;
            System.out.println(qrcodeUrl);
            long time = System.currentTimeMillis();
            String tLogin = "https://login.wx.qq.com/cgi-bin/mmwebwx-bin/login?loginicon=true&tip=%s&r=2109427913&_=%s&uuid=%s";
            String login = String.format(tLogin, 1, time, uuid);
            String s1 = httpClientTemplate.executeGet(login);
            System.out.println("s1-->" + s1);
            login = String.format(tLogin, 0, time + 1, uuid);
            String s2 = httpClientTemplate.executeGet(login);
            System.out.println("s2-->" + s2);
            String redirectUrl = s2.substring(s2.indexOf("\"") + 1,
                s2.lastIndexOf("\""));
            //            System.out.println(redirectUrl);
            System.out.println("ok");
            String ticket = redirectUrl.substring(
                redirectUrl.indexOf("ticket=") + 7,
                redirectUrl.indexOf("&uuid"));
            String loginPage = "https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxnewloginpage?ticket=%s&uuid=%s&lang=zh_CN&scan=1496898816&fun=new&version=v2&lang=zh_CN";
            String loginPage2 = String.format(loginPage, ticket, uuid);
            String s3 = httpClientTemplate.executeGet(loginPage2);
            System.out.println("s3-->" + s3);
            //            <error><ret>0</ret><message></message><skey>@crypt_d5fcbef7_32d376d175aff2b6a584377d478bdb05</skey><wxsid>XlfdM+/3gvmAjCoh</wxsid><wxuin>163107255</wxuin><pass_ticket>C2ODUU3VixjknoFCH54OAw7GIkurvyoyWzsHBSoZtmQ%3D</pass_ticket><isgrayscale>1</isgrayscale></error>
            //解析skey
            String skey = s3.substring(s3.indexOf("<skey>") + 6,
                s3.indexOf("</skey>"));
            String sid = s3.substring(s3.indexOf("<wxsid>") + 7,
                s3.indexOf("</wxsid>"));
            String uin = s3.substring(s3.indexOf("<wxuin>") + 7,
                s3.indexOf("</wxuin>"));
            String passTicket = s3.substring(s3.indexOf("<pass_ticket>") + 13,
                s3.indexOf("</pass_ticket>"));
            String initUrl = "https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxinit?r=2059340119&pass_ticket=%s";
            initUrl = String.format(initUrl, passTicket);

            Map<String, String> bodyMap = new HashMap<String, String>();
            bodyMap.put("Uin", uin);
            bodyMap.put("Sid", sid);
            bodyMap.put("Skey", skey);
            bodyMap.put("DeviceID", "e" + System.currentTimeMillis());
            Map<String, Object> baseRequest = new HashMap<String, Object>();
            baseRequest.put("BaseRequest", bodyMap);
            String body = JSON.toJSONString(baseRequest);
            String s4 = httpClientTemplate.executePost(initUrl, body, "utf-8");
            System.out.println("s4-->" + s4);
            JSONObject jsonObject = JSON.parseObject(s4);
            String fromUsername = jsonObject.getJSONObject("User")
                .get("UserName").toString();
            System.out.println("s4-->username-->" + fromUsername);
            String contact = "https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxgetcontact?pass_ticket=%s&r=%s&seq=0&skey=%s";
            contact = String.format(contact, passTicket,
                System.currentTimeMillis(), skey);
            String s5 = httpClientTemplate.executeGet(contact);
            System.out.println("contact###");
            System.out.println("s5-->" + s5);
            ContactResponse contactResponse = JSON.parseObject(s5,
                ContactResponse.class);
            int ret = contactResponse.getBaseResponse().getRet();
            int index = -1;
            if (ret == 0) {
                List<Contact> memberList = contactResponse.getMemberList();
                for (int i = 0; i < memberList.size(); i++) {
                    if ("徐文".equals(memberList.get(i).getRemarkName())) {
                        index = i;
                    }
                }
            }

            //send msg
            String sendMsg = "https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxsendmsg?lang=zh_CN&pass_ticket=%s";
            sendMsg = String.format(sendMsg, passTicket);
            Map<String, Object> msgMap = new HashMap<String, Object>();
            msgMap.put("Type", 1);
            msgMap.put("Content", "测试");
            msgMap.put("FromUserName", fromUsername);
            msgMap.put("ToUserName",
                contactResponse.getMemberList().get(index).getUserName());
            String localID = System.currentTimeMillis() + "";
            msgMap.put("LocalID", localID);
            msgMap.put("ClientMsgId", localID);
            baseRequest.put("Msg", msgMap);
            baseRequest.put("Scene", 0);
            String s6 = httpClientTemplate.executePost(sendMsg,
                JSON.toJSONString(baseRequest), "utf-8");
            System.out.println("send msg,ret->" + s6);
            System.out.println(System.currentTimeMillis());
        } catch (IOException e) {
            e.printStackTrace();
        }
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

}
