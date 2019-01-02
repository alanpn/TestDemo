package com.sandlife.baselibrary.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.nfc.NfcAdapter;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.sandlife.baselibrary.activity.BaseActivity;
import com.sandlife.baselibrary.widget.MaterialDialog;

import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by wubin on 2016/8/23.
 */
public class Util {

    // 日志Tag
    private static String TAG = "Sand";
    // 日志打印开关
    private static boolean print = true;

    public static void print(String msg) {
        if (print) {
            Log.v(TAG, msg);
        }
    }

    public static void print(Object obj) {
        print(obj.toString());
    }

    public static void sendToast(Activity act, String info) {
        BaseActivity.showAlertDialog(act, info);
    }


    public static int dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int px2Dp(Context context, float px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }


    public static int getDisplayHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();

        wm.getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    public static int getDisplayWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();

        wm.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    public static void setViewWith(View view, int width) {
        setViewSize(view, width, -1);
    }

    public static void setViewHeight(View view, int height) {
        setViewSize(view, -1, height);
    }

    public static void setViewSize(View view, int width, int height) {

        if (view == null) {
            return;
        }

        ViewGroup.LayoutParams lp = view.getLayoutParams();

        if (width != -1) {
            lp.width = width;
        }
        if (height != -1) {
            lp.height = height;
        }
        view.setLayoutParams(lp);
    }

    public static void setViewSize(View view, int width) {

        if (view == null) {
            return;
        }

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);

        view.setLayoutParams(lp);
    }

    /**
     * NFC
     */
    public static boolean isNFC(final Activity mAct) {
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(mAct);

        if (nfcAdapter != null) {
            if (!nfcAdapter.isEnabled()) {
                final MaterialDialog mDialog = new MaterialDialog(mAct);
                mDialog.setTitle("提示");
                mDialog.setMessage("请打开手机NFC选项以体验完整功能");
                mDialog.setPositiveButton("设置",
                        new View.OnClickListener() {
                            public void onClick(View v) {
                                mDialog.dismiss();

//                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                                    Intent intent = new Intent(Settings.ACTION_NFC_SETTINGS);
//                                    mAct.startActivity(intent);
//                                } else {
                                Intent intent = new Intent(Settings.ACTION_SETTINGS);
                                mAct.startActivity(intent);
                                //}
                            }
                        }).show();
            } else {
                return true;
            }
        } else {
            final MaterialDialog mDialog1 = new MaterialDialog(mAct);
            mDialog1.setTitle("提示")
                    .setMessage("您的手机设备不支持NFC")
                    .setPositiveButton("确定",
                            new View.OnClickListener() {
                                public void onClick(View v) {
                                    mDialog1.dismiss();
                                }
                            }).show();
            return false;
        }

        return false;
    }

    /**
     * 隐藏键盘
     */
    public static void hidKeyboard(Activity act) {
        try {
            InputMethodManager imm = ((InputMethodManager) act.getSystemService(Context.INPUT_METHOD_SERVICE));
            if (imm.isActive()) {
                View view = act.getCurrentFocus();
                if (view != null) {
                    imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置listview 高度
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) { // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

    public static void setGridViewHeightBasedOnChildren(GridView gridView) {
        // 获取GridView对应的Adapter
        ListAdapter listAdapter = gridView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int rows;
        int columns = 0;
        int horizontalBorderHeight = 0;
        Class<?> clazz = gridView.getClass();
        try {
            //利用反射，取得每行显示的个数
            Field column = clazz.getDeclaredField("mRequestedNumColumns");
            column.setAccessible(true);
            columns = (Integer) column.get(gridView);
            //利用反射，取得横向分割线高度
            Field horizontalSpacing = clazz.getDeclaredField("mRequestedHorizontalSpacing");
            horizontalSpacing.setAccessible(true);
            horizontalBorderHeight = (Integer) horizontalSpacing.get(gridView);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //判断数据总数除以每行个数是否整除。不能整除代表有多余，需要加一行
        if (listAdapter.getCount() % columns > 0) {
            rows = listAdapter.getCount() / columns + 1;
        } else {
            rows = listAdapter.getCount() / columns;
        }
        int totalHeight = 0;
        for (int i = 0; i < rows; i++) { //只计算每项高度*行数
            View listItem = listAdapter.getView(i, null, gridView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
        }
        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight + horizontalBorderHeight * (rows - 1);//最后加上分割线总高度
        gridView.setLayoutParams(params);
    }

    public static void setGridViewHeightBasedOnChildren(GridView gridView, int special) {
        // 获取GridView对应的Adapter
        ListAdapter listAdapter = gridView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int rows;
        int columns = 0;
        int horizontalBorderHeight = 0;
        Class<?> clazz = gridView.getClass();
        try {
            //利用反射，取得每行显示的个数
            Field column = clazz.getDeclaredField("mRequestedNumColumns");
            column.setAccessible(true);
            columns = (Integer) column.get(gridView);
            //利用反射，取得横向分割线高度
            Field horizontalSpacing = clazz.getDeclaredField("mRequestedHorizontalSpacing");
            horizontalSpacing.setAccessible(true);
            horizontalBorderHeight = (Integer) horizontalSpacing.get(gridView);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //判断数据总数除以每行个数是否整除。不能整除代表有多余，需要加一行
        if (listAdapter.getCount() % columns > 0) {
            rows = listAdapter.getCount() / columns + 1;
        } else {
            rows = listAdapter.getCount() / columns;
        }
        int totalHeight = 0;
        for (int i = 0; i < rows; i++) { //只计算每项高度*行数
            View listItem = listAdapter.getView(i, null, gridView);
            listItem.measure(0, 0); // 计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
        }
        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight + (horizontalBorderHeight + special) * (rows - 1);//最后加上分割线+间距总高度
        gridView.setLayoutParams(params);
    }

    /**
     * 实现密码散列
     *
     * @param text
     * @return
     */
    public static String SHA1Digest(String text) {
        String pwd = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.update(text.getBytes());
            byte resultData[] = md.digest();
            pwd = bytesToHexString(resultData);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return pwd;
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 判断网络
     *
     * @param mActivity
     * @return
     */
    public static boolean isConnectInternet(Activity mActivity) {
        ConnectivityManager conManager = (ConnectivityManager) mActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conManager.getActiveNetworkInfo();
        if (networkInfo != null) { // 注意，这个判断一定要的哦，要不然会出错
            return networkInfo.isAvailable();
        }
        return false;
    }

    /**
     * 获取版本的名字
     *
     * @param ctx
     * @return
     */
    public static String getVersionName(Context ctx) {
        PackageInfo pinfo;
        try {
            pinfo = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
        } catch (Exception e) {
            return "4.0.2";
        }
        return pinfo.versionName;
    }

    public static String getVersionCode(Context ctx) {
        PackageInfo pinfo;
        try {
            pinfo = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0);
        } catch (Exception e) {
            return "4020";
        }
        return pinfo.versionCode + "";
    }

}
