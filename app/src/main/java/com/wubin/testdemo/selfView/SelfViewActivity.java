package com.wubin.testdemo.selfView;

import android.os.Bundle;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.wubin.testdemo.R;

/**
 * @author wubin
 * @description 自定义view
 * @date 2019-06-17
 */
public class SelfViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.self);

    }
}
