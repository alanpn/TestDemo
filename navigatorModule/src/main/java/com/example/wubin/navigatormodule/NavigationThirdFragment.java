package com.example.wubin.navigatormodule;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wubin.baselibrary.util.ShowUtil;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author wubin
 * @description
 * @date 2019/4/2
 */
public class NavigationThirdFragment extends Fragment {

    @BindView(R2.id.tv)
    TextView tv;

    View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (null == mView) {
            mView = inflater.inflate(R.layout.layout_fragment, container, false);
            ButterKnife.bind(this, mView);
            tv.setText("this is thirdFragment");

            NavigationActivity.thirdFragment = this;

            ShowUtil.print("thirdFragment");
        }
        return mView;
    }

    @OnClick({R2.id.btn1, R2.id.btn2})
    void onClick(View view) {

        int i = view.getId();

        if (i == R.id.btn1) {

            Navigation.findNavController(view).navigate(R.id.to_firstFragment);

        } else if (i == R.id.btn2) {

            Navigation.findNavController(view).navigateUp();

        }
    }


}
