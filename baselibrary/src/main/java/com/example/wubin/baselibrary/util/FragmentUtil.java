package com.example.wubin.baselibrary.util;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import static com.example.wubin.baselibrary.activity.BaseActivity.myActivity;

public class FragmentUtil {

    private FragmentUtil() {
    }

    public FragmentUtil(int layoutID) {
        this.ID_FL = layoutID;
    }

    public FragmentUtil(FragmentManager manager, int layoutID) {

        this.mFragmentManager = manager;
        this.ID_FL = layoutID;

    }

    public Fragment startFragment(Class<? extends Fragment> clazz) {

        this.mClass = clazz;

        return startFragment();

    }

    public Fragment startFragment(Class<? extends Fragment> clazz, int sign) {

        this.mClass = clazz;
        this.mSign = sign;

        return startFragment();

    }

    public Fragment startFragment(Class<? extends Fragment> clazz, Bundle bundle) {

        this.mClass = clazz;
        this.mBundle = bundle;

        return startFragment();

    }

    public Fragment removeFragment(Class<? extends Fragment> clazz, int sign, Bundle bundle) {

        this.mClass = clazz;
        mAction = ACTION.REMOVE;

        return startFragment();

    }

    public Fragment startFragment() {

        try {

            if (null == mClass && null == myActivity && myActivity.isFinishing()) {
                throw new Exception("参数没传");
            }

            if (null == mFragmentManager) {
                mFragmentManager = myActivity.getSupportFragmentManager();
            }

            mFragmentTransaction = mFragmentManager.beginTransaction();

            if (null != mCurrentFragment) {

                if (mAction == ACTION.REMOVE) {
                    mFragmentTransaction.remove(mCurrentFragment);
                } else {
                    mFragmentTransaction.hide(mCurrentFragment);
                }

            }

            mTag = mClass.getName();
            if (mSign >= 0) {
                mTag += mSign;
            }

            mFragment = mFragmentManager.findFragmentByTag(mTag);
            if (null == mFragment) {
                mFragment = mClass.newInstance();
            }

            if (null != mBundle && !mBundle.isEmpty()) {
                mFragment.setArguments(mBundle);
            }

            if (mFragment.isAdded()) {
                mFragmentTransaction.show(mFragment);
            } else {
                mFragmentTransaction.add(ID_FL, mFragment, mTag);
            }

            mFragmentTransaction.commitAllowingStateLoss();

            mCurrentFragment = mFragment;

            return mFragment;

        } catch (Exception e) {
            e.printStackTrace();
            clearData();
        }

        return null;
    }

    public void setCurrentFragment(Fragment fragment) {
        this.mCurrentFragment = fragment;
    }

    //==============================
    private Fragment mCurrentFragment, mFragment;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    private int ID_FL;
    private String mTag;

    private Class<? extends Fragment> mClass;
    private int mSign;
    private Bundle mBundle;
    private ACTION mAction;

    private enum ACTION {
        HIDE, REMOVE
    }

    private void clearData() {

        mClass = null;
        mBundle = null;
        mSign = -1;
        mAction = null;

    }

}
