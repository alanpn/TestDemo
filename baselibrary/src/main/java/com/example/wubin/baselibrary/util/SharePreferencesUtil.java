package com.example.wubin.baselibrary.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.wubin.baselibrary.base.BaseInit;

import java.util.Map;
import java.util.Set;

public class SharePreferencesUtil {

    /**
     * 存
     */
    public static void put(String key, String value) {
        editor.putString(key, value).apply();
    }

    public static void put(String key, boolean value) {
        editor.putBoolean(key, value).apply();
    }

    public static void put(String key, float value) {
        editor.putFloat(key, value).apply();
    }

    public static void put(String key, int value) {
        editor.putInt(key, value).apply();
    }

    public static void put(String key, long value) {
        editor.putLong(key, value).apply();
    }

    public static void put(String key, Set<String> value) {
        editor.putStringSet(key, value).apply();
    }

    public static void put(String[] keys, Object[] values) {

        try {

            ObjectUtil.isNull(className, keys, " keys 为空 ");
            ObjectUtil.isNull(className, values, " values 为空 ");
            int length = keys.length;
            if (length != values.length) throw new Exception(" keys.length != values.length ");

            for (int i = 0; i < length; i++) {
                operatoEditor(editor, keys[i], values[i]);
            }

            editor.apply();

        } catch (Exception e) {
            ShowUtil.print(e);
        }

    }

    public static void put(Map<String, Object> map) {

        try {

            ObjectUtil.isNull(className, map, "map 为空");

            Set<String> keys = map.keySet();

            for (String key : keys) {
                operatoEditor(editor, key, map.get(key));
            }

            editor.apply();

        } catch (Exception e) {
            ShowUtil.print(e);
        }

    }

    /**
     * Retrieve a String value from the preferences.
     *
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defValue.  Throws
     * ClassCastException if there is a preference with this name that is not
     * a String. @throws ClassCastException
     * <p>
     * 简单的说 就是 拿了一个不是String类型的 value
     */
    public static String getString(String key, String defaultValue) {

        try {

            return sp.getString(key, defaultValue);

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

        return defaultValue;

    }

    public static String getString(String key) {
        return getString(key, "");
    }

    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {

        try {

            return sp.getBoolean(key, defaultValue);

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

        return defaultValue;

    }

    public static float getFloat(String key) {
        return getFloat(key, 0);
    }

    public static float getFloat(String key, float defaultValue) {

        try {

            return sp.getFloat(key, defaultValue);

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

        return defaultValue;
    }

    public static int getInt(String key) {
        return getInt(key, 0);
    }

    public static int getInt(String key, int defaultValue) {

        try {

            return sp.getInt(key, defaultValue);

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

        return defaultValue;
    }

    public static long getLong(String key) {
        return getLong(key, 0);
    }

    public static long getLong(String key, long defaultValue) {

        try {

            return sp.getLong(key, defaultValue);

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

        return defaultValue;
    }

    /**
     * 如果没值 返回空
     */
    public static Set<String> getStringSet(String key) {
        return sp.getStringSet(key, null);
    }

    public static Set<String> getStringSet(String key, Set<String> set) {

        try {

            return sp.getStringSet(key, set);

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

        return null;
    }

    /**
     * 并不用判断是否被contains
     */
    public static void remove(String key) {
        editor.remove(key).apply();
    }

    public static void clearValues() {
        editor.clear().apply();
    }

    public static boolean contains(String key) {
        return sp.contains(key);
    }

    public static Map<String, ?> getAll() {
        return sp.getAll();
    }

    //===========================

    private SharePreferencesUtil() {
    }

    private static final String className = SharePreferencesUtil.class.getName();

    private static final String SP_NAME = "sp";

    private static SharedPreferences sp
            = BaseInit.getApplicationContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    private static SharedPreferences.Editor editor = sp.edit();

    private static void operatoEditor(SharedPreferences.Editor editor, String key, Object value) {

        if (value instanceof String) {

            editor.putString(key, value.toString());

        } else if (value instanceof Boolean) {

            editor.putBoolean(key, (Boolean) value);

        } else if (value instanceof Float) {

            editor.putFloat(key, (Float) value);

        } else if (value instanceof Integer) {

            editor.putInt(key, (Integer) value);

        } else if (value instanceof Long) {

            editor.putLong(key, (Long) value);

        }

    }

}
