package com.wubin.testdemo.animation;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.core.widget.NestedScrollView;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.example.wubin.baselibrary.util.DeviceUtil;
import com.example.wubin.baselibrary.util.ShowUtil;
import com.wubin.testdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wubin
 * @description
 * @date 2019-05-15
 */
public class AnimationScrollActivity extends BaseActivity {

    @BindView(R.id.sv)
    NestedScrollView sv;

    @BindView(R.id.activity_animation_scroll)
    Button btn;

    private int SCREEN_WIDTH = DeviceUtil.getDisplayWidth();
    private int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_animation_scroll);
        ButterKnife.bind(this);

        sv.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {

            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                ShowUtil.print(scrollY + " xx  " + oldScrollY);
                scroll();
            }
        });

    }

    private void scroll() {
        width += 10;
        setViewSize(btn, SCREEN_WIDTH - width);
    }

    public void setViewSize(View view, int width) {

        if (view == null) {
            return;
        }

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);

        view.setLayoutParams(lp);
    }


}
