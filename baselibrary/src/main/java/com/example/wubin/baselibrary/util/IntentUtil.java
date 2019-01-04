package com.example.wubin.baselibrary.util;

import android.content.Intent;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.example.wubin.baselibrary.base.BaseInit;

import java.util.HashMap;
import java.util.Map;


/**
 * HashMap 对象的 key、value 值均可为 null。且两者的的 key 值均不能重复，若添加 key 相同的键值对，后面的 value 会自动覆盖前面的 value，但不会报错。
 */
public class IntentUtil {

    public static void startActivity(final Class clazz) {
        startActivityForResult(clazz, Integer.MIN_VALUE);
    }

    public static void startActivityForResult(final Class clazz, final int requestCode) {
        toActivity(clazz, requestCode, null);
    }

    public static void startActivity(Class clazz, String key, String value) {
        startActivityForResult(clazz, Integer.MIN_VALUE, key, value);
    }

    public static void startActivityForResult(final Class clazz, final int requestCode, final String key, String value) {
        try {

            if (StringUtil.isBlank(key)) throw new MyException(className, "key 为空");
            if (StringUtil.isBlank(value)) throw new MyException(className, "value 为空");

            localMap = new HashMap<>();
            localMap.put(key, value);

            toActivity(clazz, requestCode, localMap);

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }
    }

    public static void startActivity(final Class clazz, final Map<String, Object> map) {
        startActivityForResult(clazz, Integer.MIN_VALUE, map);
    }

    public static void startActivityForResult(final Class clazz, final int requestCode, final Map<String, Object> map) {
        try {

            if (null == map) throw new MyException(className, "map 为空");

            toActivity(clazz, requestCode, map);

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }
    }

    private static void toActivity(final Class clazz, final int requestCode, final Map<String, Object> map) {

        try {

            ObjectUtil.isNull(className, clazz, "class 为空");

            if (null != map && 0 != map.size()) {
                dataMap.putAll(map);
            }

            Intent intent = new Intent(BaseActivity.myActivity, clazz);

            if (requestCode > 0) {
                ActivityUtil.startActivityForResult(intent, requestCode);
            } else {
                ActivityUtil.startActivity(intent);
            }

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

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

        Object obj = dataMap.get(key);
        dataMap.remove(key);
        return obj;
    }

    //====================================

    private static final String className = IntentUtil.class.getName();

    private static Map<String, Object> dataMap = new HashMap<>();

    private static Map<String, Object> localMap;

    private static boolean containsKey(String key) {

        try {

            if (StringUtil.isBlank(key)) throw new MyException(className, "key 为空");
            if (dataMap.containsKey(key)) return true;

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

        return false;

    }

    private static boolean isNotContainsKey(String key) {
        return !containsKey(key);
    }

}
