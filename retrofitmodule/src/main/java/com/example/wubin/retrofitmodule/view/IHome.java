package com.example.wubin.retrofitmodule.view;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.QueryMap;

public interface IHome {

    @GET("app/order/count/today")
    Call<TodaySalesPo> getTodaySales(@Header("token") String token, @QueryMap Map<String, String> map);
}
