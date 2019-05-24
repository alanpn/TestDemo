package com.example.wubin.viewpagerindicatormodule;

import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import com.example.wubin.viewpagerindicatormodule.library.TitlePageIndicator;

public class SampleTitlesBottom extends BaseSampleActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_titles_bottom);

        mAdapter = new TestFragmentAdapter(getSupportFragmentManager());

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        mIndicator = (TitlePageIndicator)findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
    }
}