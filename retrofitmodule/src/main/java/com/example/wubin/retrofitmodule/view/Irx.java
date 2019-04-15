package com.example.wubin.retrofitmodule.view;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

//  https://api.douban.com/v2/book/search?q=%E5%B0%8F%E7%8E%8B%E5%AD%90&tag=&start=0&count=3

public interface Irx {

    @GET("search")
    Observable<RxBean> search(@Query("q") String a, @Query("tag") String b, @Query("start") int c, @Query("count") int d);

    @GET("search")
    Observable<String> searchStr(@Query("q") String a, @Query("tag") String b, @Query("start") int c, @Query("count") int d);

    @GET("search")
    Observable<List<RxBean>> search(@Query("q") String query);

    @GET("search")
    Observable<String> searchStr(@Query("q") String query);

    //=============================================
    // 举例 不可调用
    //=============================================

    /**
     * 公共参数
     * @Header 请求头包含字段字段
     */
    /**
     * @GET 表示请求模式为get
     * @Path 表示 动态地址 例如 @GET("app/merchant/store/info/{store_no}") 中就可以使用 @Path("store_no") 来添加传递参数
     * @QueryMap 是 get 的参数组合
     */
    @GET("app/merchant/store/info/{store_no}")
    Call<RxBean> info(@Header("token") String token, @Path("store_no") String store_no, @QueryMap Map<String, String> map);

    /**
     * @FormUrlEncoded 貌似要和 @POST 配合使用 否则请求post会报错
     * @POST 表示请求模式为post
     * @FieldMap 是 post 的参数组合
     */
    @FormUrlEncoded
    @POST("app/merchant/store/edit")
    Call<String> edit(@Header("token") String token, @FieldMap Map<String, String> map);
}

