package com.example.wubin.baselibrary.util;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;

import static com.example.wubin.baselibrary.activity.BaseActivity.myActivity;

public class DeviceUtil {

    /**
     * 是否有NFC
     */
    public static NfcAdapter hasNFC() {
        return NfcAdapter.getDefaultAdapter(myActivity);
    }

    /**
     * NFC是否开启
     */
    public static boolean isNfcEnabled() {

        NfcAdapter adapter = hasNFC();
        if (null == adapter) return false;
        return adapter.isEnabled();

    }

    /**
     * 判断摄像头权限
     */
    public static boolean checkCameraPermission() {
        try {

            Camera camera = Camera.open();
            camera.release();
            camera = null;
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }

    /**
     * 判断读取联系人权限
     */
    public static boolean checkReadContact() {
        return checkPermission(Manifest.permission.READ_CONTACTS);
    }

    /**
     * 判断是否有权限
     */
    public static boolean checkPermission(final String permission) {

        try {

            // only targetSdkVersion >=23 || android 6.0
            if (getSDK() >= 23) {
                return ContextCompat.checkSelfPermission(myActivity, permission) == PackageManager.PERMISSION_GRANTED;
            } else {
                return PermissionChecker.checkSelfPermission(myActivity, permission) == PermissionChecker.PERMISSION_GRANTED;
            }

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

        return false;
    }

    public static boolean checkNotPermission(final String permission) {
        return !checkPermission(permission);
    }

    /**
     * 安装app权限
     * <p>
     * <uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES"/>
     */
    public static boolean canInstallApk() {

        // 8.0以上需要安装apk权限
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            return getPackageManager().canRequestPackageInstalls();
        }
        return true;
    }

    /**
     * 请求安装apk权限
     */
    public static void requestInstallApkPermission() {
        requestPermissions(Manifest.permission.REQUEST_INSTALL_PACKAGES);
    }

    /**
     * 开启安装未知来源权限
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void permitInstallUnknownApk(int resultCode) {

        Uri packageURI = Uri.parse("package:" + getPackageName());
        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
        myActivity.startActivityForResult(intent, resultCode);

    }


    private static void requestPermissions(final String permission) {

        try {

            ActivityUtil.checkActivity(className);

            ActivityCompat.requestPermissions(myActivity, new String[]{permission}, 1);

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

    }

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
     * 获取sdk版本号 api版本号 如 28
     */
    public static int getSDK() {
        return Build.VERSION.SDK_INT;
    }

    /**
     * 固件版本号 如 9.0.0
     */
    public static String getRelease() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获得手机制造商
     */
    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    /**
     * 获得手机型号
     */
    public static String getModel() {
        return Build.MODEL;
    }

    /**
     * 获取cpu架构
     */
    public static String getCPU() {
        return Build.CPU_ABI;
    }

    /**
     * 获取版本名字
     */
    public static String getVersionName() {
        return null == getPackageInfo() ? "" : getPackageInfo().versionName;
    }

    /**
     * 获取版本号
     */
    public static int getVersionCode() {
        return null == getPackageInfo() ? 0 : getPackageInfo().versionCode;
    }

    /**
     * 获得目标文件的版本号 如 需要安装的apk version
     */
    public static int getTargetPackageInfo(String packageName) {

        try {

            ActivityUtil.checkActivity(className);

            // 0 : PackageManager.GET_ACTIVITIES
            return myActivity.getPackageManager().getPackageInfo(packageName, 0).versionCode;

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

        return 0;
    }

    public static int getTargetSdkVersion() {
        return null == getPackageInfo() ? 0 : getPackageInfo().applicationInfo.targetSdkVersion;
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

    /**
     * 隐藏键盘
     */
    public static void hideKeyBoardAction() {

        InputMethodManager inputMethodManager = (InputMethodManager) myActivity.getSystemService(Context.INPUT_METHOD_SERVICE);

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

        try {

            ConnectivityManager connectivityManager = (ConnectivityManager) myActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
            return connectivityManager.getActiveNetworkInfo().isAvailable();

        } catch (Exception e) {
            ShowUtil.print(e);
        }

        return false;

    }

    public static boolean isNotConnected() {
        return !isConnected();
    }

    public static void getNetworkState(ConnectivityManager manager) {

        NetworkInfo.State mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        if (mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING) {
            Toast.makeText(myActivity, "当前为网络状态为mobile", Toast.LENGTH_SHORT).show();
        }

        NetworkInfo.State wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        if (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING) {
            Toast.makeText(myActivity, "当前为网络状态为Wi-Fi", Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * 获取手机IMEI
     * <uses-permission android:name="android.permission.READ_PHONE_STATE" />
     */
    public String getDeviceID() {

        final String deviceID = "0000000000";

        // 在未首先获得 READ_CALL_LOG 权限的情况下，除了应用的用例需要的其他权限之外，
        // 运行于 Android 9 上的应用无法读取电话号码或手机状态
        if (checkNotPermission(Manifest.permission.READ_CALL_LOG) || checkNotPermission(Manifest.permission.READ_PHONE_STATE)) {
            return deviceID;
        }

        TelephonyManager telephonyManager = (TelephonyManager) myActivity.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();

    }

    //=================================================================

    private static final String className = DeviceUtil.class.getName();

    private static float density;
    private static DisplayMetrics displayMetrics;
    private static PackageInfo packageInfo;

    private static DisplayMetrics getDisplayMetrics() {

        if (null == displayMetrics) {
            displayMetrics = myActivity.getResources().getDisplayMetrics();
        }
        return displayMetrics;

    }

    private static float getDensity() {

        if (0 == density) {
            density = getDisplayMetrics().density;
        }
        return density;

    }

    public static PackageManager getPackageManager() {

        try {

            ActivityUtil.checkActivity(className);

            return myActivity.getPackageManager();

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

        return null;

    }

    private static String getPackageName() {

        try {

            ActivityUtil.checkActivity(className);

            return myActivity.getPackageName();

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

        return "";

    }

    private static PackageInfo getPackageInfo() {

        try {

            if (null == packageInfo) {
                // 0 : PackageManager.GET_ACTIVITIES
                packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            }

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

        return packageInfo;

    }
}
