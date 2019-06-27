package com.example.wubin.popstabviewmodule;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

    }


    public void simpleFilter(View view) {
        startActivity(new Intent(this, SimpleFilterActivity.class));
    }


    public void multifyFilter(View view) {
        startActivity(new Intent(this, MultifyFilterActivity.class));
    }

    public void myFilterResult(View view) {
        startActivity(new Intent(this, MyFilterResultActivity.class));

    }


    public void myFilterStyle(View view) {
        startActivity(new Intent(this, MyFilterStyleActivity.class));

    }
}
