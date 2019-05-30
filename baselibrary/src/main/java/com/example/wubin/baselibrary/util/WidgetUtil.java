package com.example.wubin.baselibrary.util;

import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.wubin.baselibrary.activity.BaseActivity.myActivity;

/**
 * @author wubin
 * @description
 * @date 2019-05-29
 */
public class WidgetUtil {

    public static TextView getTextView(int id, String text) {

        TextView tv = myActivity.findViewById(id);
        tv.setText(text);
        return tv;

    }

    public static TextView getTextView(View view, int id, String text) {

        TextView tv = view.findViewById(id);
        tv.setText(text);
        return tv;

    }

    public static TextView getTextView(int id, String text, int color) {

        TextView tv = getTextView(id, text);
        tv.setTextColor(ColorUtil.getColor(color));
        return tv;

    }

    public static Button getButton(int id) {
        return myActivity.findViewById(id);
    }

    public static Button getButton(View view, int id) {
        return view.findViewById(id);
    }

    public static Button getButton(int id, View.OnClickListener clickListener) {

        Button button = myActivity.findViewById(id);
        button.setOnClickListener(clickListener);
        return button;

    }

    public static ImageView getImageView(int id) {
        return myActivity.findViewById(id);
    }

    public static ImageView getImageView(View view, int id) {
        return view.findViewById(id);
    }

    public static ImageView getImageView(int id, View.OnClickListener clickListener) {

        ImageView iv = myActivity.findViewById(id);
        iv.setOnClickListener(clickListener);
        return iv;

    }

    public static ImageView getImageView(int id, String url) {

        ImageView iv = myActivity.findViewById(id);
        ImageViewUtil.loadImage(iv, url);
        return iv;

    }

    public static ImageView getImageView(int id, View.OnClickListener clickListener, String url) {

        ImageView iv = myActivity.findViewById(id);
        iv.setOnClickListener(clickListener);
        ImageViewUtil.loadImage(iv, url);
        return iv;

    }

    public static EditText getEditText(int id) {
        return myActivity.findViewById(id);
    }

    public static EditText getEditText(View view, int id) {
        return view.findViewById(id);
    }

    public static EditText getEditText(int id, TextWatcher textWatcher) {

        EditText et = myActivity.findViewById(id);
        et.addTextChangedListener(textWatcher);
        return et;

    }

}
