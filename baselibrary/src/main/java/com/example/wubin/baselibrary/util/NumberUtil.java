package com.example.wubin.baselibrary.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class NumberUtil {

    private static final String className = NumberUtil.class.getName();

    public static boolean isNumber(Object object) throws Exception {

        ObjectUtil.isNull(className, object, "object 为空");

        // 详见JSON 第23行 CheckDouble 方法
        if (object instanceof Number) {

            // 详见 JSONOjbect 第263行 put方法
            Double d = ((Number) object).doubleValue();

            // isInfinite 是否是无穷大 isNaN 是否非数字
            if (Double.isInfinite(d) || Double.isNaN(d)) return false;
            return true;

        }

        return false;

    }

    public static boolean isNotNumber(Object object) throws Exception {
        return !isNumber(object);
    }

    /**
     * 转换成带2位小数
     */
    public static String formatToNumber(String str) {

        try {

            if (isNotNumber(str)) throw new MyException(className, "不是数字类型");

            double d = Double.parseDouble(str);
            return getDecimalFormatWithNumber().format(d);

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

        return "0.00";

    }

    /**
     * 转换成整数
     */
    public static String formatToInteger(String str) {

        try {

            if (isNotNumber(str)) throw new MyException(className, "不是数字类型");

            double d = Double.parseDouble(str);
            return getDecimalFormatWithInteger().format(d);

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

        return "0.00";

    }

    /**
     * 获得货币
     */
    public static String getMoney(String str) {

        try {

            if (isNotNumber(str)) throw new MyException(className, "不是数字类型");

            return getNumberFormatWithCurrency().format(str);

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

        return "¥0.00";

    }

    /**
     * 获得百分比
     */
    public static String getPercent(String str) {

        try {

            if (isNotNumber(str)) throw new MyException(className, "不是数字类型");

            return getNumberFormatWithPercent().format(str);

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

        return "¥0.00";
    }

    public static boolean isPureNumber(String str) {

        if (StringUtil.isBlank(str)) {
            return false;
        }

        char[] chars = str.toCharArray();

        for (char c : chars) {
            if (c >= 48 && c <= 57) {
            } else {
                return false;
            }
        }

        return false;
    }

    /**
     * 设置数字精度 BigDecimal.ROUND_HALF_UP 四舍五入
     *
     * @param scal 精度(保留几位小数)
     */
    public static double getScalWithHalfUp(double d, int scal) {
        return getScal(d, scal, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 设置数字精度 BigDecimal.ROUND_DOWN)直接删除多余的小数位
     *
     * @param scal 精度(保留几位小数)
     */
    public static double getScalWithDown(double d, int scal) {
        return getScal(d, scal, BigDecimal.ROUND_DOWN);
    }

    //===========================

    private static DecimalFormat fn; // 带小数点的
    private static DecimalFormat fi; // 不带小数点
    private static NumberFormat nfc; // 钱格式
    private static NumberFormat nfp; // 百分比

    private static DecimalFormat getDecimalFormatWithNumber() {
        if (null == fn) fn = new DecimalFormat("#############.00");
        return fn;
    }

    private static DecimalFormat getDecimalFormatWithInteger() {
        if (null == fi) fi = new DecimalFormat("#############0");
        return fi;
    }

    private static NumberFormat getNumberFormat() {
        return NumberFormat.getNumberInstance();
    }

    private static NumberFormat getNumberFormatWithCurrency() {
        if (null == nfc) nfc = getNumberFormat().getCurrencyInstance(Locale.CHINA);
        return nfc;
    }

    private static NumberFormat getNumberFormatWithPercent() {
        if (null == nfp) nfp = getNumberFormat().getPercentInstance(Locale.CHINA);
        return nfp;
    }

    private static double getScal(double d, int scal, int mode) {
        return new BigDecimal(d).setScale(scal, mode).doubleValue();
    }

}
