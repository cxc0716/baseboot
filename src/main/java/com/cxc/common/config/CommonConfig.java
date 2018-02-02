package com.cxc.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig extends BaseConfig {

    @Value("${config.common.path}")
    private String filename;

    @Override
    protected String getFilename() {
        return filename;
    }
    public String getVal() {
        return getValue("key", "");
    }
}
