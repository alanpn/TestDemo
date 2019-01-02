package com.example.wubin.baselibrary.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * y 新历的年 Y 格里高利的年(一年固定52/53周)
 * M 月 m 分
 * d days in month  D days in year
 * s second S 毫秒
 */
public class TimeUtil {

    public static String format(Date date, int flag) {

        try {

            ObjectUtil.isNull(className, date, "date 为空");

            return getSimpleDateFormat(flag).format(date);

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

        return "";

    }

    public static Date format(String str, int flag) {

        try {

            if (StringUtil.isEmpty(str)) throw new MyException(className, "字符串为空");

            return getSimpleDateFormat(flag).parse(str);

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

        return new Date();

    }

    public static String getCurrentDate() {
        return getSimpleDateFormat(2).format(new Date());
    }

    /**
     * 获得几个月前/后 的 日期
     *
     * @param str   日期
     * @param flag  日期格式
     * @param hours +/- 小时
     */
    public static String toHours(String str, int flag, int hours) {
        return toTimes(str, flag, Calendar.HOUR_OF_DAY, hours);
    }

    public static String toDays(String str, int flag, int days) {
        return toTimes(str, flag, Calendar.DAY_OF_MONTH, days);
    }

    public static String toMonths(String str, int flag, int months) {
        return toTimes(str, flag, Calendar.MONTH, months);
    }

    public static String toYears(String str, int flag, int years) {
        return toTimes(str, flag, Calendar.YEAR, years);
    }

    public static Calendar getCalendar(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar;
    }

    public static String getSandStringBefore() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(calendar.getTime());
    }

    public static String getSandStringBefore6Months(String str) {
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
            Date date = sdf.parse(str);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 5);

            return sdf.format(calendar.getTime());

        } catch (Exception e) {
            ShowUtil.print(e);
        }

        return "";
    }

    //===========================

    private static final String className = TimeUtil.class.getName();

    private static SimpleDateFormat format;

    private static SimpleDateFormat getSimpleDateFormat(int flag) {

        switch (flag) {

            case 0:
                format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                break;

            case 1:
                format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒", Locale.CHINA);
                break;

            case 2:
                format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                break;

            case 3:
                format = new SimpleDateFormat("yyyy年MM月dd日", Locale.CHINA);
                break;

            case 4:
                format = new SimpleDateFormat("yyyy-MM", Locale.CHINA);
                break;

            case 5:
                format = new SimpleDateFormat("yyyy年MM月", Locale.CHINA);
                break;

            case 6:
                format = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA);
                break;

            default: // 与0相同
                format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
                break;

        }

        return format;

    }

    /**
     * 获得 几个 日期类型 前/后 的 日期
     *
     * @param str   日期
     * @param flag  日期格式
     * @param field 类型
     * @param times +/- 时间
     * @return
     */
    private static String toTimes(String str, int flag, int field, int times) {

        try {

            format = getSimpleDateFormat(flag);

            Date date = format.parse(str);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(field, calendar.get(field) + times);

            return format.format(calendar.getTime());

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

        return "";

    }

}
