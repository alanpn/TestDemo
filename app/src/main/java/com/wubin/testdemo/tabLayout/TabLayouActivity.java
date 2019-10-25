package com.wubin.testdemo.tabLayout;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.google.android.material.tabs.TabLayout;
import com.wubin.testdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/20.
 */
public class TabLayouActivity extends BaseActivity {

    @BindView(R.id.activity_tablayout_tab2)
    TabLayout tabLayout2;

    @BindView(R.id.activity_tablayout_tab)
    TabLayout tabLayout;

    @BindView(R.id.activity_tablayout_tab1)
    TabLayout tabLayout1;

    @BindView(R.id.activity_tablayout_vp)
    ViewPager viewPager;

    private TabFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tablayout);

        ButterKnife.bind(this);

        for (int i = 0; i < 5; i++) {
            tabLayout2.addTab(tabLayout2.newTab().setText("第" + i + "个标签"));
        }

        // tab 下划线是否跟文字大小一样宽 (默认是整个tab的宽度)
//        app:tabIndicatorFullWidth="false"
//        tabLayout.setTabIndicatorFullWidth(false);

        // 可以自定义TabView样式 实现文字图片排列
//        final View view = getLayoutInflater().inflate(R.layout.view_tab, null, false);
//        tabLayout.addTab(tabLayout.newTab().setCustomView(view));

        final List<String> titles = new ArrayList<>();
        titles.add("a");
        titles.add("b");
        titles.add("csdfsfdsfsf");
        titles.add("d");
        titles.add("e");
        titles.add("f");

        // 是否可以滚动 默认不滚动时tab只会平分一个屏幕大小 导致tab变小，字体挤到下排
        // 可以实现根据字数不同而tab大小不同的样式 但tab最大宽度有个20个汉字左右的边界值
//        app:tabMode="scrollable"
//        tabLayout1.setTabMode(TabLayout.MODE_SCROLLABLE);

        // 设置tab 的最大宽度
//        app:tabMinWidth="120dp"
        // 设置tab 的最小宽度
//        app:tabMaxWidth="120dp"

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }

            @Override
            public Fragment getItem(int position) {
                return new TabFragment();
            }

            @Override
            public int getCount() {
                return titles.size();
            }
        });

        tabLayout1.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        tabLayout1.setupWithViewPager(viewPager);

    }
}
