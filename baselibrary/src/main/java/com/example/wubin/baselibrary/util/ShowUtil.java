package com.example.wubin.baselibrary.util;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.example.wubin.baselibrary.base.BaseInit;

public class ShowUtil {

    public static void print(Object... objs) {
        if (BaseInit.canPrint) Log.d(BaseInit.TAG, StringUtil.getString(objs));
    }

    public static void toastShow(Object... objs) {
        toastShow(BaseActivity.myActivity, objs);
    }

    public static void toastShow(Activity activity, Object... objs) {
        Toast.makeText(activity, StringUtil.getString(objs), Toast.LENGTH_LONG).show();
    }

    public static void showErrorMessage(Exception e) {
        e.printStackTrace();
        print(e.getMessage());
    }

    public static void showErrorMessage(String className, String errorInfo) throws Exception {
        throw new Exception(StringUtil.getString(className, errorInfo));
    }

    //===========================

    private static final String className = ShowUtil.class.getName();
}
