package com.example.wubin.eventbus;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.example.wubin.baselibrary.util.IntentUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

/**
 * @author wubin
 * @description
 * @date 2019/3/27
 */
public class EventBusFristActivity extends BaseActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_event_bus);

        ButterKnife.bind(this);

        EventBus.getDefault().register(this);

        init();

    }

    private void init() {
        tv = findViewById(R.id.activity_event_bus_tv);

        findViewById(R.id.activity_event_bus_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtil.startActivity(EventBusSecondActivity.class);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(LoginEvent event) {
        if (event.what == 1) {
            tv.setText(event.msg);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent event) {
        if (event.what == 1) {
            tv.setText(event.msg);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

}
