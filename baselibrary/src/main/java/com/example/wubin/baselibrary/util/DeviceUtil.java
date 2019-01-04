package com.example.wubin.baselibrary.util;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.nfc.NfcAdapter;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;

import com.example.wubin.baselibrary.activity.BaseActivity;

import static com.example.wubin.baselibrary.activity.BaseActivity.myActivity;

public class DeviceUtil {

    /**
     * 是否有NFC
     */
    public NfcAdapter hasNFC() {
        return NfcAdapter.getDefaultAdapter(myActivity);
    }

    /**
     * NFC是否开启
     */
    public boolean isNfcEnabled() {

        NfcAdapter adapter = hasNFC();
        if (null == adapter) return false;
        return adapter.isEnabled();

    }

    /**
     * 判断摄像头权限
     */
    public boolean checkCameraPermission() {
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
    public boolean checkReadContact() {
        return checkPermission(Manifest.permission.READ_CONTACTS);
    }

    /**
     * 判断是否有权限
     */
    public boolean checkPermission(final String permission) {

        try {

            // only targetSdkVersion >=23 || android 6.0
            if (ScreenUtil.getSDK() >= 23) {
                return ContextCompat.checkSelfPermission(myActivity, permission) == PackageManager.PERMISSION_GRANTED;
            } else {
                return PermissionChecker.checkSelfPermission(myActivity, permission) == PermissionChecker.PERMISSION_GRANTED;
            }

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

        return false;
    }

    public boolean checkNotPermission(final String permission) {
        return !checkPermission(permission);
    }

    /**
     * 安装app权限
     * <p>
     * <uses-permission android:name="android.permission.REQUEST_INSTALL_PACKAGES"/>
     */
    private boolean installApkPermission() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) { // 8.0以上需要安装apk权限
            return ScreenUtil.getPackageManager().canRequestPackageInstalls();
        }
        return true;
    }

    public boolean isNotInstallApkPermission() {
        return !installApkPermission();
    }

    /**
     * 请求权限
     */
    public void requestinstallApkPermission() {
        requestPermissions(Manifest.permission.REQUEST_INSTALL_PACKAGES);
    }

    private void requestPermissions(final String permission) {
        ActivityCompat.requestPermissions(BaseActivity.myActivity, new String[]{permission}, 1);
    }

    //=================================================================

    private static DeviceUtil util;

    public static DeviceUtil getInstance() {
        if (null == util) util = new DeviceUtil();
        return util;
    }
}
