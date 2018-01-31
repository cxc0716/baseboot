package com.cxc.model;

import java.util.List;

public class RecentIssueInfo extends BaseResponseBean {
    private List<RecentIssueItem> items;

    public List<RecentIssueItem> getItems() {
        return items;
    }

    public void setItems(List<RecentIssueItem> items) {
        this.items = items;
    }
}
