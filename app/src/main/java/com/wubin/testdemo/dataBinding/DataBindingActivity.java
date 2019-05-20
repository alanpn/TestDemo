package com.wubin.testdemo.dataBinding;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.wubin.testdemo.R;
import com.wubin.testdemo.databinding.ActivityDataBindingBinding;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author wubin
 * @description
 * @date 2019-05-20
 */
public class DataBindingActivity extends BaseActivity {

    UserPo user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityDataBindingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
        user = new UserPo("Test", "User");
        binding.setUser(user);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.activity_data_binding_btn)
    void onClick(View view) {
        user.setName("sdfsdfsd");
    }
}
