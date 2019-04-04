package com.wubin.testdemo.navigation;

import android.os.Bundle;
import android.view.View;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.wubin.testdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wubin
 * @description
 * @date 2019/4/2
 */
public class NavigationActivity extends BaseActivity {

    @BindView(R.id.fragment)
    View fragment;

    public static NavigationFristFragment fristFragment;
    public static NavigationSecondFragment secondFragment;
    public static NavigationThirdFragment thirdFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.navigation);
        ButterKnife.bind(this);

    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment);
//        return NavHostFragment.findNavController(fragment).navigateUp();
//    }


}
