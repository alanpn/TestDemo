package com.wubin.testdemo;

import android.os.Bundle;

import com.example.wubin.baselibrary.activity.BaseActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author wubin
 * @description
 * @date 2019-07-19
 */
public class ReverseActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void method1() {

        StringBuffer sb = new StringBuffer();
        sb.reverse();

        List<String> list = new ArrayList<>();
        Collections.reverse(list);


    }

}
