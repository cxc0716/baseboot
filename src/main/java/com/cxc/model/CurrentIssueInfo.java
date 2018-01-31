package com.cxc.model;

public class CurrentIssueInfo extends BaseResponseBean {
    //"current":1517274658000,"dailyCount":120,"issue":"180130024","lotteryid":1,"openedCount":23,"saleend":1517277540000,"salestart":1517248460000,"success":1}

    private long current;

    private int dailyCount;

    private String issue;

    private int lotteryid;

    private int openedCount;

    private long saleend;

    private long salestart;

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public int getDailyCount() {
        return dailyCount;
    }

    public void setDailyCount(int dailyCount) {
        this.dailyCount = dailyCount;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public int getLotteryid() {
        return lotteryid;
    }

    public void setLotteryid(int lotteryid) {
        this.lotteryid = lotteryid;
    }

    public int getOpenedCount() {
        return openedCount;
    }

    public void setOpenedCount(int openedCount) {
        this.openedCount = openedCount;
    }

    public long getSaleend() {
        return saleend;
    }

    public void setSaleend(long saleend) {
        this.saleend = saleend;
    }

    public long getSalestart() {
        return salestart;
    }

    public void setSalestart(long salestart) {
        this.salestart = salestart;
    }
}
