package com.wubin.testdemo.drawable;

import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.wubin.testdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DrawableActivity extends BaseActivity {

    private final int ID_TRANSITION = R.id.activity_drawable_transition;
    @BindView(ID_TRANSITION)
    TextView tv;

    TransitionDrawable td;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_drawable);
        ButterKnife.bind(this);

    }

    @OnClick(ID_TRANSITION)
    public void onClick(View view) {
        transition();
    }

    /**
     * 实现两个Drawable之间的淡入淡出
     */
    void transition() {
        if (null == td) td = (TransitionDrawable) tv.getBackground();
        td.startTransition(5000);
    }
}
