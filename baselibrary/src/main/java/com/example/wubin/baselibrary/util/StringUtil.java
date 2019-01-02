package com.example.wubin.baselibrary.util;

import java.util.regex.Pattern;

public class StringUtil {

    public static boolean isEmpty(String str) {

        int len;

        if ((null == str) || ((len = str.length()) == 0) || str.equals("null")) {
            return true;
        }

        for (int i = 0; i < len; i++) {

            if (!(Character.isWhitespace(str.charAt(i)))) {
                return false;
            }

        }

        return true;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static String getString(Object... objs) {

        try {

            ObjectUtil.isNull(className, objs, "字符串为空");

            sb = new StringBuffer();

            for (Object obj : objs) {
                sb.append(obj.toString());
            }

            return sb.toString();

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

        return "";

    }

    /**
     * 是不是中文
     */
    public static boolean isChinese(String str) {
        String regEx = "[\u4e00-\u9fa5]";
        return Pattern.compile(regEx).matcher(str).find();
    }

    // ===========================

    private static final String className = StringUtil.class.getName();

    private static StringBuffer sb;
}
