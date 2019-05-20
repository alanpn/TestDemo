package com.wubin.testdemo.dataBinding;

import androidx.databinding.BaseObservable;

/**
 * @author wubin
 * @description
 * @date 2019-05-20
 */
public class UserPo extends BaseObservable {

    String name;

    String pass;

    public UserPo(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyChange();
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
