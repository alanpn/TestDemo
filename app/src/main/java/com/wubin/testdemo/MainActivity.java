package com.wubin.testdemo;

import android.os.Bundle;
import android.view.View;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.example.wubin.baselibrary.util.ActivityUtil;
import com.wubin.testdemo.animation.AnimationMainActivity;
import com.wubin.testdemo.jd.SearchActivity;
import com.wubin.testdemo.recyclerView.RecyclerViewActivity;
import com.wubin.testdemo.selfView.SelfViewActivity;

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

                ActivityUtil.startActivity(AnimationMainActivity.class);

                break;

            case ID_TEST2:

                ActivityUtil.startActivity(SelfViewActivity.class);

                break;

            case ID_TEST3:

                ActivityUtil.startActivity(SearchActivity.class);

                break;

            case ID_TEST4:

                ActivityUtil.startActivity(RecyclerViewActivity.class);

                break;

            case ID_TEST5:

                ActivityUtil.startActivity(TestActivity.class);

                break;

        }

    }


}
