package com.wubin.testdemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.example.wubin.baselibrary.util.ShowUtil;
import com.wubin.testdemo.R;

import java.util.ArrayList;
import java.util.List;

public class MyFragmentActivity2 extends BaseActivity {

    private final int ID_FL = R.id.activity_fragment_fl;

    FragmentManager manager;
    Fragment currentFragment;
    List<Class<? extends Fragment>> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fragment);

        manager = getSupportFragmentManager();

        startFragmentA();

    }

    private void startFragment(Class<? extends Fragment> clazz) {

        try {

            if (null == clazz) {
                return;
            }

            FragmentTransaction transaction = manager.beginTransaction();

            String tag = clazz.getName();
            Fragment fragment = manager.findFragmentByTag(tag);

            if (null == fragment) {
                fragment = clazz.newInstance();
            }

            if (null != currentFragment) {
                transaction.hide(currentFragment);
            }

            if (fragment.isAdded()) {
                transaction.show(fragment);
            } else {
                transaction.add(ID_FL, fragment, tag);
            }

            currentFragment = fragment;

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        back();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            back();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void back() {

        if (fragments.size() == 1) {
            ShowUtil.toastShow("this is the first one");
            return;
        }

        fragments.remove(getLastIndex());
        startFragment(getLastFragment());

    }

    private int getLastIndex() {
        return fragments.size() - 1;
    }

    private Class<? extends Fragment> getLastFragment() {
        return fragments.get(getLastIndex());
    }

    public void startFragmentA() {
        fragments.add(FragmentA.class);
        startFragment(FragmentA.class);
    }

    public void startFragmentB() {
        fragments.add(FragmentB.class);
        startFragment(FragmentB.class);
    }

    public void startFragmentC() {
        fragments.add(FragmentC.class);
        startFragment(FragmentC.class);
    }
}
