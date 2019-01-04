package com.example.wubin.baselibrary.util;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.wubin.baselibrary.base.BaseInit;

import static com.example.wubin.baselibrary.activity.BaseActivity.myActivity;

public class FragmentUtil {

    private FragmentUtil() {
    }

    public FragmentUtil(int layoutID) {
        this.ID_FL = layoutID;
    }

    public FragmentUtil(final FragmentManager manager, final int layoutID) {
        this.fragmentManager = manager;
        this.ID_FL = layoutID;
    }

    public Fragment startFragment(final Class<? extends Fragment> clazz) {
        return startFragment(clazz, null);
    }

    public Fragment startFragment(final Class<? extends Fragment> clazz, final Bundle bundle) {
        return startFragment(clazz, Integer.MIN_VALUE, bundle);
    }

    public Fragment startFragment(final Class<? extends Fragment> clazz, final int sign) {
        return startFragment(clazz, sign, null);
    }

    public Fragment startFragment(final Class<? extends Fragment> clazz, final int sign, final Bundle bundle) {

        try {

            if (null == fragmentManager) fragmentManager = myActivity.getSupportFragmentManager();

            fragmentTransaction = fragmentManager.beginTransaction();

            if (null != currentFragment) fragmentTransaction.hide(currentFragment);

            tag = clazz.getName();
            if (sign >= 0) tag += sign;

            fragment = fragmentManager.findFragmentByTag(tag);
            if (null == fragment) fragment = clazz.newInstance();

            if (null != bundle && !bundle.isEmpty()) fragment.setArguments(bundle);

            if (fragment.isAdded()) {
                fragmentTransaction.show(fragment);
            } else {
                fragmentTransaction.add(ID_FL, fragment, tag);
            }

            fragmentTransaction.commitAllowingStateLoss();

            currentFragment = fragment;

            return fragment;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void setCurrentFragment(final Fragment fragment) {
        this.currentFragment = fragment;
    }

//======================================================

    private final String className = FileUtil.class.getName();

    private String tag;
    private Fragment fragment;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment currentFragment;

    private int ID_FL;

}
