package com.example.wubin.viewpagerindicatormodule;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.wubin.viewpagerindicatormodule.library.PageIndicator;

import java.util.Random;

public abstract class BaseSampleActivity extends FragmentActivity {
    private static final Random RANDOM = new Random();

    TestFragmentAdapter mAdapter;
    ViewPager mPager;
    PageIndicator mIndicator;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.random) {
            final int page = RANDOM.nextInt(mAdapter.getCount());
            Toast.makeText(this, "Changing to page " + page, Toast.LENGTH_SHORT);
            mPager.setCurrentItem(page);
            return true;
        } else if (i == R.id.add_page) {
            if (mAdapter.getCount() < 10) {
                mAdapter.setCount(mAdapter.getCount() + 1);
                mIndicator.notifyDataSetChanged();
            }
            return true;
        } else if (i == R.id.remove_page) {
            if (mAdapter.getCount() > 1) {
                mAdapter.setCount(mAdapter.getCount() - 1);
                mIndicator.notifyDataSetChanged();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
