package com.wubin.testdemo.base;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import java.util.HashMap;

public class App extends Application {

    public static HashMap<String, Object> map = new HashMap<>();

    private static App app;


    @Override
    public void onCreate() {
        super.onCreate();

        app = this;

//        initLeakCanary();

    }

    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) return;
        LeakCanary.install(this);
    }

    public static App getApp() {
        return app;
    }

}
