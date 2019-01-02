package com.example.wubin.baselibrary.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.wubin.baselibrary.base.BaseInit;

import java.util.Set;

public class SharePreferencesUtil {

    /**
     * 存
     */
    public static void putValue(String key, String value) {
        getSharedPreferencesEditor().putString(key, value).apply();
    }

    public static void putValue(String key, boolean value) {
        getSharedPreferencesEditor().putBoolean(key, value).apply();
    }

    public static void putValue(String key, float value) {
        getSharedPreferencesEditor().putFloat(key, value).apply();
    }

    public static void putValue(String key, int value) {
        getSharedPreferencesEditor().putInt(key, value).apply();
    }

    public static void putValue(String key, long value) {
        getSharedPreferencesEditor().putLong(key, value).apply();
    }

    public static void putValue(String key, Set<String> value) {
        getSharedPreferencesEditor().putStringSet(key, value).apply();
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

            return getSharedPreferences().getString(key, defaultValue);

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

            return getSharedPreferences().getBoolean(key, defaultValue);

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

            return getSharedPreferences().getFloat(key, defaultValue);

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

            return getSharedPreferences().getInt(key, defaultValue);

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

        return defaultValue;
    }

    public static long getLong(String key) {
        try {

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }
        return getLong(key, 0);
    }

    public static long getLong(String key, long defaultValue) {
        try {

            return getSharedPreferences().getLong(key, defaultValue);

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }
        return defaultValue;
    }

    /**
     * 如果没值 返回空
     */
    public static Set<String> getStringSet(String key) {
        return getSharedPreferences().getStringSet(key, null);
    }

    public static Set<String> getStringSet(String key, Set<String> set) {
        return getSharedPreferences().getStringSet(key, set);
    }

    /**
     * 并不用判断是否被contains
     */
    public static void remove(String key) {
        getSharedPreferencesEditor().remove(key).apply();
    }

    public static void clearValues() {
        getSharedPreferencesEditor().clear().apply();
    }

    //===========================

    private static final String className = SharePreferencesUtil.class.getName();

    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;

    private static final String SP_NAME = "sp";

    private static SharedPreferences getSharedPreferences() {
        if (null == sp) {
            sp = BaseInit.getApplicationContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        return sp;
    }

    private static SharedPreferences.Editor getSharedPreferencesEditor() {

        try {

            if (null != editor) return editor;

            editor = getSharedPreferences().edit();

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

        return editor;
    }

}
