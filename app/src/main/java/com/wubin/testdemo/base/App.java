package com.wubin.testdemo.base;

import android.app.Application;
import android.content.Context;

import java.util.HashMap;

public class App extends Application {

    public static HashMap<String, Object> map = new HashMap<>();

    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;

    }

    public static App getApp() {
        return app;
    }
}
