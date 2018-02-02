package com.cxc.common.config;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;

public abstract class BaseConfig extends BaseReloadableConfig {

    private Properties prop;

    @Override
    protected final void loadSuccess(InputStream input) {
        prop = loadAsProperties(input);
        if (logger.isDebugEnabled()) {
            logger.debug("{} Properties:{}", getClassName(),
                JSON.toJSON(prop).toString());
        }
        loadSuccess();
    }

    protected Properties loadAsProperties(InputStream input) {
        Properties p = new Properties();
        try {
            BufferedReader brIn = new BufferedReader(
                new InputStreamReader(input, "UTF-8"));
            p.load(brIn);
        } catch (Exception e) {
            logger.error("{} load config file fail:{}", getClassName(),
                getFilename(), e);
        }
        return p;
    }


    protected void loadSuccess() {
    }

    public String getValue(String key) {
        if (this.prop == null) {
            logger.warn("{} config file not loaded, can't get its value {}",
                getClassName(), getFilename());

            return null;
        }
        return this.prop.getProperty(key);
    }

    public String getValue(String key, String defau) {
        String value = getValue(key);
        if (StringUtils.isBlank(value)) {
            return defau;
        }
        return value;
    }

    public int getInt(String key, int defau) {
        String value = getValue(key);
        if (StringUtils.isBlank(value)) {
            return defau;
        }
        return Integer.parseInt(value);
    }

    public long getLong(String key, long defau) {
        String value = getValue(key);
        if (StringUtils.isBlank(value)) {
            return defau;
        }
        return Long.parseLong(value);
    }

    public boolean getBoolean(String key, boolean defau) {
        String value = getValue(key);
        if (StringUtils.isBlank(value)) {
            return defau;
        }
        return Boolean.parseBoolean(value);
    }

    public Date getDate(String key, Date defau) {
        String value = getValue(key);
        if (StringUtils.isBlank(value)) {
            return defau;
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value);
        } catch (ParseException e) {
            //ignore
        }
        return defau;
    }

    public Set<String> getStringKeys() {
        if (this.prop == null) {
            logger.warn("{} config file not loaded, can't get its key set {}",
                getClassName(), getFilename());

            return Collections.emptySet();
        }
        return this.prop.stringPropertyNames();
    }
}
