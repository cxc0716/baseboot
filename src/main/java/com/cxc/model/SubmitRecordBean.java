package com.cxc.model;

public class SubmitRecordBean {

    private String issue;
    private int hitIndex;
    private boolean continuous;

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public int getHitIndex() {
        return hitIndex;
    }

    public void setHitIndex(int hitIndex) {
        this.hitIndex = hitIndex;
    }

    public boolean isContinuous() {
        return continuous;
    }

    public void setContinuous(boolean continuous) {
        this.continuous = continuous;
    }
}
