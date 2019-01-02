package com.example.wubin.retrofitmodule.util;

import android.text.TextUtils;

import com.example.wubin.baselibrary.util.GsonUtil;
import com.example.wubin.baselibrary.util.ShowUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

public class ReFt {

    private static final int CONNECT_TIMEOUT = 10;
    private static final int READ_TIMEOUT = 15;
    private static final int WRITE_TIMEOUT = 15;

    private final String appUrl = "http://www.baidu.com";

    private static ReFt INSTANCE;
    private Retrofit mRetrofit;

    /**
     * Rxtrofit 可添加 okhttp请求 gson,string解析 rxjava的返回
     * <p>
     * 虽然REtrofit自带okhttp 但也可以自定义一个okhttp设置超时时间等 如果有兴趣还可以增加一个HttpLoggingInterceptor作为打印请求报文和添加公共参数
     * <p>
     * Rxtrofit 缺点在于 当404的时候 也会走 onResponse 方法 所以需要加response.isSuccessful() 进行判断 当然你也可以自定义CallBack
     * <p>
     * Rxjava 就是一个CallBack
     */
    private ReFt() {

        mRetrofit = new Retrofit.Builder()
                .client(getOkClient())
                .baseUrl(appUrl)
                .addConverterFactory(MyScalarsConverterFactory.create())
                .addConverterFactory(MyGsonConverterFactory.create(GsonUtil.create()))
                .build();
    }

    private OkHttpClient getOkClient() {

        // okhttpclient
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();

        return builder
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(getInterceptor())
                .build();
    }

    private HttpLoggingInterceptor getInterceptor() {

        HttpLoggingInterceptor lgr = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {

                if (TextUtils.isEmpty(message)) return;

                ShowUtil.print(message);

            }
        });

        lgr.setLevel(HttpLoggingInterceptor.Level.BODY);

        return lgr;
    }


    public static ReFt get() {

        if (INSTANCE == null) {
            synchronized (ReFt.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ReFt();
                }
            }
        }

        return INSTANCE;
    }


    public <T> T create(Class<T> service) {
        return mRetrofit.create(service);
    }

}
