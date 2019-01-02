package com.sandlife.baselibrary.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.listener.single.BasePermissionListener;
import com.sandlife.baselibrary.util.EventUtil;
import com.sandlife.baselibrary.util.ToastUtil;
import com.sandlife.baselibrary.util.Toolbar;
import com.sandlife.baselibrary.util.Util;
import com.sandlife.baselibrary.widget.CustomProgressDialog;
import com.sandlife.baselibrary.widget.MaterialDialog;

public class BaseActivity extends AppCompatActivity {

    public static BaseActivity myActivity;
    public static MaterialDialog mDialog;
    public static CustomProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myActivity = this;

    }

    @Override
    protected void onResume() {
        super.onResume();
        myActivity = this;
    }

//  =============================
//  ui相关、Perference
//  =============================

    public static Toolbar getToolbar(Activity mActivity) {
        return new Toolbar(mActivity);
    }

    public static Toolbar getToolbar(Activity mActivity, View view) {
        return new Toolbar(mActivity, view);
    }

    public static void savePerference(String key, String value) {
        myActivity.getApplicationContext().getSharedPreferences("initAppState", MODE_PRIVATE).edit().putString(key, value).apply();
    }

    public static String getPerference(String key) {
        return myActivity.getApplicationContext().getSharedPreferences("initAppState", MODE_PRIVATE).getString(key, "");
    }

    public static void saveAppStatePerference(String key, Boolean value) {
        myActivity.getApplicationContext().getSharedPreferences("initAppState", MODE_PRIVATE).edit().putBoolean(key, value).apply();
    }

    /**
     * 开启摄像头权限
     */
    public static boolean checkCameraPermission() {
        try {
            Camera camera = Camera.open();
            camera.release();
            camera = null;

            return true;
        } catch (Exception e) {

            BaseActivity.showAlertDialog("请开启摄像头权限");

            Dexter.withActivity(myActivity)
                    .withPermission(Manifest.permission.CAMERA)
                    .withListener(new BasePermissionListener()).check();

        }

        return false;
    }

    /**
     * 读取联系人权限
     */
    public static boolean checkReadContactsPermission() {

        String permission = Manifest.permission.READ_CONTACTS;

        boolean flag = checkPermission(permission, "请开启读取联系人权限");

        if (flag) {
            return true;
        }

        Dexter.withActivity(myActivity)
                .withPermission(Manifest.permission.READ_CONTACTS)
                .withListener(new BasePermissionListener()).check();

        return false;

    }

    public static boolean checkPermission(String permission, String info) {
        if (checkPermission(permission)) {
            return true;
        } else {
            showAlertDialog(myActivity, info);
            return false;
        }
    }

    @SuppressLint("NewApi")
    public static boolean checkPermission(String permission) {
        boolean result = false;

        try {
            // 6.0以上版本要单独判断
            if (Build.VERSION.SDK_INT >= 23) {
                result = myActivity.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
            } else {
                result = PermissionChecker.checkSelfPermission(myActivity, permission) == PermissionChecker.PERMISSION_GRANTED;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    @SuppressLint("NewApi")
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 10010: // 未知应用来源权限
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    paySPS.getDetection(this); // 无法安装 还是需要重新点击
                } else {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
                    startActivityForResult(intent, 10012);
                }
                break;
        }

    }

    /**
     * 是否可以点击
     */
    public static boolean isClickable() {
        return !EventUtil.eventCount(1300);
    }

    public static boolean isClickable(int time) {
        return !EventUtil.eventCount(time);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    /**
     * 显示对话框
     */
    public static void showAlertDialog(String msg) {
        showAlertDialog(myActivity, msg);
    }

    /**
     * showAlertDialog
     *
     * @param act
     * @param msg
     */
    public static void showAlertDialog(Activity act, String msg) {
       /* dismissDialog();
        dismissAlertDialog();

        mDialog = new MaterialDialog(act);
        mDialog.setMessage(msg).setCanceledOnTouchOutside(true);
        mDialog.show();*/

        dismissDialog();
        if (!TextUtils.isEmpty(msg)) {
            ToastUtil.showToast(myActivity, msg);
        }

    }

    /**
     * 关闭消息框
     */
    public static void dismissAlertDialog() {
        try {
            if (null != mDialog && null != myActivity && !myActivity.isFinishing()) {
                mDialog.dismiss();
                mDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示进度条
     */
    public static void showProgressDialog(Activity activity, String msg) {
        if (null != pDialog) {
            pDialog.dismiss();
        }
        if (!activity.isFinishing()) {
            pDialog = new CustomProgressDialog(activity, msg);
            pDialog.show();
        }
    }

    public static void showProgressDialog(Activity activity) {
        showProgressDialog(activity, "正在加载中...");
    }

    public static void showProgressDialog() {
        showProgressDialog(myActivity);
    }

    public static void showProgressDialog(boolean cancelable) {
        showProgressDialog();

        try {
            pDialog.setCancelable(cancelable);
            pDialog.setCanceledOnTouchOutside(cancelable);
        } catch (Exception e) {
        }
    }

    public static void dismissDialog() {
        try {
            if (null != pDialog && null != myActivity && !myActivity.isFinishing()) {
                pDialog.dismiss();
                pDialog = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static boolean checkNetWork(Activity act) {
        if (Util.isConnectInternet(act)) {
            return true;
        } else {
            showNetError(act);
            return false;
        }
    }

    public static void showErrorMessage(Activity act) {
        showAlertDialog(act, "稍后重试");
    }

    private static void showNetError(Activity act) {
        showAlertDialog("抱歉当前无网络");
    }

}
