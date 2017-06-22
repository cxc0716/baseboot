package com.cxc.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cxc.common.constant.CommonConstant;
import com.cxc.common.util.HttpClientTemplate;
import com.cxc.common.util.JacksonUtil;
import com.cxc.domain.Content;
import com.cxc.model.MsgInfo;
import com.cxc.model.PostBody;
import com.cxc.model.QrcodeInfo;
import com.cxc.model.TokenInfo;
import com.cxc.model.weixin.Contact;
import com.cxc.model.weixin.ContactResponse;
import com.cxc.model.weixin.UploadResponse;
import com.cxc.service.WeixinService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * author:chenxinchao date:2017-06-10 21:07 desc:com.cxc.service.impl
 */
@Service
public class WeixinServiceImpl implements WeixinService {

    private static final Logger logger = LoggerFactory
        .getLogger(WeixinServiceImpl.class);

    @Autowired
    private HttpClientTemplate httpClientTemplate;

    @Value("${upload.file.path}")
    private String filePath;

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
            throw new Exception("扫描超时，请刷新二维码重新扫描");
        }
        TokenInfo loginInitInfo = getLoginInitInfo(uuid, ticket);

        String userName = getInitInfo(loginInitInfo);
        List<Contact> contactList = getContactList(loginInitInfo);
        List<Contact> contacts = filterContact(contactList, content);
        if (!CollectionUtils.isEmpty(contacts)) {
            for (Contact contact: contacts) {
                PostBody postBody = new PostBody();
                postBody.setBaseRequest(loginInitInfo);
                MsgInfo msgInfo = new MsgInfo();
                msgInfo.setFromUserName(userName);
                msgInfo.setToUserName(contact.getUserName());
                msgInfo.setContent(content.getText());
                postBody.setMsg(msgInfo);
                postBody.setScene(0);
                sendSingleMsg(loginInitInfo.getPassTicket(), postBody);
                if (StringUtils.isNotBlank(content.getPicUrl())) {
                    sendPicMsg(loginInitInfo, postBody, filePath+content.getPicUrl());
                }
            }
        }
        return true;
    }

    private String waitAndGetTicket(String uuid) {
        String ticket = "";
        try {
            int cnt = 0;
            long time = System.currentTimeMillis();
            while (cnt++ < 6) {
                Long passTime = System.currentTimeMillis() - time;
                if (passTime > CommonConstant.VALID_TIME_SEC * 1000L) {
                    break;
                }
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
        Map<String, String> result = httpClientTemplate
            .getBodyAndCookieByPost(loginPage2, null);
        String s3 = result.get("body");
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
        tokenInfo.setUin(Long.valueOf(uin));
        tokenInfo.setPassTicket(passTicket);
        tokenInfo.setDeviceID(deviceID);
        //获取cookie信息
        tokenInfo.setDataTicket(result.get("dataTicket"));
        return tokenInfo;
    }

    private String getInitInfo(TokenInfo loginInitInfo) throws IOException {
        String initUrl = "https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxinit?r=2059340119&pass_ticket=%s";
        initUrl = String.format(initUrl, loginInitInfo.getPassTicket());
        PostBody postBody = new PostBody();
        postBody.setBaseRequest(loginInitInfo);
        String body = JacksonUtil.write(postBody);
        String s4 = httpClientTemplate.executePost(initUrl, body, "utf-8");
        System.out.println("s4--->" + s4);
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
            /*
             * if (content.getSex() == 0 || contact.getSex() ==
             * content.getSex()) { //friend if (content.getSendType() == 1 &&
             * contact.getContactFlag() == 1) { list.add(contact); } else if
             * (contact.getUserName().startsWith("@@")) { //group
             * list.add(contact); } }
             */
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

    private boolean sendPicMsg(TokenInfo tokenInfo, PostBody body,
        String filePath) {
        try {
            String mediaId = uploadFile(tokenInfo,
                body.getMsg().getFromUserName(), body.getMsg().getToUserName(),
                new File(filePath));
            if (StringUtils.isNotBlank(mediaId)) {
                body.getMsg().setMediaId(mediaId);
                return sendMedia(tokenInfo.getPassTicket(), body);
            }
            return false;
        } catch (Exception e) {
            logger.error("[op:sendPicMsg]:", e);
            return false;
        }
    }

    /**
     * @param tokenInfo
     * @param file
     * @return
     * @throws IOException
     */
    private String uploadFile(TokenInfo tokenInfo, String from, String to,
        File file) throws IOException {
        String uploadUrl = "https://file.wx.qq.com/cgi-bin/mmwebwx-bin/webwxuploadmedia?f=json";
        List<NameValuePair> parameters = Lists.newArrayList();
        parameters.add(new BasicNameValuePair("id", "WU_FILE_0"));
        parameters.add(new BasicNameValuePair("name", file.getName()));
        String type = "image/" + getExtWithoutDot(file.getName());
        parameters.add(new BasicNameValuePair("type", type));
        String format = DateFormatUtils.format(new Date(),
            "EEE MMM dd yyyy hh:mm:ss 'GMT+0800 (CST)'", Locale.ENGLISH);
        parameters.add(new BasicNameValuePair("lastModifiedDate", format));
        parameters.add(new BasicNameValuePair("size", file.length() + ""));
        parameters.add(new BasicNameValuePair("mediatype", "pic"));
        parameters.add(new BasicNameValuePair("webwx_data_ticket",
            tokenInfo.getDataTicket()));
        parameters.add(
            new BasicNameValuePair("pass_ticket", tokenInfo.getPassTicket()));
        parameters.add(new BasicNameValuePair("filename", file.getName()));

        Map<String, Object> map = Maps.newHashMap();
        map.put("UploadType", 2);
        map.put("BaseRequest", tokenInfo);
        map.put("ClientMediaId", System.currentTimeMillis());
        map.put("TotalLen", file.length());
        map.put("DataLen", file.length());
        map.put("StartPos", 0);
        map.put("MediaType", 4);
        map.put("FromUserName", from);
        map.put("ToUserName", to);
        map.put("FileMd5", getFileMd5(file));
        parameters.add(new BasicNameValuePair("uploadmediarequest",
            JacksonUtil.write(map)));
        String s = httpClientTemplate.executeFilePost(uploadUrl, parameters,
            file, type);
        System.out.println("upload>>>>" + s);
        UploadResponse read = JacksonUtil.read(s, UploadResponse.class);
        if (read.getBaseResponse().getRet() == 0) {
            return read.getMediaId();
        }
        return null;
    }

    private boolean sendMedia(String passTicket, PostBody body)
        throws IOException {
        String url = "https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxsendmsgimg?fun=async&f=json&pass_ticket=%s";
        url = String.format(url, passTicket);
        body.getMsg().setType(3);
        String result = httpClientTemplate.executePost(url,
            JacksonUtil.write(body), "utf-8");
        ContactResponse read = JacksonUtil.read(result, ContactResponse.class);
        if (read != null && read.getBaseResponse().getRet() == 0) {
            return true;
        }
        return false;
    }

    private String getExtWithoutDot(String fileName) {
        int i = fileName.lastIndexOf(".");
        return fileName.substring(i + 1, fileName.length());
    }

    private String getFileMd5(File file) throws FileNotFoundException {
        String value = null;
        FileInputStream in = new FileInputStream(file);
        try {
            MappedByteBuffer byteBuffer = in.getChannel()
                .map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

    public void setHttpClientTemplate(HttpClientTemplate httpClientTemplate) {
        this.httpClientTemplate = httpClientTemplate;
    }
}
