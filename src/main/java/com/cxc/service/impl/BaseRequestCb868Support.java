package com.cxc.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cxc.common.util.HttpClientTemplate;
import com.cxc.common.util.ImageUtil;
import com.cxc.model.CurrentIssueInfo;
import com.cxc.model.RecentIssueInfo;
import com.cxc.model.SubmitParamBean;
import com.cxc.model.SubmitParamItemBean;
import com.google.common.collect.Lists;

public abstract class BaseRequestCb868Support {

    public static final String LOGIN_GET_URI = "/cagamesclient/login/login.do?method=getVerifyImage";

    public static final String LOGIN_CHECK_URI = "/cagamesclient/login/login.do?method=checkVerifyCode";

    public static final String LOGIN_VALIDATE_URI = "/cagamesclient/login/login.do?method=validate";

    public static final String USER_INFO_URI = "/cagamesclient/home/userInfo.do?method=getUserFund";

    public static final String ISSUE_CURRENT_URI = "/cagamesclient/issue.do?method=current";

    public static final String ISSUE_RECENTLY_URI = "/cagamesclient/issue.do?method=recentlyCode";

    public static final String SUBMIT_URI = "/cagamesclient/booking.do?method=add";

    protected abstract String getHost();

    protected abstract String getImgTempPath();

    public abstract String getUsername();

    public abstract String getPassword();

    public abstract int getTimeout();

    public abstract String getInputCodePath();

    public abstract HttpClientTemplate getHttpClientTemplate();

    public abstract String getVerifyCode(String path);

    public abstract String getGameId();

    public abstract int getMethodId();

    protected boolean login() {
        String s = null;
        try {
            s = getHttpClientTemplate().executeGet(getHost() + LOGIN_GET_URI);
            JSONObject jsonObject = JSON.parseObject(s);
            Object data = jsonObject.get("data");
            String tempName = System.currentTimeMillis() + ".png";
            boolean ret = ImageUtil.generateImage(data + "",
                getImgTempPath() + tempName);
            if (ret) {
                String verifyCode = getVerifyCode(getImgTempPath() + tempName);
                org.apache.http.NameValuePair verifyCode1 = new BasicNameValuePair(
                    "verifyCode", verifyCode);
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(verifyCode1);
                String checkResult = getHttpClientTemplate()
                    .executePost(getHost() + LOGIN_CHECK_URI, nameValuePairs);
                JSONObject checkResponse = JSON.parseObject(checkResult);
                int success = checkResponse.getIntValue("success");
                if (success == 1) {
                    //doLogin
                    org.apache.http.NameValuePair usernamePair = new BasicNameValuePair(
                        "userName", getUsername());
                    org.apache.http.NameValuePair userPwd = new BasicNameValuePair(
                        "userPwd", getPassword());
                    org.apache.http.NameValuePair code = new BasicNameValuePair(
                        "verifyCode", verifyCode);
                    org.apache.http.NameValuePair channelType = new BasicNameValuePair(
                        "channelType", "web");
                    org.apache.http.NameValuePair timout = new BasicNameValuePair(
                        "timeout", getTimeout() + "");
                    ArrayList<NameValuePair> nameValuePairs2 = Lists
                        .newArrayList(usernamePair, userPwd, code, channelType,
                            timout);
                    String loginJson = getHttpClientTemplate().executePost(
                        getHost() + LOGIN_VALIDATE_URI, nameValuePairs2,
                        "utf-8");
                    int loginResult = JSON.parseObject(loginJson)
                        .getIntValue("success");
                    if (loginResult == 1) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    protected BigDecimal getResultRefund() {
        String url = getHost() + USER_INFO_URI;
        try {
            String response = getHttpClientTemplate().executeGet(url);
            JSONObject jsonObject = JSON.parseObject(response);
            int success = jsonObject.getIntValue("success");
            if (success == 1) {
                BigDecimal resultMoney = jsonObject
                    .getBigDecimal("availableBalance");
                return resultMoney;
            }
        } catch (IOException e) {
            return null;
        }
        return BigDecimal.ZERO;
    }

    protected CurrentIssueInfo getCurrentIssueInfo() {
        String url = getHost() + ISSUE_CURRENT_URI;
        NameValuePair gameid = new BasicNameValuePair("gameid", getGameId());
        try {
            String result = getHttpClientTemplate().executePost(url,
                Lists.newArrayList(gameid));
            if (StringUtils.isNotBlank(result)) {
                return JSON.parseObject(result, CurrentIssueInfo.class);
            }
        } catch (IOException e) {
            return null;
        }
        return null;
    }

    protected RecentIssueInfo getRecentIssueInfo(int pageNo, int pageSize) {
        String url = getHost() + ISSUE_RECENTLY_URI;
        org.apache.http.NameValuePair gameId = new BasicNameValuePair("gameid",
            getGameId());
        org.apache.http.NameValuePair pageNum = new BasicNameValuePair(
            "pageNum", pageNo + "");
        org.apache.http.NameValuePair size = new BasicNameValuePair("size",
            pageSize + "");
        try {
            String result = getHttpClientTemplate().executeGet(url,
                Lists.<NameValuePair>newArrayList(gameId, pageNum, size));
            if (StringUtils.isNotBlank(result)) {
                return JSON.parseObject(result, RecentIssueInfo.class);
            }
        } catch (IOException e) {
            return null;
        }
        return null;
    }

    protected boolean submit(SubmitParamBean paramBean) {
        String url = getHost() + SUBMIT_URI;
        org.apache.http.NameValuePair p1 = new BasicNameValuePair("gameid",
            getGameId());
        String issue = getCurrentIssueInfo().getIssue();
        org.apache.http.NameValuePair p2 = new BasicNameValuePair("issue",
            issue);
        org.apache.http.NameValuePair p3 = new BasicNameValuePair("totalnums",
            paramBean.getNos().size() + "");
        org.apache.http.NameValuePair p4 = new BasicNameValuePair("totalmoney",
            paramBean.getPrice().setScale(3, RoundingMode.HALF_UP)
                .toPlainString());
        org.apache.http.NameValuePair p5 = new BasicNameValuePair("type", "1");
        org.apache.http.NameValuePair p6 = new BasicNameValuePair("isusefree",
            "0");
        org.apache.http.NameValuePair p7 = new BasicNameValuePair("trace", "");
        org.apache.http.NameValuePair p8 = new BasicNameValuePair("isJoinPool",
            "0");
        SubmitParamItemBean item = new SubmitParamItemBean();
        item.setMethodid(getMethodId());
        item.setType(0);
        item.setPos("");
        item.setCodes(StringUtils.join(paramBean.getNos(), "|"));
        item.setCount(paramBean.getNos().size());
        item.setTimes(paramBean.getTime());
        item.setMoney(paramBean.getPrice().doubleValue());
        item.setMode(paramBean.getMode());
        item.setUserpoint("0.0000");
        org.apache.http.NameValuePair p9 = new BasicNameValuePair("items",
            JSON.toJSONString(Lists.newArrayList(item)));
        try {
            String result = getHttpClientTemplate().executePost(url,
                Lists.newArrayList(p1, p2, p3, p4, p5, p6, p7, p8, p9));
            JSONObject jsonObject = JSON.parseObject(result);
            int success = jsonObject.getIntValue("success");
            return success == 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;

    }
}
