package com.example.wubin.retrofitmodule.view;

import android.os.Bundle;
import android.view.View;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.example.wubin.baselibrary.util.ShowUtil;
import com.example.wubin.retrofitmodule.R;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;

public class RxJavaActivity extends BaseActivity implements View.OnClickListener {

    private static OkHttpClient.Builder okBuilder;
    private static OkHttpClient okHttpClient;

    private Subscription subscription;

    private static Retrofit.Builder rfBuilder;

    //  https://api.douban.com/v2/book/search?q=%E5%B0%8F%E7%8E%8B%E5%AD%90&tag=&start=0&count=3

    private static final int BTN_1 = R.id.btn1;
    private static final int BTN_2 = R.id.btn2;
    private static final int BTN_3 = R.id.btn3;
    private static final int BTN_4 = R.id.btn4;
    private static final int BTN_5 = R.id.btn5;
    private static final int BTN_6 = R.id.btn6;
    private static final int BTN_7 = R.id.btn7;
    private static final int BTN_8 = R.id.btn8;
    private static final int BTN_9 = R.id.btn9;
    private static final int BTN_10 = R.id.btn10;
    private static final int BTN_11 = R.id.btn11;
    private static final int BTN_12 = R.id.btn12;
    private static final int BTN_13 = R.id.btn13;

