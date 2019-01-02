package com.example.wubin.baselibrary.util;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.wubin.baselibrary.activity.BaseActivity;

import static com.example.wubin.baselibrary.activity.BaseActivity.myActivity;

public class ScreenUtil {

    /**
     * 设置焦点
     */
    public static void setFocusable(View view, boolean focusable) {

        try {

            ObjectUtil.isNull(className, view, "view 为空");

            if (focusable) {

                view.setFocusable(true);
                view.setFocusableInTouchMode(true);
                view.requestFocus();

            } else {

                view.setFocusable(true);
                view.setFocusableInTouchMode(true);

            }

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

    }

    /**
     * 获取sdk版本号
     */
    public static int getSDK() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 获取版本名字
     */
    public static String getVersionName() {

        try {

            return getPackageInfo().versionName;

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

        return "";

    }

    /**
     * 获取版本号
     */
    public static int getVersionCode() {

        try {

            return getPackageInfo().versionCode;

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

        return 0;

    }

    public static int getInstallApk(String packageName) {

        try {

            return BaseActivity.myActivity.getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES).versionCode;

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

        return 0;
    }

    public static int getTargetSdkVersion() {

        try {

            return getPackageInfo().applicationInfo.targetSdkVersion;

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

        return 0;

    }

    public static PackageManager getPackageManager() {
        return BaseActivity.myActivity.getPackageManager();
    }

    private static String getPackageName() {
        return BaseActivity.myActivity.getPackageName();
    }

    public static int dp2Px(float dp) {
        return (int) (dp * getDensity() + 0.5f);
    }

    public static int px2Dp(float px) {
        return (int) (px / getDensity() + 0.5f);
    }

    public static int getDisplayHeight() {
        return getDisplayMetrics().heightPixels;
    }

    public static int getDisplayWidth() {
        return getDisplayMetrics().widthPixels;
    }

    //=============================================

    private static final String className = ScreenUtil.class.getName();

    private static float density;
    private static DisplayMetrics displayMetrics;
    private static PackageInfo packageInfo;

    private static DisplayMetrics getDisplayMetrics() {
        if (null == displayMetrics) displayMetrics = myActivity.getResources().getDisplayMetrics();
        return displayMetrics;
    }

    private static float getDensity() {
        if (0 == density) density = getDisplayMetrics().density;
        return density;
    }

    private static PackageInfo getPackageInfo() throws Exception {
        if (null == packageInfo) return getPackageManager().getPackageInfo(getPackageName(), 0);
        return packageInfo;
    }
}
