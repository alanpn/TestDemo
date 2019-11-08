package com.wubin.testdemo.SimpleViewModel;

import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.example.wubin.baselibrary.util.ShowUtil;
import com.wubin.testdemo.viewModel.UserPo;

import java.util.List;

/**
 * @author wubin
 * @description
 * @date 2019-11-05
 */
public class SimpleViewModelActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyViewModel viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        viewModel.getUsers().observe(this, new Observer<List<UserPo>>() {
            @Override
            public void onChanged(List<UserPo> userPos) {
                ShowUtil.print(userPos);
            }
        });

    }
}