    /**
     * map  zip  latmap  retryWhen  behaviorSubject
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rx);

    }

    private Irx init(String url) {
        if (rfBuilder == null) {

            initOkHttp();

            rfBuilder = new Retrofit.Builder()
                    .baseUrl(url)
                    //增加返回值为Gson的支持(以实体类返回)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    //增加返回值为Oservable<T>的支持
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient);
        }

        return rfBuilder.baseUrl(url).build().create(Irx.class);
    }

    private static void initOkHttp() {
        okBuilder = new OkHttpClient.Builder();

        //设置超时
        okBuilder.connectTimeout(15, TimeUnit.SECONDS);
        okBuilder.readTimeout(20, TimeUnit.SECONDS);
        okBuilder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        okBuilder.retryOnConnectionFailure(true);

//  log拦截一，也可以用于拼接公共的参数，如type=android version=4.0
//        Interceptor addQueryParameterInterceptor = new Interceptor() {
//
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//
//                //公共参数的拼装
////                    Request originalRequest = chain.request();
////                    Request request;
////                    String method = originalRequest.method();
////                    Headers headers = originalRequest.headers();
////                    HttpUrl modifiedUrl = originalRequest.url().newBuilder()
////                            .addQueryParameter("platform", "android")
////                            .addQueryParameter("version", "1.0.0")
////                            .build();
////                    request = originalRequest.newBuilder().url(modifiedUrl).build();
////                    return chain.proceed(request);
//
//                Request request = chain.request();
//                Log.e("request", "xxxx:" + request.toString());
//                return chain.proceed(request);
//            }
//        };
//        okBuilder.addInterceptor(addQueryParameterInterceptor);

        //log信息拦截器二
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //设置Debug Log模式
        okBuilder.addInterceptor(httpLoggingInterceptor);

        okHttpClient = okBuilder.build();
    }

    @Override
    public void onClick(View view) {

        int i = view.getId();

        if (i == BTN_1) {

            fun1();

        } else if (i == BTN_2) {

            fun2();

        } else if (i == BTN_3) {

            fun3();

        } else if (i == BTN_4) {

            fun4();

        } else if (i == BTN_5) {

            fun5();

        } else if (i == BTN_6) {

            fun6();

        } else if (i == BTN_7) {

            fun7();

        } else if (i == BTN_8) {

            fun8();

        } else if (i == BTN_9) {

            fun9();

        } else if (i == BTN_10) {

            fun10();

        } else if (i == BTN_11) {

            fun11();

        } else if (i == BTN_12) {

            fun12();

        } else if (i == BTN_13) {

            fun13();

        }
    }

    public void getInfo(int page_num, String seach_msg, String store_status) {

//        Irx irx = ReFt.get().create(Irx.class);
//
//        Map<String, String> map = new HashMap<>();
//        map.put("key", "value");
//
//        // enqueue 异步请求
//        irx.info(BaseActivity.getToken(), store_no, map).enqueue(new MyCallBack<RXBean>() {
//
//            @Override
//            public void onResponse(RXBean bean) {
//                BaseActivity.dismissDialog();
////                infoView.setInfo(storeInfoPo);
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                BaseActivity.dismissDialog();
//                ThrowableUtil.showErrorMessage(t);
//            }
//        });

    }

    /**
     * 返回bean
     */
    private void fun1() {
        subscription = init("https://api.douban.com/v2/book/").search("小王子", "", 0, 3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * list
     */
    private void fun2() {
        subscription = init("http://www.zhuangbi.info/").search("110")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer1);
    }

    /**
     * map 拦截返回值，做处理，返回真正需要的参数
     */
    private void fun3() {
        subscription = init("https://api.douban.com/v2/book/").search("小王子", "", 0, 3)
                .map(func1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer1);
    }

    /**
     * 返回String
     */
    private void fun4() {
        subscription = init("https://api.douban.com/v2/book/").searchStr("小王子", "", 0, 3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(strObserver);
    }

    /**
     * map 拦截返回值，做处理，返回真正需要的参数
     */
    private void fun5() {
        subscription = init("https://api.douban.com/v2/book/").searchStr("小王子", "", 0, 3)
                .map(strFunc)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer1);
    }

    /**
     * 两个一起执行，对结果进行加工后再返回
     */
    private void fun6() {
        Irx rx = init("http://www.zhuangbi.info/");

        subscription =
                Observable.zip(rx.searchStr("110"), rx.searchStr("110"), func2)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(observer1);
    }

    /**
     * 对应输出，传入bean 输出bean 传入list 输出list
     * 也就是单个输出，一对一
     */
    private void fun7() {
        subscription =
                Observable
                        .just(new RxBean())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(observer);
    }

    /**
     * 输入list 依次输出N个bean
     * 多对一
     */
    private void fun8() {
        List<RxBean> list = new ArrayList<>();
        list.add(new RxBean());
        list.add(new RxBean());
        list.add(new RxBean());

        Observable
                .from(list)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 返回正确 错误分开写
     */
    private void fun9() {
        init("https://api.douban.com/v2/book/").search("小王子", "", 0, 3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(actSucc, actError);
    }


    private void fun10() {
        Observable
                .just("http://www.baidu.com", "http://www.baidu.com", "http://www.baidu.com")
                .flatMap(flatFun)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(strObserver);
    }

    /**
     * 请求一返回数据如token 作为请求二中 避免Callback嵌套
     */
    private void fun11() {
        init("https://api.douban.com/v2/book/").searchStr("小王子", "", 0, 3)
                .flatMap(flatFun1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(strObserver);
    }

    /**
     * 当.retry()接收到.onError()事件后触发重订阅。
     * 现在无论对于错，都会走retryError 正确走strObserver， 如果失败，则执行error的flatMap方法，里面可以控制重连次数
     */
    private void fun12() {
//        init("https://api.douban.com/v2/book/").searchStr("小王子", "", 0, 3)

        init("https://api.douban.com/v2/bookkkkkk/").searchStr("小王子", "", 0, 3)
                .retryWhen(retryError)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(strObserver);
    }

    /**
     * cache缓存
     */
    private void fun13() {
        subscribeData(observer1);

        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(5000);

                    subscribeData(observer1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    BehaviorSubject<List<RxBean>> cache;
    List<RxBean> sources = null;

    public Subscription subscribeData(Observer<List<RxBean>> observer) {
        if (cache == null) {
            cache = BehaviorSubject.create();

            Observable.create(new Observable.OnSubscribe<List<RxBean>>() {
                @Override
                public void call(Subscriber<? super List<RxBean>> subscriber) {
//                    List<RxBean> items = Database.getInstance().readItems();
                    //判断硬盘缓存是否为空
                    if (sources == null) {
                        init("http://www.zhuangbi.info/").search("110")
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .doOnNext(new Action1<List<RxBean>>() {
                                    @Override
                                    public void call(List<RxBean> items) {
                                        ShowUtil.print("donOnNext 中的:" + items.toString());
//                                        Database.getInstance().writeItems(items);
                                        sources = items;
                                    }
                                })
                                .subscribe(new Action1<List<RxBean>>() {
                                    @Override
                                    public void call(List<RxBean> items) {
                                        cache.onNext(items);
                                    }
                                }, new Action1<Throwable>() {
                                    @Override
                                    public void call(Throwable throwable) {
                                        throwable.printStackTrace();
                                    }
                                });
                    } else {
                        //发送硬盘数据
                        subscriber.onNext(sources);
                    }
                }
            }).subscribeOn(Schedulers.io())
                    .subscribe(cache);
        }

        return cache.observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    Func1<Observable<? extends Throwable>, Observable<?>> retryError = new Func1<Observable<? extends Throwable>, Observable<?>>() {
        int count = 0, time = 5;

        @Override
        public Observable<?> call(Observable<? extends Throwable> errors) {
            ShowUtil.print("retryError");

            return errors.flatMap(new Func1<Throwable, Observable<?>>() {
                @Override
                public Observable<?> call(Throwable error) {
                    count++;
                    if (count > time) {
                        ShowUtil.print("end............");
                        return Observable.error(error);
                    } else {
                        // For IOExceptions, we  retry
                        if (error instanceof HttpException) {
                            ShowUtil.print(error.getMessage());
                        }

                        return Observable.interval(5, TimeUnit.SECONDS);
                    }
                }
            });
        }
    };

    Func1<String, Observable<String>> flatFun1 = new Func1<String, Observable<String>>() {
        @Override
        public Observable<String> call(String s) {
            ShowUtil.print("xxxxxx", s);
            //这里可以取出请求一获得的值，然后取出需要的部分放入请求二中,如token之类
            return init("http://www.zhuangbi.info/").searchStr("110");
        }
    };

    /**
     * 与flatFun 同
     */
    Func1<String, Observable<String>> flatFun3 = new Func1<String, Observable<String>>() {
        @Override
        public Observable<String> call(final String s) {
            return Observable.create(new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> subscriber) {
                    try {
                        subscriber.onNext(s);
                    } catch (Exception e) {
                        e.printStackTrace();
                        //subscriber.onError(e);
                        subscriber.onNext(null);
                    }
                    subscriber.onCompleted();
                }
            });
        }
    };

    Func1<String, Observable<String>> flatFun = new Func1<String, Observable<String>>() {
        @Override
        public Observable<String> call(String s) {
            return createObservable(s);
        }
    };

    private Observable<String> createObservable(final String url) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    subscriber.onNext(url);
                } catch (Exception e) {
                    e.printStackTrace();
                    //subscriber.onError(e);
                    subscriber.onNext(null);
                }
                subscriber.onCompleted();
            }
        });
    }

