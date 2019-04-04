package com.wubin.testdemo.arouter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wubin.testdemo.R;

import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wubin
 * @description
 * @date 2019/3/28
 */

@Route(path = "/aroute/fragment")
public class ARouterFragment extends Fragment {

    @BindView(R.id.activity_event_bus_tv)
    TextView tv;

    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (null == mView) {
            mView = inflater.inflate(R.layout.activity_arouter, container, false);
            ButterKnife.bind(this, mView);
            init();
        }
        return mView;
    }

    /**
     * 初始化
     */
    private void init() {

        tv.setText(ARouterFragment.class.getName());

    }

}