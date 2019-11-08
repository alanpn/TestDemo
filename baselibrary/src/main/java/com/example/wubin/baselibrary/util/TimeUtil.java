package com.example.wubin.baselibrary.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * y 新历的年 Y 格里高利的年(一年固定52/53周)
 * M 月 m 分
 * d days in month  D days in year
 * s second S 毫秒
 */
public class TimeUtil {

    /**
     * 获得当前时间秒数
     */
    public static long getTime() {
        return new Date().getTime() / 1000;
    }

    /**
     * 将秒转成需要显示的时间
     */
    public static String getTime(long time, String pattern) {
        time = time * 1000; // date类型是毫秒 所以要将秒转成毫秒
        return format(new Date(time), pattern);
    }

    /**
     * 设置时间
     */
    public static String setDate(String originDate, String pattern, int field, int value) {
        try {

            Date date = format(originDate, pattern);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(field, calendar.get(field) + value);

            return format(calendar.getTime(), pattern);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String format(String originDate, String originPattern, String translatePattern) {

        try {

            Date orignDate = format(originDate, originPattern);
            return format(orignDate, translatePattern);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String format(String pattern) {
        return format(new Date(), pattern);
    }

    public static String format(Date date, String pattern) {
        try {
            return getSimpleDateFormat(pattern).format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Date format(String date, String pattern) {
        try {
            return getSimpleDateFormat(pattern).parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static SimpleDateFormat getSimpleDateFormat(String pattern) {

        if (mMap.containsKey(pattern)) return mMap.get(pattern);

        SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.CHINA);
        mMap.put(pattern, sdf);

        return sdf;
    }

    //===========================

    private static final String className = TimeUtil.class.getName();

    private static Map<String, SimpleDateFormat> mMap = new HashMap<>();

    private void ex() {

        Calendar calendar = Calendar.getInstance();

        calendar.get(Calendar.HOUR_OF_DAY);
        calendar.get(Calendar.DAY_OF_MONTH);
        calendar.get(Calendar.MONTH);
        calendar.get(Calendar.YEAR);

        String str = TimeUtil.setDate("2018-09-09", "yyyy-MM-dd", Calendar.DAY_OF_MONTH, -5);
        ShowUtil.print(str);

    }

    private void exSDF() {
        SimpleDateFormat format;

        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

        format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒", Locale.CHINA);

        format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);

        format = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);

        format = new SimpleDateFormat("yyyy-MM", Locale.CHINA);

        format = new SimpleDateFormat("yyyy年MM月", Locale.CHINA);

        format = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);

        format = new SimpleDateFormat("yyyyMM", Locale.CHINA);

    }

}
