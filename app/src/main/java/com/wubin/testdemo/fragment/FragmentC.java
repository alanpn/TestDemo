package com.wubin.testdemo.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wubin.testdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragmentC extends Fragment {

    private View mView;

    @BindView(R.id.fragment_tablayout_tv)
    TextView tv;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (null == mView) {

            mView = inflater.inflate(R.layout.fragment_tablayout, container, false);
            ButterKnife.bind(this, mView);

            tv.setText("fragmentC");

        }

        return mView;
    }

    @OnClick(R.id.fragment_tablayout_btn)
    void onClick(View view) {

        Activity activity = getActivity();
        if (activity instanceof MyFragmentActivity) {
            ((MyFragmentActivity) getActivity()).startFragmentA();
        } else {
            ((MyFragmentActivity2) getActivity()).startFragmentA();
        }

    }
}
