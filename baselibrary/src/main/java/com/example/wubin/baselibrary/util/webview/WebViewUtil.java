package com.example.wubin.baselibrary.util.webview;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.wubin.baselibrary.base.BaseInit;
import com.example.wubin.baselibrary.util.DeviceUtil;

public class WebViewUtil {

    public static void setWebView(WebView webView) {

        WebSettings settings = webView.getSettings();

//            settings.setUserAgentString("testDeom");    // 浏览器标识

//            如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        settings.setJavaScriptEnabled(true);
        // 支持通过JS打开新窗口 适用于JavaScript方法window.open()
        settings.setJavaScriptCanOpenWindowsAutomatically(true);

        // 当一个安全的来源（origin）试图从一个不安全的来源加载资源时配置WebView的行为
        // android 5.0
        if (Build.VERSION.SDK_INT >= 21)
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        // 定位是否可用，默认为true
        settings.setGeolocationEnabled(true);

        /*
         *  设置自适应屏幕，两者合用
         */
        settings.setUseWideViewPort(true); // 将图片调整到适合webview的大小
        settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        /*
         * 缩放操作
         */
        settings.setSupportZoom(false); //支持缩放，默认为true。是下面那个的前提。
        settings.setBuiltInZoomControls(false); //设置内置的缩放控件。若为false，则该WebView不可缩放
        settings.setDisplayZoomControls(true); //隐藏原生的缩放控件

        /*
         * scrollBar
         */
        webView.setVerticalScrollBarEnabled(false); // 竖向scrollbar
        webView.setHorizontalScrollBarEnabled(false); // 横向scrollbar
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);

        /*
         * 缓存
         */
//            当加载 html 页面时，WebView会在/data/data/包名目录下生成 database 与 cache 两个文件夹
//            请求的 URL记录保存在 WebViewCache.db，而 URL的内容是保存在 WebViewCache 文件夹下
//
//            缓存模式如下：
//            LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
//            LOAD_DEFAULT: （默认）根据 http cache-control 决定是否从网络上取数据。
//            LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
//            LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据
        if (DeviceUtil.isConnected()) {
            settings.setCacheMode(WebSettings.LOAD_DEFAULT);//根据cache-control决定是否从网络上取数据。
        } else {
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//没网，则从本地获取，即离线加载
        }

        String appCasheDir = BaseInit.getApplicationContext().getCacheDir().getAbsolutePath();
        settings.setAppCachePath(appCasheDir);   //设置 Application Caches 缓存目录
        settings.setAppCacheEnabled(true);  // 开启 Application Caches 功能

        // 开启DOM storage API 功能 代替cookie，存储大数据
        settings.setDomStorageEnabled(true);

        String dbPath = BaseInit.getApplicationContext().getDir("database", Context.MODE_PRIVATE).getAbsolutePath();
        // 设置数据库缓存路径 已废弃，数据库路径由实现（implementation）管理，调用此方法无效。
        settings.setDatabasePath(dbPath);
        settings.setDatabaseEnabled(true);  // 开启database storage API功能 js使用数据库

        /*
         * 文件访问权限
         */

        // 是否允许访问文件，默认允许。注意，这里只是允许或禁止对文件系统的访问，
        // Assets 和 resources 文件使用file:///android_asset和file:///android_res仍是可访问的。
        settings.setAllowFileAccess(true);

        // 设置是否允许通过 file url 加载的 Js代码读取其他的本地文件
        // 在Android 4.1前默认允许 在Android 4.1后默认禁止
        settings.setAllowFileAccessFromFileURLs(true);

        // 设置是否允许通过 file url 加载的 Javascript 可以访问其他的源(包括http、https等源)
        settings.setAllowUniversalAccessFromFileURLs(true);

        // 是否允许在WebView中访问内容URL（Content Url），默认允许。
        // 内容Url访问允许WebView从安装在系统中的内容提供者载入内容。
        settings.setAllowContentAccess(true);

        settings.setLoadsImagesAutomatically(true);     // 支持自动加载图片
        settings.setDefaultTextEncodingName("UTF-8");   // 设置编码格式

    }

    //===========================
}
