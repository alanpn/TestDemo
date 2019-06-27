package com.wubin.testdemo.recyclerView;

/**
 * @author wubin
 * @description
 * @date 2019-06-04
 */
public class UserPo {

    public String name;

    public UserPo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserPo{" +
                "name='" + name + '\'' +
                '}';
    }
}
