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

    public static void isNull(Object object) throws Exception {
        isNull(className, object, "object 为空");
    }

    public static void isNull(String className, Object object, String info) throws Exception {
        if (null == object) throw new MyException(className, info);
    }


    // ===========================

    private static final String className = ObjectUtil.class.getName();

}
