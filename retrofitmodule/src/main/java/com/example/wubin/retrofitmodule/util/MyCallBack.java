package com.example.wubin.retrofitmodule.util;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class MyCallBack<T> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {

        if (null == response) {
            onFailure(new RequestException("数据请求失败"));
            return;
        }

        if (response.isSuccessful()) {
            onResponse(response.body());
            return;
        }

        onFailure(new RequestException("数据请求失败" + response.code()));

    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onFailure(t);
    }

    public abstract void onResponse(T t);

    public abstract void onFailure(Throwable t);
}
