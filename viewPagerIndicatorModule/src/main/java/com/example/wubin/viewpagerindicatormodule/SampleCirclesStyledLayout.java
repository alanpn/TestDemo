package com.example.wubin.viewpagerindicatormodule;

import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import com.example.wubin.viewpagerindicatormodule.library.CirclePageIndicator;


public class SampleCirclesStyledLayout extends BaseSampleActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themed_circles);

        mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        mIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
    }
}