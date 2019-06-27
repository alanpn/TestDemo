package com.example.wubin.baselibrary.util;

import androidx.core.content.ContextCompat;

import java.util.HashMap;
import java.util.Map;

import static com.example.wubin.baselibrary.activity.BaseActivity.myActivity;

public class ColorUtil {

    /**
     * 获得颜色
     *
     * @param color : R.color.bg_white
     * @return 可以直接塞入View的颜色 tv.setTextColor(color)
     */
    public static int getColor(final int color) {

        initMap();

        if (mColorCache.containsKey(color)) return mColorCache.get(color);

        int resourceColor = ContextCompat.getColor(myActivity, color);
        mColorCache.put(color, resourceColor);

        return resourceColor;
    }

    //====================================

    // key xml里定义的颜色 value 系统生成的颜色
    private static Map<Integer, Integer> mColorCache;

    private static Map<Integer, Integer> initMap() {
        if (null == mColorCache) mColorCache = new HashMap<>();
        return mColorCache;
    }

}
