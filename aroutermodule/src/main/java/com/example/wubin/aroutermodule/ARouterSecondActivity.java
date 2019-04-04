package com.example.wubin.aroutermodule;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.wubin.baselibrary.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wubin
 * @description
 * @date 2019/3/27
 */

@Route(path = "/aroute/second")
public class ARouterSecondActivity extends BaseActivity {

    @BindView(R2.id.activity_event_bus_tv)
    TextView tv;

    @Autowired
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_event_bus);

        ButterKnife.bind(this);

        ARouter.getInstance().inject(this);

        tv.setText(name);

    }


}
