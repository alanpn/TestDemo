package com.wubin.testdemo;

import android.os.Bundle;
import android.view.View;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.example.wubin.baselibrary.util.ActivityUtil;
import com.wubin.testdemo.banner.BannerActivity;
import com.wubin.testdemo.eraser.EraserViewActivity;
import com.wubin.testdemo.materialDesign.FullscreenActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private final int ID_TEST1 = R.id.activity_main_test1;
    private final int ID_TEST2 = R.id.activity_main_test2;
    private final int ID_TEST3 = R.id.activity_main_test3;
    private final int ID_TEST4 = R.id.activity_main_test4;
    private final int ID_TEST5 = R.id.activity_main_test5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

    }

    @OnClick({ID_TEST1, ID_TEST2, ID_TEST3, ID_TEST4, ID_TEST5})
    void onClick(View view) {

        switch (view.getId()) {

            case ID_TEST1:

                ActivityUtil.startActivity(SystemWidgetActivity.class);

                break;

            case ID_TEST2:

                ActivityUtil.startActivity(BannerActivity.class);

                break;

            case ID_TEST3:

                ActivityUtil.startActivity(EraserViewActivity.class);

                break;

            case ID_TEST4:

                ActivityUtil.startActivity(FullscreenActivity.class);

                break;

            case ID_TEST5:

                ActivityUtil.startActivity(LandscapeActivity.class);

                break;

        }

    }

}
