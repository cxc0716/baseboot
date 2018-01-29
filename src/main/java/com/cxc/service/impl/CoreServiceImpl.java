package com.cxc.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cxc.configuration.InitConfig;
import com.cxc.model.QueryResultBean;
import com.cxc.model.SubmitParamBean;
import com.cxc.service.RequestProxyService;

@Service
public class CoreServiceImpl {

    private final static Logger logger = LoggerFactory
        .getLogger(CoreServiceImpl.class);

    @Autowired
    private InitConfig initConfig;

    @Autowired
    private RequestProxyService requestProxyService;

    public void submit() {
        //login 

        while (true) {
            // pass rest money check
            boolean pass = requestProxyService.initCheck();
            if (!pass) {
                logger.warn("[op:submit] the capital is not enough.");
                break;
            }
            SubmitParamBean paramBean = new SubmitParamBean();
            boolean submit = requestProxyService.submit(paramBean);

            if (submit) {
                int frequency = initConfig.getFrequency();
                try {
                    Thread.sleep(frequency - 10);
                } catch (InterruptedException e) {}
                while (true) {
                    QueryResultBean queryResultBean = requestProxyService
                        .queryResult("");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {}
                }
            }
            //submit.if not all ok ,retry; else wait with robin

            //received the result

            //judge hit result
        }

    }

    public void simulate() {

    }
}
