package com.wubin.testdemo;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.example.wubin.baselibrary.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wubin
 * @description
 * @date 2019-08-19
 */
public class SystemWidgetActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_system_widget);
        ButterKnife.bind(this);

        init();

    }

    private void init() {

        initAutoCompleteTextView();
        initImageView();

    }

    //================================
    //
    //================================

    @BindView(R.id.autoComplete)
    AutoCompleteTextView autoCompleteTextView;

    private void initAutoCompleteTextView() {

        //    android:completionThreshold：指定用户至少输入多少个字符才会显示提示 默认2个

        List<String> data = new ArrayList<>();
        data.add("张一 1311234");
        data.add("张二 1311234");
        data.add("王一 1411234");
        data.add("李一 1511234");
        data.add("赵一 1611234");
        data.add("孙一 1322511");
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        autoCompleteTextView.setAdapter(arrayAdapter);
    }

    //================================
    //
    //================================

    @BindView(R.id.imageView)
    ImageView imageView;

    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;

    private void initImageView() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

                switch (checkedId) {
                    case R.id.rb_center:
                        imageView.setScaleType(ImageView.ScaleType.CENTER);
                        break;
                    case R.id.rb_center_inside:
                        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                        break;
                    case R.id.rb_center_crop:
                        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        break;
                    case R.id.rb_matrix:
                        imageView.setScaleType(ImageView.ScaleType.MATRIX);
                        break;
                    case R.id.rb_fit_xy:
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        break;
                    case R.id.rb_fit_start:
                        imageView.setScaleType(ImageView.ScaleType.FIT_START);
                        break;
                    case R.id.rb_fit_center:
                        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                        break;
                    case R.id.rb_fit_end:
                        imageView.setScaleType(ImageView.ScaleType.FIT_END);
                        break;
                    case R.id.rb_adjustviewbound:
                        imageView.setAdjustViewBounds(true);
                        break;
                }
            }
        });
    }
}
