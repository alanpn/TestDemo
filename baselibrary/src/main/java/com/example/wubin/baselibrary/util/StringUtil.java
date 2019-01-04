package com.example.wubin.baselibrary.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StringUtil {

    /**
     * 判断字符串是否为空
     * 只要不是空格的字符就返回false 缺点 有带空格的字符串
     *
     * <pre>
     * isBlank(null)      = true
     * isBlank("")        = true
     * isBlank(" ")       = true
     * isBlank("bob")     = false
     * isBlank("  bob  ") = false
     * </pre>
     */
    public static boolean isBlank(final CharSequence cs) {
        if (isEmpty(cs)) return true;

        for (int i = 0, strLen = cs.length(); i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) return false;
        }

        return true;
    }

    public static boolean isNotBlank(final String str) {
        return !isBlank(str);
    }

    /**
     * 判断只要有空格就为true 保证程序没有空格
     *
     * <pre>
     * isWhitespace("aaa")       = false
     * isWhitespace("   bbb   ") = true
     * isWhitespace("b  b")      = true
     * </pre>
     */
    public static boolean isWhitespace(final String str) {
        if (isEmpty(str)) return false;
        if (str.trim().length() != str.length()) return true;

        for (int i = 0, strLen = str.length(); i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) return true;
        }

        return false;
    }

    /**
     * 去空格
     *
     * <pre>
     * deleteWhitespace(null)            = ""
     * deleteWhitespace(" ")             = ""
     * deleteWhitespace("  a  b  cd   ") = abcd
     * </pre>
     */
    public static String deleteWhitespace(final String str) {
        if (isEmpty(str)) return "";

        char c;
        StringBuffer buffer = new StringBuffer();
        for (int i = 0, strLen = str.length(); i < strLen; i++) {
            c = str.charAt(i);
            if (!Character.isWhitespace(c)) buffer.append(c);
        }
        return buffer.toString();
    }


    /**
     * <pre>
     * trim(null)          = null
     * trim("")            = ""
     * trim("     ")       = ""
     * trim("abc")         = "abc"
     * trim("    abc    ") = "abc"
     * trim(" a b ")       = "a b"
     * </pre>
     */
    public static String trim(final String str) {
        return str == null ? null : str.trim();
    }

    /**
     * Splits the provided text into an array
     * <p>
     * split("a..b.c", '.')  = ["a", "b", "c"]
     * String.split 使用正则表达式写的 缺点: 不能去空格
     * <pre>
     * split(null, *)         = null
     * split("", *)           = []
     * split("a.b.c", '.')    = ["a", "b", "c"]
     * split("a..b.c", '.')   = ["a", "b", "c"]
     * split("a:b:c", '.')    = ["a:b:c"]
     * split("a b c", ' ')    = ["a", "b", "c"]
     * </pre>
     */
    public static String[] split(final String str, final char separatorChar) {
        boolean preserveAllTokens = false;

        if (str == null) return null;

        final int len = str.length();
        if (len == 0) return new String[]{};
        final List<String> list = new ArrayList<>();
        int i = 0, start = 0;
        boolean match = false;
        boolean lastMatch = false;
        while (i < len) {
            if (str.charAt(i) == separatorChar) {   // 碰到分隔符就continue 所以间接保证没有空格 然后记录开始和结束的index
                if (match || preserveAllTokens) {
                    list.add(str.substring(start, i));
                    match = false;
                    lastMatch = true;
                }
                start = ++i;
                continue;
            }
            lastMatch = false;
            match = true;
            i++;
        }
        if (match || preserveAllTokens && lastMatch) {
            list.add(str.substring(start, i));
        }
        return list.toArray(new String[list.size()]);
    }

    /**
     * 去除多余字段区间
     * <p>
     * replaceAll("aaa<script>sdfsfsfs</script>bbb", "<script>", "</script>") = aaabbb
     */
    public static String replaceAll(final String str, final String startStr, final String endStr) {
        if (isEmpty(str)) return "";
        if (isEmpty(startStr) || isEmpty(endStr)) return str;
        if (startStr.equals(endStr)) return str;

        StringBuffer buffer = new StringBuffer();
        buffer.append(str);
        int startIndex, endIndex, endStrLength = endStr.length();
        while (true) {
            startIndex = buffer.indexOf(startStr);
            endIndex = buffer.indexOf(endStr);
            if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
                buffer.delete(startIndex, endIndex + endStrLength);
                continue;
            }
            break; // 保证能退出循环体
        }

        return buffer.toString();
    }

    /**
     * 字符串拼装
     */
    public static String getString(final Object... objs) {

        try {

            ObjectUtil.isNull(className, objs, "字符串为空");

            StringBuffer sb = new StringBuffer();
            for (Object obj : objs) sb.append(ObjectUtil.toString(obj, ""));
            return sb.toString();

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

        return "";

    }

    /**
     * 排除非空情况 否则插入到TextView的时候会报错
     */
    public static String getString(final String str) {
        return isBlank(str) ? "" : str;
    }

    /**
     * 是不是中文
     */
    public static boolean isChinese(final String str) {
        String regEx = "[\u4e00-\u9fa5]";
        return Pattern.compile(regEx).matcher(str).find();
    }

    // ===========================

    private static final String className = StringUtil.class.getName();

    private StringUtil() {
    }

    private static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0 || cs.equals("null");
    }

    private static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    public static void main(String[] args) {
        String str = "aa a bcc c bbd dd bbb eee";
        System.out.println(str.replaceAll(" ",""));
    }
}
