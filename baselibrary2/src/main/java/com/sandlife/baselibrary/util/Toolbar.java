package com.sandlife.baselibrary.util;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sandlife.baselibrary.R;

public class Toolbar {

    private RelativeLayout rl_bg;

    private LinearLayout ll_back;
    private ImageView iv_left;

    private TextView tv_title;

    private LinearLayout ll_right;
    private ImageView iv_right;
    private TextView tv_right;

    private View line;
    private TextView tv_right1;

    private final int LayoutID = R.id.title;

    private View mView;
    private Activity activity;

    public Toolbar(Activity activity) {

        mView = activity.findViewById(LayoutID);
        this.activity = activity;
        initView();

    }

    public Toolbar(Activity activity, View v) {

        mView = v.findViewById(LayoutID);
        this.activity = activity;
        initView();

    }

    private void initView() {

        rl_bg = mView.findViewById(R.id.view_toolbar_bg);
        ll_back = mView.findViewById(R.id.view_toolbar_back);
        iv_left = mView.findViewById(R.id.view_toolbar_left_iv);
        tv_title = mView.findViewById(R.id.view_toolbar_title);
        iv_right = mView.findViewById(R.id.view_toolbar_right_iv);
        tv_right = mView.findViewById(R.id.view_toolbar_right_tv);
        ll_right = mView.findViewById(R.id.view_toolbar_right_ll);
        line = mView.findViewById(R.id.view_toolbar_line);
        tv_right1 = mView.findViewById(R.id.view_toolbar_right_tv1);

    }

    //================================
    // 背景
    //================================

    public void setBackgroundColor(int color) {
        rl_bg.setBackgroundColor(color);
    }

    public RelativeLayout getToolbar() {
        return rl_bg;
    }

    //================================
    // 左边
    //================================

    public LinearLayout getBackView() {
        ll_back.setVisibility(View.VISIBLE);
        return ll_back;
    }

    public void setBackBg(int resID) {

        ll_back.setVisibility(View.VISIBLE);
        iv_left.setBackgroundResource(resID);

    }

    //================================
    // 中间
    //================================

    public void setTitle(String text, int color, float f) {

        tv_title.setText(text);
        tv_title.setTextColor(color);
        tv_title.setTextSize(f);

    }

    public TextView getTitleView() {
        return tv_title;
    }

    //================================
    // 右边
    //================================

    public LinearLayout getRightView() {

        ll_right.setVisibility(View.VISIBLE);
        return ll_right;

    }

    public ImageView getRightImageView() {

        ll_right.setVisibility(View.VISIBLE);
        iv_right.setVisibility(View.VISIBLE);
        return iv_right;

    }

    public TextView getRightTextView() {

        ll_right.setVisibility(View.VISIBLE);
        tv_right.setVisibility(View.VISIBLE);
        return tv_right;

    }

    public TextView getRightTextView1() {
        ll_right.setVisibility(View.VISIBLE);
        tv_right1.setVisibility(View.VISIBLE);
        return tv_right1;
    }

    //================================
    // 底部线条
    //================================

    public void showLine(boolean flag) {
        if (flag) {
            line.setVisibility(View.VISIBLE);
        } else {
            line.setVisibility(View.GONE);
        }
    }

}
