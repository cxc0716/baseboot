package com.cxc.model;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Lists;

public class RecentIssueItem {

    private String issue;

    private String code;

    private int lotteryid;

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getLotteryid() {
        return lotteryid;
    }

    public void setLotteryid(int lotteryid) {
        this.lotteryid = lotteryid;
    }

    public List<String> getCodeList() {
        if (StringUtils.isBlank(code)) {
            return Lists.newArrayList();
        }
        return Lists.newArrayList(code.split(","));
    }
}
