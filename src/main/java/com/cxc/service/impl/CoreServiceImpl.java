package com.cxc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cxc.configuration.InitConfig;
import com.cxc.service.RequestProxyService;

@Service
public class CoreServiceImpl {
    @Autowired
    private InitConfig initConfig;

    @Autowired
    private RequestProxyService requestProxyService;

    public void submit() {
        //login 


        while (true) {
            // pass rest money check

            //submit.if not all ok ,retry; else wait with robin

            //received the result

            //judge hit result
        }

    }

}
