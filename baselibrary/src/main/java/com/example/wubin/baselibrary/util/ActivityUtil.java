package com.example.wubin.baselibrary.util;

import android.content.Intent;

import com.example.wubin.baselibrary.activity.BaseActivity;

public class ActivityUtil {

    public static void startActivity(final Intent intent) {
        try {

            checkActivity(className);

            BaseActivity.myActivity.startActivity(intent);

        } catch (Exception e) {
            ShowUtil.print(e);
        }
    }

    public static void startActivityForResult(final Intent intent, final int requestCode) {
        try {

            checkActivity(className);

            BaseActivity.myActivity.startActivityForResult(intent, requestCode);

        } catch (Exception e) {
            ShowUtil.print(e);
        }
    }

    public static void checkActivity(String name) throws Exception {

        if (null == BaseActivity.myActivity) throw new MyException(name, "activity 为空");

        if (BaseActivity.myActivity.isFinishing()) {
            throw new MyException(name, "activity 正在关闭中");
        }

    }

    //======================================================

    private static final String className = ActivityUtil.class.getName();

}
