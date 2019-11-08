package com.wubin.testdemo.观察者模式;

import com.example.wubin.baselibrary.util.ShowUtil;

import java.util.Observable;
import java.util.Observer;

/**
 * @author wubin
 * @description
 * @date 2019-11-01
 */
public class Coder implements Observer {

    public String name;

    public Coder(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        ShowUtil.print("码农 ；", name, " ", arg);
    }

}
