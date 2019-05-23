package com.wubin.testdemo.viewModel;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.example.wubin.baselibrary.util.ShowUtil;
import com.example.wubin.baselibrary.util.StringUtil;
import com.wubin.testdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author wubin
 * @description
 * @date 2019-05-21
 */
public class ViewModelActivity extends BaseActivity {

    @BindView(R.id.et)
    EditText et;

    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_model);

        ButterKnife.bind(this);

        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory()).get(LoginViewModel.class);

        loginViewModel.getLoginFromState().observe(this, new Observer<LoginFromState>() {
            @Override
            public void onChanged(LoginFromState state) {
                if (null == state) return;

                String error = state.getError();
                if (StringUtil.isNotBlank(error)) {
                    et.setError(error);
                } else {
                    ShowUtil.toastShow(state.getSuccess());
                }
            }
        });

    }

    @OnClick(R.id.btn)
    void onClick(View view) {
        loginViewModel.login(et.getText().toString());
    }

}
