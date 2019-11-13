package com.wubin.testdemo.dataBinding;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.wubin.testdemo.BR;

/**
 * @author wubin
 * @description
 * @date 2019-05-20
 */
public class UserPo extends BaseObservable {

    String name;

    String pass;

    int age;

    String text;

    public UserPo(String name, String pass, int age) {
        this.name = name;
        this.pass = pass;
        this.age = age;
    }

    @Bindable
    public String getName() {
        return name;
    }

    @Bindable
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
        notifyPropertyChanged(BR.pass);
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Bindable
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        notifyPropertyChanged(BR.text);
    }
}
