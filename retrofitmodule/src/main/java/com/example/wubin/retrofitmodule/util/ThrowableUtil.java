package com.example.wubin.retrofitmodule.util;

import com.example.wubin.baselibrary.util.ShowUtil;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

public class ThrowableUtil {

    public static void showErrorMessage(Throwable t) {

        if (t instanceof RequestException) {

            ShowUtil.toastShow(t.getMessage());

        } else if (t instanceof TokenException) {

            ShowUtil.toastShow("登录异常");

        } else if (t instanceof JsonParseException || t instanceof JSONException || t instanceof ParseException) {

            ShowUtil.toastShow("数据解析失败");

        } else if (t instanceof ConnectException) {

            ShowUtil.toastShow("请检查网络连接");

        } else if (t instanceof UnknownHostException) {

            ShowUtil.toastShow("网络地址错误");

        } else if (t instanceof SocketTimeoutException) {

            ShowUtil.toastShow("连接超时");

        } else {
            ShowUtil.toastShow("网络连接开小差儿了");
        }
    }
}
