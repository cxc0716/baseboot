package com.cxc.service.impl;

import org.springframework.stereotype.Service;

import com.cxc.model.QueryResultBean;
import com.cxc.model.SubmitParamBean;
import com.cxc.service.RequestProxyService;

@Service
public class RequestProxyServiceImpl implements RequestProxyService {
    @Override
    public boolean initCheck() {
        return true;
    }

    @Override
    public boolean submit(SubmitParamBean param) {
        return false;
    }

    @Override
    public QueryResultBean queryResult(String no) {
        return null;
    }
}
