
package com.cxc.service;

import com.cxc.model.QueryResultBean;
import com.cxc.model.SubmitParamBean;

public interface RequestProxyService {

    public boolean initCheck();

    public boolean submit(SubmitParamBean param);

    public QueryResultBean queryResult(String no);
}
