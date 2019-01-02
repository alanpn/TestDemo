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

    public static void startActivity(Class clazz) {
        startActivityForResult(clazz, BaseInit.NOT_VALUE);
    }

    public static void startActivityForResult(Class clazz, int requestCode) {
        toActivity(clazz, requestCode, null);
    }

    public static void startActivity(Class clazz, String key, String value) {
        startActivityForResult(clazz, BaseInit.NOT_VALUE, key, value);
    }

    public static void startActivityForResult(Class clazz, int requestCode, String key, String value) {
        try {

            if (StringUtil.isEmpty(key)) throw new MyException(className, "key 为空");
            if (StringUtil.isEmpty(value)) throw new MyException(className, "value 为空");

            localMap = new HashMap<>();
            localMap.put(key, value);

            toActivity(clazz, requestCode, localMap);

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }
    }

    public static void startActivity(Class clazz, Map<String, Object> map) {
        startActivityForResult(clazz, BaseInit.NOT_VALUE, map);
    }

    public static void startActivityForResult(Class clazz, int requestCode, Map<String, Object> map) {
        try {

            if (null == map) throw new MyException(className, "map 为空");

            toActivity(clazz, requestCode, map);

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }
    }

    private static void toActivity(Class clazz, int requestCode, Map<String, Object> map) {

        try {

            ObjectUtil.isNull(className, clazz, "class 为空");

            if (null != map && 0 != map.size()) {
                dataMap.putAll(map);
            }

            Intent intent = new Intent(BaseActivity.myActivity, clazz);

            if (BaseInit.NOT_VALUE == requestCode) {
                ActivityUtil.startActivity(intent);
            } else {
                ActivityUtil.startActivityForResult(intent, requestCode);
            }

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

    }

    /**
     * 如果不传 默认为""
     */
    public static String getString(String key) {
        return getString(key, "");
    }

    public static String getString(String key, String defaultVaule) {
        return (String) getObject(key, defaultVaule);
    }

    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean defaultVaule) {
        return (boolean) getObject(key, defaultVaule);
    }

    public static double getDouble(String key) {
        return getDouble(key, 0);
    }

    public static double getDouble(String key, double defaultVaule) {
        return (double) getObject(key, defaultVaule);
    }

    public static Object getObject(String key) {
        return getObject(key, null);
    }

    public static Object getObject(String key, Object defaultVaule) {

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

            if (StringUtil.isEmpty(key)) throw new MyException(className, "key 为空");
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
