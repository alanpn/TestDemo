package com.example.wubin.litepalmodule;

import org.litepal.crud.LitePalSupport;

/**
 * @author wubin
 * @description
 * @date 2019/4/4
 */
public class Student extends LitePalSupport {

    String no;

    String name;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
