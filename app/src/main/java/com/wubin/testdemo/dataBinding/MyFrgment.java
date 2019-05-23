package com.wubin.testdemo.dataBinding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.wubin.testdemo.R;
import com.wubin.testdemo.databinding.ActivityDataBindingBinding;

/**
 * @author wubin
 * @description
 * @date 2019-05-21
 */
public class MyFrgment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ActivityDataBindingBinding binding = DataBindingUtil.inflate(inflater, R.layout.activity_data_binding, container, false);

        UserPo user = new UserPo("name1", "pass1", 181);
        binding.setUser(user);

        binding.tv.setText("BB");

        return binding.getRoot();
    }
}
