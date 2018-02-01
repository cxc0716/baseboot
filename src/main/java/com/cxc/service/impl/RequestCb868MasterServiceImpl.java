package com.cxc.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cxc.common.util.HttpClientTemplate;
import com.cxc.common.util.ImageUtil;
import com.cxc.model.CurrentIssueInfo;
import com.cxc.model.RecentIssueInfo;
import com.cxc.model.SubmitParamBean;
import com.cxc.model.SubmitParamItemBean;
import com.cxc.service.RequestService;
import com.google.common.collect.Lists;

@Service("requestCb868MasterService")
public class RequestCb868MasterServiceImpl extends BaseRequestCb868Support
    implements RequestService {

    @Value("${platform.1.host}")
    private String host;

    @Value("${platform.1.code.path}")
    private String imgTempPath;

    @Value("${platform.1.login.username}")
    private String username;

    @Value("${platform.1.login.password}")
    private String password;

    @Value("${platform.1.login.timeout:5000}")
    private int timeout;

    @Value("${platform.1.login.code.file.path}")
    private String inputCodePath;

    @Value("${platform.1.gameId}")
    private String gameId;

    @Value("${platform.1.methodId}")
    private int methodId;

    @Autowired
    private HttpClientTemplate httpClientTemplate;

    @Override
    public boolean submit(SubmitParamBean param) {
        return super.submit(param);
    }

    @Override
    public boolean login() {
        int maxTime = 0;
        while (maxTime < 3) {
            boolean ret = super.login();
            if (ret) {
                return ret;
            }
            maxTime++;
        }
        return false;
    }

    @Override
    public BigDecimal getResultRefund() {
        return super.getResultRefund();
    }

    @Override
    public CurrentIssueInfo getCurrentIssueInfo() {
        return super.getCurrentIssueInfo();
    }

    @Override
    public RecentIssueInfo getRecentIssueInfo(int pageNo, int pageSize) {
        return super.getRecentIssueInfo(pageNo, pageSize);
    }

    @Override
    public String getGameId() {
        return gameId;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public String getImgTempPath() {
        return imgTempPath;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public int getTimeout() {
        return timeout;
    }

    @Override
    public String getInputCodePath() {
        return inputCodePath;
    }

    @Override
    public HttpClientTemplate getHttpClientTemplate() {
        return httpClientTemplate;
    }

    @Override
    public int getMethodId() {
        return methodId;
    }

    public static void main(String[] args)
        throws IOException, NoSuchAlgorithmException, KeyManagementException {
        HttpClientTemplate httpClientTemplate2 = new HttpClientTemplate();
        httpClientTemplate2.init();
        String getImgUrl = "https://api.cb868.net:1888/cagamesclient/login/login.do?method=getVerifyImage";
        String s = httpClientTemplate2.executeGet(getImgUrl);
        JSONObject jsonObject = JSON.parseObject(s);
        Object data = jsonObject.get("data");
        boolean ret = ImageUtil.generateImage(data + "", "D:/test/code.png");
        System.out.println("please input verify code:");
        Scanner scanner = new Scanner(System.in);
        String verifyCode = "";
        if (scanner.hasNext()) {
            verifyCode = scanner.next();
        }
        String checkUrl = "https://api.cb868.net:1888/cagamesclient/login/login.do?method=checkVerifyCode";
        org.apache.http.NameValuePair verifyCode1 = new BasicNameValuePair(
            "verifyCode", verifyCode);
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(verifyCode1);
        String s1 = httpClientTemplate2.executePost(checkUrl, nameValuePairs);
        System.out.println("check result-->" + s1);

        String loginUrl = "https://api.cb868.net:1888/cagamesclient/login/login.do?method=validate";
        org.apache.http.NameValuePair username = new BasicNameValuePair(
            "userName", "ttjc2017");
        org.apache.http.NameValuePair userPwd = new BasicNameValuePair(
            "userPwd", "q1w2e3r4");
        org.apache.http.NameValuePair code = new BasicNameValuePair(
            "verifyCode", verifyCode);
        org.apache.http.NameValuePair channelType = new BasicNameValuePair(
            "channelType", "web");
        org.apache.http.NameValuePair timout = new BasicNameValuePair("timeout",
            5000 + "");
        ArrayList<NameValuePair> nameValuePairs2 = Lists.newArrayList(username,
            userPwd, code, channelType, timout);
        String s2 = httpClientTemplate2.executePost(loginUrl, nameValuePairs2,
            "utf-8");
        System.out.println("login result-->" + s2);

        String userInfo = "https://api.cb868.net:1888/cagamesclient/home/userInfo.do?method=getUserFund";
        String s3 = httpClientTemplate2.executeGet(userInfo);
        System.out.println("issue result-->" + s3);

        String history = "https://api.cb868.net:1888/cagamesclient/issue.do?method=recentlyCode";
        org.apache.http.NameValuePair gameId = new BasicNameValuePair("gameid",
            "1");
        org.apache.http.NameValuePair pageNum = new BasicNameValuePair(
            "pageNum", "1");
        org.apache.http.NameValuePair size = new BasicNameValuePair("size",
            "30");
        String s4 = httpClientTemplate2.executeGet(history,
            Lists.<NameValuePair>newArrayList(gameId, pageNum, size));
        System.out.println("history result-->" + s4);

        String current = "https://api.cb868.net:1888/cagamesclient/issue.do?method=current";
        String s5 = httpClientTemplate2.executeGet(current,
            Lists.newArrayList(gameId));
        System.out.println("current result-->" + s5);

        String submitUrl = "https://api.cb868.net:1888/cagamesclient/booking.do?method=add";
        org.apache.http.NameValuePair p1 = new BasicNameValuePair("gameid",
            "1");
        org.apache.http.NameValuePair p2 = new BasicNameValuePair("issue",
            "180130070");
        org.apache.http.NameValuePair p3 = new BasicNameValuePair("totalnums",
            "3");
        org.apache.http.NameValuePair p4 = new BasicNameValuePair("totalmoney",
            "0.06");
        org.apache.http.NameValuePair p5 = new BasicNameValuePair("type", "1");
        org.apache.http.NameValuePair p6 = new BasicNameValuePair("isusefree",
            "0");
        org.apache.http.NameValuePair p7 = new BasicNameValuePair("trace", "");
        org.apache.http.NameValuePair p8 = new BasicNameValuePair("isJoinPool",
            "0");

        SubmitParamItemBean item = new SubmitParamItemBean();
        item.setMethodid(29);
        item.setType(0);
        item.setPos("");
        item.setCodes("02|21|88");
        item.setCount(3);
        item.setTimes(1);
        item.setMoney(0.06);
        item.setMode(3);
        item.setUserpoint("0.0000");
        org.apache.http.NameValuePair p9 = new BasicNameValuePair("items",
            JSON.toJSONString(Lists.newArrayList(item)));
        String s6 = httpClientTemplate2.executePost(submitUrl,
            Lists.newArrayList(p1, p2, p3, p4, p5, p6, p7, p8, p9));
        System.out.println("submit result-->" + s6);

        //        gameid:1

        //        issue:180130067
        //        totalnums:3
        //        totalmoney:0.6000000000000001
        //        type:1
        //        isusefree:0
        //        items:[{"methodid":29,"type":0,"pos":"","codes":"50|55|58","count":3,"times":1,"money":0.6000000000000001,"mode":2,"userpoint":"0.0000"}]
        //        trace:
        //        isJoinPool:0

    }

}
