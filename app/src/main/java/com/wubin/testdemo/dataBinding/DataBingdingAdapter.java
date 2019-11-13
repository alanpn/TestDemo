package com.wubin.testdemo.dataBinding;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

/**
 * @author wubin
 * @description
 * @date 2019-11-13
 */
public class DataBingdingAdapter {

//    @BindingAdapter("android:text")
//    public static void setText(TextView view, String str) {
//        ShowUtil.print(str);
//        view.setText(str);
//    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView iv, int imageUrl) {
        iv.setBackgroundResource(imageUrl);
    }

}
