package com.example.wubin.retrofitmodule.util;

/**
 * @author luyanjun
 * @email lu.yj@sand.com.cn
 * @description token失效或未传抛出此异常
 */
public class RequestException extends RuntimeException {
    public RequestException(String msg) {
        super(msg);
    }
}
