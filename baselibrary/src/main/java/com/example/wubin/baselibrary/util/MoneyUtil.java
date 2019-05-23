package com.example.wubin.baselibrary.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MoneyUtil {
    public static final String CURRENCY_FEN_REGEX = "\\-?[0-9]+";

    /**
     * 金钱转换
     *
     * @param str
     * @return ￥1.00
     */
    public static String getCurrency(String str) {
        return "¥" + getCurrencyWithoutSign(str);
    }

    public static String getCurrencyWithoutSign(String str) {
        String outStr = "0.00";
        try {
            if (StringUtil.isNotBlank(str)) return getDecimalFormat(str);
        } catch (Exception e) {
        }
        return outStr;
    }

    /**
     * 金钱转换
     *
     * @param str
     * @return 600, 450, 625, 465.563
     */
    public static String getFormatter(String str) {
        NumberFormat n = NumberFormat.getNumberInstance();
        String outStr = null;
        try {
            double d = Double.parseDouble(str);
            outStr = n.format(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outStr;
    }

    /**
     * 金钱转换
     *
     * @param str
     * @return 1.00
     */
    public static String getDecimalFormat(String str) {
        DecimalFormat fmt = new DecimalFormat("#############0.00");
        String outStr = null;
        double d;
        try {
            d = Double.parseDouble(str);
            outStr = fmt.format(d);
        } catch (Exception e) {
        }
        return outStr;
    }

    public static String getMoney(String str) {
        try {
            if (StringUtil.isBlank(str)) {
                Double d = Double.valueOf(MoneyUtil.changeF2Y(Long.parseLong(str)));
                return MoneyUtil.getDecimalFormat(String.valueOf(d));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 金钱转换---55.000转换整数
     */
    public static String getDecimalFormat2(String str) {
        DecimalFormat fmt = new DecimalFormat("#############0");
        String outStr = null;
        double d;
        try {
            d = Double.parseDouble(str);
            outStr = fmt.format(d);
        } catch (Exception e) {
        }
        return outStr;
    }

    /**
     * 传入任意两点之间经纬度，计算出距离
     *
     * @param long1：纬度1
     * @param lat1：经度1
     * @param long2：纬度2
     * @param lat2：经度2
     * @return double
     */
    public static Double getDistance(double long1, double lat1, double long2,
                                     double lat2) {
        double a, b, R;
        R = 6378137; // 地球半径
        lat1 = lat1 * Math.PI / 180.0;
        lat2 = lat2 * Math.PI / 180.0;
        a = lat1 - lat2;
        b = (long1 - long2) * Math.PI / 180.0;
        double d;
        double sa2, sb2;
        sa2 = Math.sin(a / 2.0);
        sb2 = Math.sin(b / 2.0);
        d = 2
                * R
                * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(lat1)
                * Math.cos(lat2) * sb2 * sb2));
        return d;
    }

    public static String changeF2Y(Long amount) throws Exception {
        if (!amount.toString().matches(CURRENCY_FEN_REGEX)) {
            throw new Exception("金额格式有误");
        }
        int flag = 0;
        String amString = amount.toString();
        if (amString.charAt(0) == '-') {
            flag = 1;
            amString = amString.substring(1);
        }
        StringBuffer result = new StringBuffer();
        if (amString.length() == 1) {
            result.append("0.0").append(amString);
        } else if (amString.length() == 2) {
            result.append("0.").append(amString);
        } else {
            String intString = amString.substring(0, amString.length() - 2);
            for (int i = 1; i <= intString.length(); i++) {
                if ((i - 1) % 3 == 0 && i != 1) {
                    //result.append(",");
                }
                result.append(intString.substring(intString.length() - i,
                        intString.length() - i + 1));
            }
            result.reverse().append(".")
                    .append(amString.substring(amString.length() - 2));
        }
        if (flag == 1) {
            return "-" + result.toString();
        } else {
            return result.toString();
        }
    }

    public static String changeY2F(String amount) {
        String currency = amount.replaceAll("\\$|\\￥|\\,", ""); // 处理包含, ￥
        int index = currency.indexOf(".");
        int length = currency.length();
        Long amLong = 0l;
        if (index == -1) {
            amLong = Long.valueOf(currency + "00");
        } else if (length - index >= 3) {
            amLong = Long.valueOf((currency.substring(0, index + 3)).replace(
                    ".", ""));
        } else if (length - index == 2) {
            amLong = Long.valueOf((currency.substring(0, index + 2)).replace(
                    ".", "") + 0);
        } else {
            amLong = Long.valueOf((currency.substring(0, index + 1)).replace(
                    ".", "") + "00");
        }
        return amLong.toString();
    }

    /**
     * 金钱转换
     */
    public static String transfer(String param) {
        if ("".equals(param) && param == null) {
            param = "0";
        }
        DecimalFormat df = new DecimalFormat("0.00");
        String outStr = null;
        double d;
        try {
            d = Double.parseDouble(param);
            outStr = df.format(d);
        } catch (Exception e) {
        }
        return outStr;
    }

}