    Func1<String, List<RxBean>> strFunc = new Func1<String, List<RxBean>>() {
        @Override
        public List<RxBean> call(String str) {
            ShowUtil.print(str);
            return new ArrayList<>();
        }
    };

    /**
     * 前两个是入参，后一个是出参
     */
    Func2<String, String, List<RxBean>> func2 = new Func2<String, String, List<RxBean>>() {
        @Override
        public List<RxBean> call(String str, String str1) {
            ShowUtil.print("入参1", str);
            ShowUtil.print("入参1", str);
            return new ArrayList<>();
        }
    };

    Func1<RxBean, List<RxBean>> func1 = new Func1<RxBean, List<RxBean>>() {
        @Override
        public List<RxBean> call(RxBean rxBean) {
            ShowUtil.print(rxBean.toString());
            return new ArrayList<>();
        }
    };

    Action1<RxBean> actSucc = new Action1<RxBean>() {
        @Override
        public void call(RxBean rxBean) {
            ShowUtil.print(rxBean.toString());
        }
    };

    Action1<Throwable> actError = new Action1<Throwable>() {
        @Override
        public void call(Throwable throwable) {
            //相当于Subscriber的onError(Throwable e)

            if (throwable instanceof HttpException) {
                ShowUtil.print(throwable.getMessage());
            }
        }
    };

    Observer<String> strObserver = new Observer<String>() {

        @Override
        public void onCompleted() {
            ShowUtil.print("strObserver onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            ShowUtil.print("strObserver onError");
        }

        @Override
        public void onNext(String str) {
            ShowUtil.print("strObserver 的 OnNext :" + str);
        }
    };

    Observer<RxBean> observer = new Observer<RxBean>() {

        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {

            if (e instanceof HttpException) {
                ShowUtil.print(e.getMessage());
            } else if (e instanceof UnknownHostException) {
                ShowUtil.print(e.getMessage());
            }

        }

        @Override
        public void onNext(RxBean bean) {
            ShowUtil.print(bean.toString());
        }
    };

    Observer<List<RxBean>> observer1 = new Observer<List<RxBean>>() {
        @Override
        public void onCompleted() {
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onNext(List<RxBean> images) {
            ShowUtil.print("observer1 中的onNext :" + images.toString());
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unsubscribe();
    }

    private void unsubscribe() {
        if (subscription != null && subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
