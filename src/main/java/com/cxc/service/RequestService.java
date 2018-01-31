package com.cxc.service;

import java.math.BigDecimal;

import com.cxc.model.CurrentIssueInfo;
import com.cxc.model.RecentIssueInfo;
import com.cxc.model.SubmitParamBean;

public interface RequestService {

    public boolean submit(SubmitParamBean param);

    public boolean login();

    public BigDecimal getResultRefund();

    public CurrentIssueInfo getCurrentIssueInfo();

    public RecentIssueInfo getRecentIssueInfo(int pageNo, int pageSize);

}
