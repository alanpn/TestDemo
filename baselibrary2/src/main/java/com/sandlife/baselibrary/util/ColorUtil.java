package com.sandlife.baselibrary.util;

import android.content.Context;
import androidx.core.content.ContextCompat;

import com.sandlife.baselibrary.R;


public class ColorUtil {

    private static int COLOR_WHITE;
    private static int COLOR_BLACK;
    private static int COLOR_FF5B10;

    public static int getCOLOR_FF5B10(Context context) {
        return getColors(context, COLOR_FF5B10, R.color.bg_FF5B10);
    }

    public static int getColor_white(Context context) {
        return getColors(context, COLOR_WHITE, R.color.bg_white);
    }

    public static int getColor_black(Context context) {
        return getColors(context, COLOR_BLACK, R.color.bg_black);
    }

    public static int getColors(Context context, int target, int color) {
        if (0 == target) {
            target = ContextCompat.getColor(context, color);
        }
        return target;
    }

}
