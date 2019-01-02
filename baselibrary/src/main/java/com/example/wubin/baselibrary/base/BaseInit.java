package com.example.wubin.baselibrary.base;

import android.content.Context;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.example.wubin.baselibrary.util.ShowUtil;

public class BaseInit {

    // ShowUtil 是否可以打印
    public static final boolean canPrint = true;

    public static final String TAG = "testDemo";

    // 没值
    public static final int NOT_VALUE = -99;


    public static Context getApplicationContext() {

        try {

            if (null == applicationContext) {
                applicationContext = BaseActivity.myActivity.getApplicationContext();
            }

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

        return applicationContext;
    }

    //================================

    private static Context applicationContext;

}
