package com.example.wubin.baselibrary.util;

import android.content.Intent;

import com.example.wubin.baselibrary.activity.BaseActivity;

import java.util.HashMap;
import java.util.Map;


/**
 * HashMap 对象的 key、value 值均可为 null。且两者的的 key 值均不能重复，若添加 key 相同的键值对，后面的 value 会自动覆盖前面的 value，但不会报错。
 */
public class IntentUtil {

    public static void startActivity(Class clazz) {

        mClass = clazz;

        toActivity();

    }

    public static void startActivityWithFlag(Class clazz, int flag) {

        mClass = clazz;
        mFlag = flag;

        toActivity();

    }

    public static void startActivity(Class clazz, String key, String value) {

        mClass = clazz;
        putKeyValue(key, value);

        toActivity();

    }

    public static void startActivity(Class clazz, Map<String, Object> map) {

        mClass = clazz;
        mLocalMap = map;

        toActivity();

    }

    public static void startActivityForResult(Class clazz, int requestCode) {

        mClass = clazz;
        mRequestCode = requestCode;

        toActivity();

    }

    public static void startActivityForResultWithFlag(Class clazz, int requestCode, int flag) {

        mClass = clazz;
        mRequestCode = requestCode;
        mFlag = flag;

        toActivity();

    }

    public static void startActivityForResult(Class clazz, int requestCode, String key, String value) {

        mClass = clazz;
        mRequestCode = requestCode;
        putKeyValue(key, value);

        toActivity();

    }

    public static void startActivityForResult(Class clazz, int requestCode, Map<String, Object> map) {

        mClass = clazz;
        mRequestCode = requestCode;
        mLocalMap = map;

        toActivity();

    }

    private static void toActivity() {

        try {

            ObjectUtil.isNull(className, mClass, "class 为空");

            Intent intent = new Intent(BaseActivity.myActivity, mClass);

            if (mFlag > 0) intent.addFlags(mFlag);

            if (mRequestCode > 0) {
                ActivityUtil.startActivityForResult(intent, mRequestCode);
            } else {
                ActivityUtil.startActivity(intent);
            }

            if (null != mLocalMap && 0 != mLocalMap.size()) {
                mDataMap.putAll(mLocalMap);
            }

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

        clearLocalData();

    }

    /**
     * 如果不传 默认为""
     */
    public static String getString(final String key) {
        return getString(key, "");
    }

    public static String getString(final String key, final String defaultVaule) {
        return (String) getObject(key, defaultVaule);
    }

    public static boolean getBoolean(final String key) {
        return getBoolean(key, false);
    }

    public static boolean getBoolean(final String key, final boolean defaultVaule) {
        return (boolean) getObject(key, defaultVaule);
    }

    public static double getDouble(final String key) {
        return getDouble(key, 0);
    }

    public static double getDouble(final String key, final double defaultVaule) {
        return (double) getObject(key, defaultVaule);
    }

    public static Object getObject(final String key) {
        return getObject(key, null);
    }

    public static Object getObject(final String key, final Object defaultVaule) {

        if (isNotContainsKey(key)) return defaultVaule;

        Object obj = mDataMap.get(key);
        mDataMap.remove(key);
        return obj;
    }

    //====================================

    private static final String className = IntentUtil.class.getName();

    private static Map<String, Object> mDataMap = new HashMap<>();
    private static Map<String, Object> mLocalMap = new HashMap<>();

    private static Class mClass;
    private static int mRequestCode, mFlag;

    private static void clearLocalData() {
        mLocalMap.clear();
        mClass = null;
        mRequestCode = 0;
        mFlag = 0;
    }

    private static void putKeyValue(String key, String value) {
        try {

            if (StringUtil.isBlank(key)) throw new MyException(className, "key 为空");
            if (StringUtil.isBlank(value)) throw new MyException(className, "value 为空");

            mLocalMap.put(key, value);

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }
    }

    private static boolean containsKey(String key) {

        try {

            if (StringUtil.isBlank(key)) throw new MyException(className, "key 为空");
            if (mDataMap.containsKey(key)) return true;

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

        return false;

    }

    private static boolean isNotContainsKey(String key) {
        return !containsKey(key);
    }

}
