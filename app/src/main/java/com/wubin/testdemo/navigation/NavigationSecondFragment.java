package com.wubin.testdemo.navigation;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wubin.baselibrary.util.ShowUtil;
import com.wubin.testdemo.R;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author wubin
 * @description
 * @date 2019/4/2
 */
public class NavigationSecondFragment extends Fragment {

    @BindView(R.id.tv)
    TextView tv;

    View mView;

    private final int ID_FIRST = R.id.btn1;
    private final int ID_SECOND = R.id.btn2;

    NavController controller;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (null == mView) {
            mView = inflater.inflate(R.layout.layout_fragment, container, false);
            ButterKnife.bind(this, mView);
            tv.setText("this is secondFragment");

            NavigationActivity.secondFragment = this;

            ShowUtil.print("secondFragment");
        }
        return mView;
    }

    @OnClick({ID_FIRST, ID_SECOND})
    void onClick(View view) {
        switch (view.getId()) {
            case ID_FIRST:
                Navigation.findNavController(view).navigate(R.id.to_thirdFragment);
                break;

            case ID_SECOND:

//                Navigation.findNavController(view).navigate(R.id.to_secondFragment);
                Navigation.findNavController(view).navigateUp();

                break;
        }
    }


}
