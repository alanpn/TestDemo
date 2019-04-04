package com.wubin.testdemo.litepal;

import org.litepal.crud.LitePalSupport;

/**
 * @author wubin
 * @description
 * @date 2019/4/1
 */
public class Student extends LitePalSupport {

    String id;

    String name;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
