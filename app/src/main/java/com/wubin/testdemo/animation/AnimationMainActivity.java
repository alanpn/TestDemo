package com.wubin.testdemo.animation;

import android.os.Bundle;
import android.view.View;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.example.wubin.baselibrary.util.IntentUtil;
import com.wubin.testdemo.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author wubin
 * @description
 * @date 2019-06-11
 */
public class AnimationMainActivity extends BaseActivity {

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

                IntentUtil.startActivity(AnimationActivity.class);

                break;

            case ID_TEST2:

                IntentUtil.startActivity(AnimationLayoutActivity.class);

                break;

            case ID_TEST3:

                IntentUtil.startActivity(AnimationScrollActivity.class);

                break;

            case ID_TEST4:

                IntentUtil.startActivity(AnimationToolbarActivity.class);

                break;

            case ID_TEST5:

//                IntentUtil.startActivity(RecyclerViewActivity.class);

                break;

        }

    }
}
