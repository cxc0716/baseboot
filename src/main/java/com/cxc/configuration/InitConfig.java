package com.cxc.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class InitConfig {

    //本金
    private long capital;

    //单元
    private int unit;

    //单位:秒
    private int frequency;

    public long getCapital() {
        return capital;
    }

    public void setCapital(long capital) {
        this.capital = capital;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
}
