package com.example.wubin.eventbus;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.wubin.baselibrary.activity.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

/**
 * @author wubin
 * @description
 * @date 2019/3/27
 */
public class EventBusThirdActivity extends BaseActivity {

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
                LoginEvent event = new LoginEvent();
                event.msg = "xxx";
                event.what = 1;
                EventBus.getDefault().post(event);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent event) {
        if (event.what == 3) {
            tv.setText(event.msg);
        }
    }
}
