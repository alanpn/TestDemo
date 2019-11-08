package com.example.wubin.baselibrary.util;

import java.util.List;

public class ObjectUtil {

    public static boolean checkList(List list) {

        try {

            isNull(className, list, "list 为空");

            return 0 != list.size();

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

        return false;
    }

    public static void isNull(String name, Object object, String info) throws Exception {
        if (null == object) throw new MyException(name, info);
    }

    public static String toString(Object o, String defaultValue) {
        return null == o ? defaultValue : o.toString();
    }

    // ===========================

    private static final String className = ObjectUtil.class.getName();

}
