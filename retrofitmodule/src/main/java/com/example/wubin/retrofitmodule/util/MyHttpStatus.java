package com.example.wubin.retrofitmodule.util;

public class MyHttpStatus {

    public static final String SUCCESS_CODE = "200"; //  成功code
    public static final String TOKEN_FAIL_CODE = "401"; // 登录失效
    public static final String KEY_DATA = "data"; // 解析数据所在的层次

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "MyHttpStatus{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
