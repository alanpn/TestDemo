package com.example.wubin.baselibrary.util;

import android.Manifest;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.wubin.baselibrary.activity.BaseActivity;

import static com.example.wubin.baselibrary.activity.BaseActivity.myActivity;

public class SystemServiceUtil {

    /**
     * 隐藏键盘
     */
    public static void hideKeyBoardAction() {

        getInputMethodManager();

        if (inputMethodManager.isActive()) {

            View view = myActivity.getWindow().peekDecorView();

            if (null == view) return;

            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

        }
    }

    /**
     * 是否联网
     */
    public static boolean isConnected() {
        getActiveNetworkInfo();
        return networkInfo.isAvailable();
    }

    public static boolean isNotConnected() {
        return !isConnected();
    }

    /**
     * 获取手机IMEI
     * <uses-permission android:name="android.permission.READ_PHONE_STATE" />
     */
    public String getDeviceID() {

        final String deviceID = "0000000000";

        // 在未首先获得 READ_CALL_LOG 权限的情况下，除了应用的用例需要的其他权限之外，
        // 运行于 Android 9 上的应用无法读取电话号码或手机状态
        if (DeviceUtil.getInstance().checkNotPermission(Manifest.permission.READ_CALL_LOG)) {
            return deviceID;
        }
        if (DeviceUtil.getInstance().checkNotPermission(Manifest.permission.READ_PHONE_STATE)) {
            return deviceID;
        }

        getTelephonyManager();
        return telephonyManager.getDeviceId();

    }

    //=============================================

    private static final String className = SystemServiceUtil.class.getName();

    private static ConnectivityManager connectivityManager;
    private static InputMethodManager inputMethodManager;
    private static TelephonyManager telephonyManager;

    private static NetworkInfo networkInfo;

    private static void getConnectivityManager() {
        if (null == connectivityManager) {
            connectivityManager = (ConnectivityManager) BaseActivity.myActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        }
    }

    private static void getInputMethodManager() {
        if (null == inputMethodManager) {
            inputMethodManager = (InputMethodManager) BaseActivity.myActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        }
    }

    private static void getActiveNetworkInfo() {
        getConnectivityManager();
        if (null == networkInfo) networkInfo = connectivityManager.getActiveNetworkInfo();
    }

    private static void getTelephonyManager() {
        if (null == telephonyManager) {
            telephonyManager = (TelephonyManager) BaseActivity.myActivity.getSystemService(Context.TELEPHONY_SERVICE);
        }
    }
}
