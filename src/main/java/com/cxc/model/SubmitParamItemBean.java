package com.cxc.model;

public class SubmitParamItemBean {
    //    items:[{"methodid":29,"type":0,"pos":"","codes":"50|55|58","count":3,"times":1,"money":0.6000000000000001,"mode":2,"userpoint":"0.0000"}]

    private int methodid = 29;

    private int type = 0;

    private String pos = "";

    //多个中间以|隔开
    private String codes;

    private int count;

    private int times = 1;

    private double money;

    private int mode; //  元-1 角-2 分-3 厘-4

    private String userpoint;

    public int getMethodid() {
        return methodid;
    }

    public void setMethodid(int methodid) {
        this.methodid = methodid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getCodes() {
        return codes;
    }

    public void setCodes(String codes) {
        this.codes = codes;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getUserpoint() {
        return userpoint;
    }

    public void setUserpoint(String userpoint) {
        this.userpoint = userpoint;
    }
}
