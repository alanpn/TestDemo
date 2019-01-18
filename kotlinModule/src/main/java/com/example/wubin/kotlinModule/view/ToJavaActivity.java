package com.example.wubin.kotlinModule.view;

import android.os.Bundle;
import android.view.View;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.example.wubin.baselibrary.util.IntentUtil;
import com.example.wubin.kotlinModule.R;

import java.text.MessageFormat;

import butterknife.ButterKnife;

public class ToJavaActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tojava);
        ButterKnife.bind(this);

        findViewById(R.id.activity_tojava_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtil.startActivity(MainActivity.class);
            }
        });

        String str = String.format("%1$s %2$s", "Hello", "World!");
        System.out.println(str);

        str = String.format("{0}", "sfdaf");

    }

    public static void main(String[] args) {
        String str = String.format("%1$s %2$s", "Hello", "World!");
        System.out.println(str);

        str = MessageFormat.format("{0}", "sfdaf");
        System.out.println(str);
    }
}
