package com.example.wubin.dialogplusmodule.util;

import android.view.Gravity;
import android.widget.BaseAdapter;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.DialogPlusBuilder;
import com.orhanobut.dialogplus.GridHolder;
import com.orhanobut.dialogplus.ListHolder;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.orhanobut.dialogplus.ViewHolder;

public class DialogplusUtil {

    public static void showViewDialog(BaseAdapter adapter, int viewResourceId, int headResourceId, int footerResourceId, OnClickListener onClickListener, OnItemClickListener onItemClickListener) {
        showDialog(adapter, TYPE_VIEW, viewResourceId, 0, headResourceId, footerResourceId, onClickListener, onItemClickListener);
    }

    public static void showlistViewDialog(BaseAdapter adapter, int headResourceId, int footerResourceId, OnClickListener onClickListener, OnItemClickListener onItemClickListener) {
        showDialog(adapter, TYPE_LISTVIEW, 0, 0, headResourceId, footerResourceId, onClickListener, onItemClickListener);
    }

    public static void showGridViewDialog(BaseAdapter adapter, int columnNumber, int headResourceId, int footerResourceId, OnClickListener onClickListener, OnItemClickListener onItemClickListener) {
        showDialog(adapter, TYPE_GRIDVIEW, 0, columnNumber, headResourceId, footerResourceId, onClickListener, onItemClickListener);
    }

    //===========================

    private static DialogPlusBuilder builder;

    private static final int TYPE_VIEW = 1;
    private static final int TYPE_LISTVIEW = 2;
    private static final int TYPE_GRIDVIEW = 3;

    /**
     * 显示DialogPlus对话框
     *
     * @param hoderType        显示那种类型
     * @param viewResourceId   ViewHolder的页面
     * @param columnNumber     一行几列
     * @param headResourceId   头部页面
     * @param footerResourceId 底部页面
     */
    private static void showDialog(BaseAdapter adapter, int hoderType, int viewResourceId, int columnNumber, int headResourceId, int footerResourceId, OnClickListener onClickListener, OnItemClickListener onItemClickListener) {

        builder = DialogPlus.newDialog(BaseActivity.myActivity);

        if (headResourceId <= 0) {
            builder.setHeader(headResourceId);
        }

        if (footerResourceId <= 0) {
            builder.setFooter(footerResourceId);
        }

        switch (hoderType) {

            case TYPE_VIEW:

                if (null != adapter) {
                    builder.setContentHolder(new ViewHolder(viewResourceId));
                    builder.setAdapter(adapter);
                }

                break;

            case TYPE_LISTVIEW:

                if (null != adapter) {
                    builder.setContentHolder(new ListHolder());
                    builder.setAdapter(adapter);
                }

                break;

            case TYPE_GRIDVIEW:

                if (null != adapter) {
                    builder.setContentHolder(new GridHolder(columnNumber));
                    builder.setAdapter(adapter);
                }

                break;
        }

        if (null != onItemClickListener) {
            builder.setOnItemClickListener(onItemClickListener);
        }

        if (null != onClickListener) {
            builder.setOnClickListener(onClickListener);
        }

        builder.setGravity(Gravity.BOTTOM).create().show();

    }

}
