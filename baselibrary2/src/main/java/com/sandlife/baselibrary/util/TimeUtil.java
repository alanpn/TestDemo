package com.sandlife.baselibrary.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeUtil {
    public static long lastTime;

    /**
     * 获取当前的毫秒时间
     *
     * @return
     */
    public static long getDate() {
        Date date = new Date();
        return date.getTime();
    }

    public static String getDateTime() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        String date = sDateFormat.format(new Date());
        return date;
    }

    public static String getDateTimeYMD() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = sDateFormat.format(new Date());
        return date;
    }

    public static String getDateTimeY() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy");
        String date = sDateFormat.format(new Date());
        return date;
    }

    public static String getDateTimeM() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("MM");
        String date = sDateFormat.format(new Date());
        return date;
    }

    public static String getDateTimeD() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("dd");
        String date = sDateFormat.format(new Date());
        return date;
    }

    public static String getDateTimeH() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(
                "HH");
        String date = sDateFormat.format(new Date());
        return date;
    }

    public static String getDateTime_m() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(
                "mm");
        String date = sDateFormat.format(new Date());
        return date;
    }

    public static boolean getDate(String str) {
        String regEx = "\\d{1}";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);
        if (!matcher.find()) {
            return false;
        }
        return true; // 分组的索引值是从1开始的，所以取第一个分组的方法是m.group(1)而不是m.group(0)。
    }

    public static String getDateTimeYMDHMS() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sDateFormat.format(new Date());
        return date;
    }

    /**
     * 时间转换
     *
     * @param flag 返回时间类型 0 返回yyyy-MM-dd HH:mm:ss 1 返回yyyy年MM月dd HH:mm:ss 2
     *             yyyy年MM月dd 3 返回yyyy-MM-dd 注意：php转java后面补三个0，java转php去掉后三位
     * @return
     */
    public static String getDateConvert(String d, int flag) {
        Long time = new Long(d + "000");
        SimpleDateFormat sDateFormat;
        switch (flag) {
            case 0:
                sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                break;
            case 1:
                sDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                break;
            case 2:
                sDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                break;
            case 3:
                sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                break;
            case 4:
                sDateFormat = new SimpleDateFormat("MM月dd日");
                break;
            case 5:
                sDateFormat = new SimpleDateFormat("yyyy");
                break;
            case 6:
                sDateFormat = new SimpleDateFormat("MM");
                break;
            case 7:
                sDateFormat = new SimpleDateFormat("dd");
                break;
            case 8:
                sDateFormat = new SimpleDateFormat("HH");
                break;
            case 9:
                sDateFormat = new SimpleDateFormat("mm");
                break;
            case 10:
                sDateFormat = new SimpleDateFormat("ss");
                break;
            case 11:
                sDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
                break;
            case 12:
                sDateFormat = new SimpleDateFormat("yyyyMMdd");
                break;
            case 13:
                sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
                break;
            default:
                sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                break;
        }
        String date = sDateFormat.format(new Date(time));
        return date;
    }

    /**
     * type=1 yyyyMMddHHmmss to yyyy-MM-dd HH:mm:ss yyyyMMddHHmmss转换yyyy-MM-dd
     * HH:mm:ss
     *
     * @return
     */
    public static String getDateConversion(int type, String OrderTime) {
        Date date = null;
        SimpleDateFormat df = null;
        SimpleDateFormat sdf = null;
        try {
            switch (type) {
                case 0:
                    df = new SimpleDateFormat("yyyyMMddHHmmss");
                    sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    break;
                case 1:
                    df = new SimpleDateFormat("yyyyMMdd");
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                    break;
            }
            String d = OrderTime;
            date = df.parse(d);
            return sdf.format(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "时间转换错误";
        }
    }

    /**
     * 时间类型转long
     */
    public static long getTimeTOLong() {
        Date dt1 = new Date();
        long lSysTime1 = dt1.getTime() / 1000; // 得到秒数，Date类型的getTime()返回毫秒数
        return lSysTime1;
    }

    /**
     * long转时间
     */
    public static String getLongToTime(long lSysTime1) {
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
        Date dt = new Date(lSysTime1 * 1000);
        String sDateTime = sdf.format(dt); // 得到精确到秒的表示：08/31/2006 21:08:00
        return sDateTime;
    }

    public static String getLongTime(long lSysTime1) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        // 前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
        Date dt = new Date(lSysTime1 * 1000);
        String sDateTime = sdf.format(dt); // 得到精确到秒的表示：08/31/2006 21:08:00
        return sDateTime;
    }

    public static String getLongToTime_Y_M_D(long lSysTime1) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
        Date dt = new Date(lSysTime1 * 1000);
        String sDateTime = sdf.format(dt); // 得到精确到秒的表示：08/31/2006 21:08:00
        return sDateTime;
    }

    public static String getLongToTime_Y_M_D1(long lSysTime1) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        // 前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
        Date dt = new Date(lSysTime1 * 1000);
        String sDateTime = sdf.format(dt); // 得到精确到秒的表示：08/31/2006 21:08:00
        return sDateTime;
    }

    public static String getSandString() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = sDateFormat.format(new Date());
        return date;
    }

    public static String getSandString2() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMM");
        String date = sDateFormat.format(new Date());
        return date;
    }

    /**
     * 时间对比
     *
     * @return playData 传入播放时间
     */
    public static String calculateData(String playData) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = df.parse(playData);
            Date date = df.parse(getDateTime());
            long l = now.getTime() - date.getTime();
            long day = l / (24 * 60 * 60 * 1000);
            long hour = (l / (60 * 60 * 1000) - day * 24);
            long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
            if (l < 0 || day < 0 || hour < 0 || min < 0 || s < 0) {
                return "002";
            } else {
                return day + "天" + hour + "小时" + min + "分" + s + "秒";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return playData;
    }

    /**
     * 传入年月计算这月有几天
     *
     * @return
     */
    public static int getMMnum(int yyyy, int mm) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, yyyy);
        c.set(Calendar.MONTH, mm - 1);
        return c.getActualMaximum(Calendar.DATE);
    }

    /**
     * 传入到期时间和当前时间对比 没超过返回true
     */
    public static boolean toTimeCompare(String endTime) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = df.parse(endTime);
            String ss = getDateTime(); // 获取当前日期
            // String ss="2013-09-25 22:00:01";
            Date date = df.parse(ss);
            long l = now.getTime() - date.getTime();
            long day = l / (24 * 60 * 60 * 1000);
            long hour = (l / (60 * 60 * 1000) - day * 24);
            long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
            if (l >= 0) {
                if (day >= 0) {
                    if (hour >= 0) {
                        if (min >= 0) {
                            if (s >= 0) {
                                return true;
                            } else {
                                return false;
                            }

                        } else {
                            return false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 返回今天是星期几
     */
    public static int getWeek() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(System.currentTimeMillis()));
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek - 1;
    }

    public static String beforeOneHourToNowDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,
                calendar.get(Calendar.HOUR_OF_DAY) + 1);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(calendar.getTime());
    }

    /*
     * 获取输入的时间后一个小时
     */
    public static String beforeOneHourToNowDateS(String data) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            cal.setTime(df.parse(data));
            cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) + 1);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return df.format(cal.getTime());
    }

    /**
     * 除周二、四及每月5日22：00至次日0：00外
     */
    public static String getSandStringBefore() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        return df.format(calendar.getTime());
    }

    public static String getSandStringBefore2() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
        SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
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
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String getSandStringAfter6Months(String str) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
            Date date = sdf.parse(str);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 5);
            return sdf.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String getSandStringBefore1Year(String str) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
            Date date = sdf.parse(str);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 1);
            calendar.set(Calendar.MONTH, 0);
            return sdf.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String getSandStringAfter1Year(String str) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
            Date date = sdf.parse(str);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
            return sdf.format(calendar.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String getData(String data) {
        Date date = null;
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        try {
            date = format.parse(data);
            return format1.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getTime(String data) {
        Date date = null;

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat format2 = new SimpleDateFormat("HHmmss");
        try {
            date = format2.parse(data);
            return format.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static boolean limitedClickesDuration(long duration) {
        long firstTime = System.currentTimeMillis();
        if (firstTime - lastTime > 0 && firstTime - lastTime < duration) {
            return false;
        }
        lastTime = firstTime;
        return true;
    }

    public static String getServiceDate(int index) {
        String date = null;
        Date webDate = null;
        SimpleDateFormat sdf = null;

        switch (index) {
            case 1:
                sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                break;
            case 2:
                sdf = new SimpleDateFormat("yyyyMMdd");
                break;
            case 3:
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                break;
            case 4:
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                break;

            default:
                break;
        }

        date = sdf.format(new Date());

        return date;
    }

    public static String getEndTime(int index, String str_Date) {
        String date = str_Date;
        String result = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date mDate = null;
        try {
            mDate = sdf.parse(str_Date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(mDate);
        switch (index) {
            case 0:
                result = date;
                break;
            case 1:
                c.add(Calendar.DATE, -7);
                result = sdf.format(c.getTime());
                break;
            case 2:
                c.add(Calendar.MONTH, -1);
                result = sdf.format(c.getTime());
                break;
            case 3:
                c.add(Calendar.MONTH, -3);
                result = sdf.format(c.getTime());
                break;

            default:
                break;
        }
        return result;
    }

    public static String getDateData(int type, String OrderTime) {
        String result = null;
        String time = "";
        int index = 0;
        Date date = null;
        SimpleDateFormat df = null;
        SimpleDateFormat sdf = null;
        df = new SimpleDateFormat("yyyyMMddHHmmss");
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = df.parse(OrderTime);
            time = sdf.format(date);
            index = time.indexOf(" ");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        switch (type) {
            case 0:
                result = time.substring(0, index);
                break;
            case 1:
                int length = time.length();
                result = time.substring(index + 1, length);
                break;

            default:
                break;
        }
        return result;
    }
}
