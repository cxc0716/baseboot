package com.cxc.model;

import java.math.BigDecimal;
import java.util.List;

public class SubmitParamBean {

    public static final int MODE_YUAN = 1;

    public static final int MODE_JIAO = 2;

    public static final int MODE_FEN = 3;

    public static final int MODE_LI = 4;

    private int time;

    private List<Integer> nos;

    private int mode;

    private BigDecimal price;

    private boolean main;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public List<Integer> getNos() {
        return nos;
    }

    public void setNos(List<Integer> nos) {
        this.nos = nos;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isMain() {
        return main;
    }

    public void setMain(boolean main) {
        this.main = main;
    }
}
