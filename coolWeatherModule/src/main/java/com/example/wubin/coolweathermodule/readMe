


本文为郭霖 https://blog.csdn.net/guolin_blog/article/details/87900605
         https://github.com/guolindev/coolweatherjetpack 的项目

采用MVVM的结构 viewModel dataBanding retrofit

1. LitePal初始化 app Manifest.xml

    <provider
        android:name="com.example.wubin.coolweathermodule.LitePalProvider"
        android:authorities="com.example.wubin.LitePalProvider" />

LitePalProvider 中 LitePal.initialize(getContext());

2.asset litePal.xml

    <list>
        <mapping class="com.example.wubin.coolweathermodule.data.model.place.Province" />
        <mapping class="com.example.wubin.coolweathermodule.data.model.place.City" />
        <mapping class="com.example.wubin.coolweathermodule.data.model.place.County" />
    </list>