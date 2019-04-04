package com.wubin.testdemo.tabLayout;

import android.os.Bundle;
import androidx.annotation.Nullable;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.wubin.testdemo.R;

import butterknife.ButterKnife;

/**
 * @author wubin
 * @description
 * @date 2019/3/12
 */
public class TabLayouDemoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_tablayout_demo);
        ButterKnife.bind(this);
        init();

    }

    private void init() {

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.layout_tablayout_demo_fl, new TabLayoutItemFragment())
                .commitAllowingStateLoss();

    }


}
