package com.example.wubin.retrofitmodule.util;

import com.google.gson.Gson;

import java.io.IOException;

public class MyConverterUtil {

    public static void resolveJson(String json, Gson gson) throws IOException {

//        Util.print(json);
        MyHttpStatus status = gson.fromJson(json, MyHttpStatus.class);
//        Util.print(status);
        String code = status.getCode();

        if (MyHttpStatus.SUCCESS_CODE.equals(code)) {
            return;
        }

        String msg = status.getMsg();
//        Util.print(msg);
        if (MyHttpStatus.TOKEN_FAIL_CODE.equals(code)) {
            throw new TokenException(msg);
        } else {
            throw new RequestException(msg);
        }

    }
}
