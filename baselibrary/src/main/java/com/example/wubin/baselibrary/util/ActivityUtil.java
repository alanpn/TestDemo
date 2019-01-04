package com.example.wubin.baselibrary.util;

import android.content.Intent;

import com.example.wubin.baselibrary.activity.BaseActivity;

public class ActivityUtil {

    public static void startActivity(final Intent intent) {
        try {

            checkActivity();
            BaseActivity.myActivity.startActivity(intent);

        } catch (Exception e) {
            ShowUtil.print(e);
        }
    }

    public static void startActivityForResult(final Intent intent, final int requestCode) {
        try {

            checkActivity();
            BaseActivity.myActivity.startActivityForResult(intent, requestCode);

        } catch (Exception e) {
            ShowUtil.print(e);
        }
    }

    //======================================================

    private static final String className = ActivityUtil.class.getName();

    private static void checkActivity() throws Exception {

        if (null == BaseActivity.myActivity) throw new MyException(className, "activity 为空");

        if (BaseActivity.myActivity.isFinishing()) {
            throw new MyException(className, "activity 正在关闭中");
        }
    }

}
