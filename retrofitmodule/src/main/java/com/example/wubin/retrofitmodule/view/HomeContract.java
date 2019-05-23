package com.example.wubin.retrofitmodule.view;

import java.util.Map;

public class HomeContract {

    public interface View {
        void setTodaySales(TodaySalesPo po);
    }

    public interface Presenter {

        Presenter setView(View view);

        void setTodaySales();
    }

    public interface Service {
        Map<String, String> setTodaySales();
    }

    public static Presenter getPresenter() {
        return new HomePresenter();
    }

}
