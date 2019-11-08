package com.wubin.testdemo.dataBinding;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.example.wubin.baselibrary.util.ShowUtil;
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

        // ActivityDataBindingBinding 通过layout名字生成
        // 已经包含 setContentView 不需要再单独设置
        ActivityDataBindingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);

        binding.setName("xxx");

        user = new UserPo("name", "pass", 18);
        binding.setUser(user);

        // 通过id设值
        binding.tv.setText("AA");

        binding.tv2.setText("234234");

        ButterKnife.bind(this);

        getSupportFragmentManager().beginTransaction().add(R.id.fl, new MyFrgment()).commitAllowingStateLoss();

    }

    @OnClick(R.id.activity_data_binding_btn)
    void onClick(View view) {
        user.setName("sdfsdfsd");
    }


    /**
     *
     * @param view
     * @param str
     */
    @BindingAdapter("android:text")
    public static void setText(TextView view, String str) {
        ShowUtil.print(str);
        view.setText(str);
    }


}
