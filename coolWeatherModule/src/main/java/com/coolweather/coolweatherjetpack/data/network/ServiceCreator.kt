package com.coolweather.coolweatherjetpack.data.network

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object ServiceCreator {

    private const val BASE_URL = "http://guolin.tech/"

    private val CONNECT_TIMEOUT = 10L
    private val READ_TIMEOUT = 15L
    private val WRITE_TIMEOUT = 15L

    private val logger = getLogger()

    fun getLogger(): HttpLoggingInterceptor {
        var logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
//            Log.d("testDemo", it)
        })
        logger.level = HttpLoggingInterceptor.Level.BODY
        return logger
    }

    private val httpClient = OkHttpClient.Builder()
            .setTimeOut()
            .addInterceptor(logger)
            .build()

    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactorys()
            .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    fun OkHttpClient.Builder.setTimeOut(): OkHttpClient.Builder {
        return connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
    }

    fun Retrofit.Builder.addConverterFactorys(): Retrofit.Builder {
        return addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
    }
}