package com.example.wubin.aroutermodule;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.wubin.baselibrary.activity.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author wubin
 * @description
 * @date 2019/3/27
 */
public class ARouterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_arouter);

        ButterKnife.bind(this);
    }

    @OnClick(R2.id.activity_event_bus_btn)
    void onClick(View v) {

//        ARouter.getInstance().build("/aroute/second").navigation();
//        ARouter.getInstance().build("/aroute/second").withString("name", "123").navigation(this, 5); // startActivityForResult

//        ARouter.getInstance().build("/aroute/second")
//                .withString("name", "123")
//                .navigation(this);

        ARouterFragment fragment = (ARouterFragment) ARouter.getInstance().build("/aroute/fragment").navigation();
        getSupportFragmentManager().beginTransaction().add(R.id.activity_event_bus_fl, fragment).commitAllowingStateLoss();

//        ARouter.getInstance().build("/aroute/second").navigation(this, new NavigationCallback() {
//            @Override
//            public void onFound(Postcard postcard) {
//                // 路由目标被发现时调用
//            }
//
//            @Override
//            public void onLost(Postcard postcard) {
//                // 路由被丢失时
//            }
//
//            @Override
//            public void onArrival(Postcard postcard) {
//                // 路由到达以后
//            }
//
//            @Override
//            public void onInterrupt(Postcard postcard) {
//                // 路由被拦截时
//            }
//        });

    }
}
