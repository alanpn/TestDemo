package com.wubin.testdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.example.wubin.baselibrary.widget.AppBarStateChangeListener;
import com.google.android.material.appbar.AppBarLayout;

/**
 * @author wubin
 * @description
 * @date 2019-05-13
 */
public class TestActivity extends BaseActivity {


    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test);

        AppBarLayout appBarLayout = findViewById(R.id.app_bar);

        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.COLLAPSED) {
                    //折叠状态
                    toolbar.getNavigationIcon().setVisible(false, true);
                } else {
                    toolbar.getNavigationIcon().setVisible(true, true);
                }
            }
        });


        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("标题");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);

        //点击左边返回按钮监听事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
