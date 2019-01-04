package com.wubin.testdemo;

import android.os.Bundle;
import android.view.View;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.example.wubin.baselibrary.util.IntentUtil;
import com.wubin.testdemo.jni.JniActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private final int ID_JNI = R.id.activity_main_jni;
    private final int ID_TEST = R.id.activity_main_test;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

    }


    @OnClick({ID_JNI, ID_TEST})
    public void onClick(View view) {

        switch (view.getId()) {

            case ID_JNI:
                IntentUtil.startActivity(JniActivity.class);
                break;

            case ID_TEST:
//                IntentUtil.getUtil().startActivity(TestActivity.class);
//                IntentUtil.startActivity(WebViewActivity.class);
                IntentUtil.startActivity(SpeakerActivity.class);

//                IntentUtil.startActivity(MyFragmentActivity.class);
//                IntentUtil.startActivity(MyFragmentActivity.class);
                break;

        }

    }


}
