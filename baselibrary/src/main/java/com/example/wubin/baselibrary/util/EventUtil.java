package com.example.wubin.baselibrary.util;

public class EventUtil {

    /**
     * 是否可点击
     *
     * @return true: 可以点击 false:不可点击
     */
    public static boolean isClickable() {
        return getInstance().eventCount(1300);
    }

    public static boolean isNotClickable() {
        return !isClickable();
    }

    //====================================
    //
    //====================================

    private static EventUtil util;

    private long lastTime;

    private static EventUtil getInstance() {
        if (null == util) util = new EventUtil();
        return util;
    }

    /**
     * 判断当前点击时的时间是否小于上次点击的时间
     *
     * @param interval
     * @return
     */
    private boolean eventCount(long interval) {

        long eventTime = System.currentTimeMillis();

        long time = eventTime - lastTime;

        if (0 < time && time <= interval) {

            return false;

        } else {

            lastTime = eventTime;
            return true;

        }

    }

    //===========================


}
