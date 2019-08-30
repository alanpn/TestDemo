package com.wubin.testdemo;

import android.os.Bundle;

import com.example.wubin.baselibrary.activity.BaseActivity;

/**
 * @author wubin
 * @description 强制横屏显示
 * style 设置
 * Manifest 设置 android:screenOrientation="landscape"
 * @date 2019-08-22
 */
public class LandscapeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

    }
}
