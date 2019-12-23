package com.example.wubin.kotlinModule.view

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import kotlinx.coroutines.launch

/**
 * @author wubin
 * @description
 * @date 2019-12-17
 */
class SharedPreferencesUtil {

    companion object SharedPreferencesUtil {

        fun getString(context: Context, key: String, defValue: String): String {
            var result = getSp(context).getString(key, defValue)
            if (null == result) result = ""
            return result
        }

        fun getString(context: Context, key: String): String {
            return getString(context, key, "")
        }

        fun getBoolean(context: Context, key: String, defValue: Boolean): Boolean {
            return getSp(context).getBoolean(key, defValue)
        }

        fun getBoolean(context: Context, key: String): Boolean {
            return getBoolean(context, key, false)
        }

        fun getFloat(context: Context, key: String, defValue: Float): Float {
            return getSp(context).getFloat(key, defValue)
        }

        fun getFloat(context: Context, key: String): Float {
            return getFloat(context, key, 0F)
        }

        fun getInt(context: Context, key: String, defValue: Int): Int {
            return getSp(context).getInt(key, defValue)
        }

        fun getInt(context: Context, key: String): Int {
            return getInt(context, key, 0)
        }

        fun getLong(context: Context, key: String, defValue: Long): Long {
            return getSp(context).getLong(key, defValue)
        }

        fun getLong(context: Context, key: String): Long {
            return getLong(context, key, 0)
        }

        fun getStringSet(context: Context, key: String, defValue: Set<String>): Set<String> {
            var result = getSp(context).getStringSet(key, defValue)
            if (null == result) result = HashSet()
            return result
        }

        fun getStringSet(context: Context, key: String): Set<String> {
            return getStringSet(context, key, HashSet())
        }

        fun put(context: Context, key: String, value: String) {
            getSp(context).edit { putString(key, value) }
        }

        fun put(context: Context, key: String, value: Boolean) {
            getSp(context).edit { putBoolean(key, value) }
        }

        fun put(context: Context, key: String, value: Float) {
            getSp(context).edit { putFloat(key, value) }
        }

        fun put(context: Context, key: String, value: Int) {
            getSp(context).edit { putInt(key, value) }
        }

        fun put(context: Context, key: String, value: Long) {
            getSp(context).edit { putLong(key, value) }
        }

        fun put(context: Context, key: String, value: Set<String>) {
            getSp(context).edit { putStringSet(key, value) }
        }

        fun clearAll(context: Context) {
            getSp(context).edit { clear() }
        }
    }

}

private lateinit var sp: SharedPreferences
private val SP_NAME = "sp"

private fun getSp(context: Context): SharedPreferences {
    if (!::sp.isInitialized) sp = context.applicationContext.getSharedPreferences(SP_NAME, MODE_PRIVATE);
    return sp
}

private fun SharedPreferences.edit(action: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    action(editor)
    editor.apply()
}