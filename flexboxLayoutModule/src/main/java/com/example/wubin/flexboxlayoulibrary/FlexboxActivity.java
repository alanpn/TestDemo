package com.example.wubin.flexboxlayoulibrary;

import android.os.Bundle;
import android.view.View;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.flexbox.JustifyContent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author wubin
 * @description
 * @date 2019/3/29
 */
public class FlexboxActivity extends BaseActivity {

    @BindView(R2.id.fl)
    FlexboxLayout fl;

    @BindView(R2.id.fl_1)
    FlexboxLayout fl1;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        ButterKnife.bind(this);

    }

    @OnClick({R2.id.flex_start, R2.id.flex_end, R2.id.center, R2.id.space_around, R2.id.space_between})
    void onClick(View view) {

        id = view.getId();

        if (R.id.flex_start == id) {

            fl.setJustifyContent(JustifyContent.FLEX_START);

        } else if (R.id.flex_end == id) {

            fl.setJustifyContent(JustifyContent.FLEX_END);

        } else if (R.id.center == id) {

            fl.setJustifyContent(JustifyContent.CENTER);

        } else if (R.id.space_around == id) {

            fl.setJustifyContent(JustifyContent.SPACE_AROUND);

        } else if (R.id.space_between == id) {

            fl.setJustifyContent(JustifyContent.SPACE_BETWEEN);

        }

    }

    @OnClick({R2.id.btn_row, R2.id.btn_row_reverse, R2.id.btn_column, R2.id.btn_column_reverse})
    void onClick1(View view) {

        id = view.getId();

        if (R.id.btn_row == id) {

            fl1.setFlexDirection(FlexDirection.ROW);

        } else if (R.id.btn_row_reverse == id) {

            fl1.setFlexDirection(FlexDirection.ROW_REVERSE);

        } else if (R.id.btn_column == id) {

            fl1.setFlexDirection(FlexDirection.COLUMN);

        } else if (R.id.btn_column_reverse == id) {

            fl1.setFlexDirection(FlexDirection.COLUMN_REVERSE);

        }

    }

    @OnClick({R2.id.btn_wap, R2.id.btn_wap_reverse, R2.id.btn_nowap})
    void onClick2(View view) {

        id = view.getId();

        if (R.id.btn_wap == id) {

            fl1.setFlexWrap(FlexWrap.WRAP);

        } else if (R.id.btn_wap_reverse == id) {

            fl1.setFlexWrap(FlexWrap.WRAP_REVERSE);

        } else if (R.id.btn_nowap == id) {

            fl1.setFlexWrap(FlexWrap.NOWRAP);

        }


    }

}
