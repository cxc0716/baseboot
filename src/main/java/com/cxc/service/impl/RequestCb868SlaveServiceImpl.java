package com.cxc.service.impl;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cxc.common.util.HttpClientTemplate;
import com.cxc.model.CurrentIssueInfo;
import com.cxc.model.RecentIssueInfo;
import com.cxc.model.SubmitParamBean;
import com.cxc.service.RequestService;

@Service("requestCb868SlaveService")
public class RequestCb868SlaveServiceImpl extends BaseRequestCb868Support
    implements RequestService {

    @Value("${platform.1.host}")
    private String host;

    @Value("${platform.1.login.timeout:5000}")
    private int timeout;

    @Value("${platform.1.gameId}")
    private String gameId;

    @Value("${platform.1.methodId}")
    private int methodId;

    @Value("${platform.1.code.path2}")
    private String imgTempPath;

    @Value("${platform.1.login.username2}")
    private String username;

    @Value("${platform.1.login.password2}")
    private String password;


    @Value("${platform.1.login.code.file.path2}")
    private String inputCodePath;



    @Autowired
    private HttpClientTemplate httpClientTemplate;

    @Override
    public boolean submit(SubmitParamBean param) {
        return super.submit(param);
    }

    @Override
    public boolean login() {
        return super.login();
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

    public String getVerifyCode(String imgPath) {
        String code = "";
        while (true) {
            //read

            if (StringUtils.isNotBlank(code)) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        }
        return code;
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

}
