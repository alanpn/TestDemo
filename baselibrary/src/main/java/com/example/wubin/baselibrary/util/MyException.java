package com.example.wubin.baselibrary.util;

public class MyException extends Exception {
    public MyException(Object... objs) {
        super(StringUtil.getString(objs));
    }
}
