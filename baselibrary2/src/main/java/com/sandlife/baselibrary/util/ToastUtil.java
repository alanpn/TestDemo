package com.sandlife.baselibrary.util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sandlife.baselibrary.R;

public class ToastUtil {
    private static String oldMsg;
    protected static Toast toast = null;
    private static long oneTime = 0;
    private static long twoTime = 0;

    private static View view;
    private static TextView tv;

    public static void showToast(Context context, int resId) {
        showToast(context, context.getString(resId));
    }

    public static void showToast(Context context, int resId, int gravity) {
        showToast(context, context.getString(resId), gravity, 0, 0);
    }

    public static void showToast(Context context, String s, int gravity) {
        showToast(context, s, gravity, 0, 0);
    }

    public static void showToast(Context context, int resId, int gravity, int offX, int offY) {
        showToast(context, context.getString(resId), gravity, offX, offY);
    }

    public static void showToast(Context context, String s) {
        showToast(context,s,Gravity.CENTER,0,0);
    }


    public static void showToast(Context context, String s, int gravity, int offX, int offY) {
        if (toast == null) {

            view = LayoutInflater.from(context).inflate(R.layout.toast_bg, null);
            tv = (TextView) view.findViewById(R.id.txt_msg);
            tv.setText(s);

            toast=new Toast(context);
            toast.setView(view);
            toast.setGravity(gravity,0,0);
            toast.setDuration(Toast.LENGTH_SHORT);

            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            if (s.equals(oldMsg)) {
                if (twoTime - oneTime > Toast.LENGTH_SHORT) {
                    toast.show();
                }
            } else {
                oldMsg = s;

                tv.setText(s);
                toast.setView(view);
                toast.show();
            }
        }
        oneTime = twoTime;
    }

}