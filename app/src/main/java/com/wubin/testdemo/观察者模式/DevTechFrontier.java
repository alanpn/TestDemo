package com.wubin.testdemo.观察者模式;

import java.util.Observable;

/**
 * @author wubin
 * @description
 * @date 2019-11-01
 */
public class DevTechFrontier extends Observable {

    public void postNewPublication(String content) {
        setChanged();
        notifyObservers(content);
    }

}
