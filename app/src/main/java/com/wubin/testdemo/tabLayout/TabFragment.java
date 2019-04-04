package com.wubin.testdemo.tabLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.wubin.testdemo.R;

import androidx.fragment.app.Fragment;

/**
 * Created by Administrator on 2018/7/20.
 */
public class TabFragment extends Fragment {

    private View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (null == mView) {
            mView = LayoutInflater.from(BaseActivity.myActivity).inflate(R.layout.fragment_tablayout, container, false);
        }
        return mView;
    }
}
