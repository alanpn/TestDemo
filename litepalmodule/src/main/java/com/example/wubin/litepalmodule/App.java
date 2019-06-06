package com.example.wubin.litepalmodule;
import android.app.Application;

import java.util.HashMap;

public class App extends Application {

    public static HashMap<String, Object> map = new HashMap<>();

    private static App app;



    @Override
    public void onCreate() {
        super.onCreate();

        app = this;
        LitePal.initialize(getContext());

    }

    public static App getApp() {
        return app;
    }

}
