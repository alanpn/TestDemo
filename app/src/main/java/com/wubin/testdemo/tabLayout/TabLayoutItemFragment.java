package com.wubin.testdemo.tabLayout;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wubin.testdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wubin
 * @description
 * @date 2019/3/12
 */
public class TabLayoutItemFragment extends Fragment {

    @BindView(R.id.layout_tablayout_demo_btn)
    Button btn;

    @BindView(R.id.layout_tablayout_demo_tl)
    TabLayout tl;

    @BindView(R.id.layout_tablayout_demo_vp)
    ViewPager vp;

    List<String> titles = new ArrayList<>();
    List<Fragment> fragments = new ArrayList<>();

    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (null == mView) {
            mView = inflater.inflate(R.layout.fragment_tablayout_item, container, false);
            ButterKnife.bind(this, mView);
            init();
        }
        return mView;
    }

    private void init() {

        MyAdapter adapter = new MyAdapter(getChildFragmentManager());

        vp.setAdapter(adapter);

        tl.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        tl.setupWithViewPager(vp);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < 6; i++) {
                    titles.add("sdfsdf");
                    fragments.add(new TabFragment());
                }

                adapter.notifyDataSetChanged();
            }
        });

    }

    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
