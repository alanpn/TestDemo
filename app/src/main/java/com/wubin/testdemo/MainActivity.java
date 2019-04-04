package com.wubin.testdemo;

import android.os.Bundle;
import android.view.View;

import com.example.wubin.aroutermodule.ARouterActivity;
import com.example.wubin.baselibrary.activity.BaseActivity;
import com.example.wubin.baselibrary.util.IntentUtil;
import com.example.wubin.flexboxlayoulibrary.FlexboxActivity;
import com.example.wubin.litepalmodule.LitePalActivity;

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

//                IntentUtil.startActivity(JniActivity.class);
                IntentUtil.startActivity(FlexboxActivity.class);

                break;

            case ID_TEST2:

//                IntentUtil.getUtil().startActivity(TestActivity.class);
//                IntentUtil.startActivity(WebViewActivity.class);
//                IntentUtil.startActivity(SpeakerActivity.class);

//                IntentUtil.startActivity(CircleViewTestActivity.class);
//                IntentUtil.startActivity(DrawableActivity.class);
//                IntentUtil.startActivity(AnimationActivity.class);
//                IntentUtil.startActivity(TabLayouActivity.class);
//                IntentUtil.startActivity(TabLayouDemoActivity.class);
//                IntentUtil.startActivity(EventBusFristActivity.class);

                IntentUtil.startActivity(ARouterActivity.class);

//                IntentUtil.startActivity(LitePalActivity.class);

//                IntentUtil.startActivity(NavigationActivity.class);

                break;

            case ID_TEST3:

                IntentUtil.startActivity(LitePalActivity.class);

                break;

        }

    }


}
