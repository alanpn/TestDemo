package com.wubin.testdemo;

import android.os.Bundle;
import android.view.View;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.example.wubin.baselibrary.util.IntentUtil;
import com.wubin.testdemo.NestedScrollParentView.NestedScrollParentViewActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    private final int ID_TEST1 = R.id.activity_main_test1;
    private final int ID_TEST2 = R.id.activity_main_test2;
    private final int ID_TEST3 = R.id.activity_main_test3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

    }

    @OnClick({ID_TEST1, ID_TEST2, ID_TEST3})
    public void onClick(View view) {

        switch (view.getId()) {

            case ID_TEST1:

                IntentUtil.startActivity(NestedScrollParentViewActivity.class);

                break;

            case ID_TEST2:


                break;

            case ID_TEST3:


                break;

        }

    }


}
