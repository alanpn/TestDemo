package com.example.wubin.baselibrary.util;

import android.support.v4.content.ContextCompat;

import com.example.wubin.baselibrary.activity.BaseActivity;

import java.util.HashMap;
import java.util.Map;

public class ColorUtil {

    /**
     * 获得颜色
     *
     * @param color : R.color.bg_white
     * @return 可以直接塞入View的颜色 tv.setTextColor(color)
     */
    public static int getColor(final int color) {
        initMap();

        if (map.containsKey(color)) return map.get(color);

        int resourceColor = ContextCompat.getColor(BaseActivity.myActivity, color);
        map.put(color, resourceColor);

        return resourceColor;
    }

    //====================================

    // key xml里定义的颜色 value 系统生成的颜色
    private static Map<Integer, Integer> map;

    private static Map<Integer, Integer> initMap() {
        if (null == map) map = new HashMap<>();
        return map;
    }

}
