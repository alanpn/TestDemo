package com.wubin.testdemo.banner;

/**
 * @author luyanjun
 * @email lu.yj@sand.com.cn
 * @description
 */
public class PaletColor {
    private int rgb;
    private String method;

    public PaletColor(int rgb, String method) {
        this.rgb = rgb;
        this.method = method;
    }

    public int getRgb() {
        return rgb;
    }

    public void setRgb(int rgb) {
        this.rgb = rgb;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
