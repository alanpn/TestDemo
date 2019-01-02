package com.wubin.testdemo;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.JavascriptInterface;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.example.wubin.baselibrary.util.ShowUtil;

import java.util.Set;

public class WebViewActivity extends BaseActivity {

    private WebView webView;

    private final String baseUrl = "file:///android_asset/test.html";

    private final String jsAlertMethod = "javascript:jsAlert()";

    // js 输入框
    private final String jsPromptMethod = "javascript:jsPrompt()";

    // js 确认框
    private final String jsConfirmMethod = "javascript:jsConfirm()";

    // 传参数到js 中
    private final String jsReceive = "javascript:jsReceive('khkh')";

    private String method = jsReceive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_web);

        webView = findViewById(R.id.activity_web_web);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);

        webView.setWebChromeClient(chromeClient);
        webView.setWebViewClient(webViewClient);

        // 主动式 android直接调用html js 代码
        findViewById(R.id.activity_web_btn).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

//                webView.loadUrl(method);

                // 该方法比loadUrl()效率更高、使用更简洁。
                // 该方法的执行不会使页面刷新，而第一种方法（loadUrl ）的执行则会。
                // >= Android 4.4 || api 19 后才可使用
                webView.evaluateJavascript(method, new ValueCallback<String>() {

                    @Override
                    public void onReceiveValue(String s) {

                        ShowUtil.print("onReceiveValue ", s);

                    }
                });

            }
        });

        // 被动式 android 响应 js 的调用（一）
        // >= api 17 || android 4.2
        // test要和html 里的名字一致
        webView.addJavascriptInterface(new JsToAndroid(), "test");

        webView.loadUrl(baseUrl);

    }

    public WebViewClient webViewClient = new WebViewClient() {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

            ShowUtil.print("onPageStarted");

        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            ShowUtil.print("shouldOverrideUrlLoading");

            // 被动式 android 响应 js 的调用 (二)
            // url ="js://webview?username=111&password=222";
            Uri uri = Uri.parse(url);
            if ("js".equals(uri.getScheme())) {

                if ("webview".equals(uri.getAuthority())) {

                    Set<String> keys = uri.getQueryParameterNames();

                    ShowUtil.print("js 调用 android 方法");

                    for (String key : keys) {
                        String value = uri.getQueryParameter(key);
                        ShowUtil.print("key :", key, " value :" + value);
                    }

                }

                return true;
            }

            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

            ShowUtil.print("onPageFinished");

        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);

            ShowUtil.print("onReceivedError");

        }

    };

    public WebChromeClient chromeClient = new WebChromeClient() {

        @Override
        public void onProgressChanged(WebView view, int newProgress) {

            ShowUtil.print("onProgressChanged ", newProgress);

        }

        @Override
        public void onReceivedTitle(WebView view, String title) {

            ShowUtil.print("onReceivedTitle ", title);

        }

        /**
         *  必须同时加入这两句话拦截弹框弹框才能生效 result.confirm(); return true;
         */
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {

            ShowUtil.print("onJsAlert ", url);
            ShowUtil.print("onJsAlert ", message);

            ShowUtil.toastShow(myActivity, message);
            result.confirm();
            return true;

//            return super.onJsAlert(view, url, message, result);
        }

        @Override
        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {

            ShowUtil.print("onJsConfirm ", message);

            return super.onJsConfirm(view, url, message, result);
        }

        @Override
        public boolean onJsPrompt(
                WebView view, String url, String message, String defaultValue, JsPromptResult result) {

            ShowUtil.print("onJsPrompt ", message);

            return super.onJsPrompt(view, url, message, defaultValue, result);
        }

        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {

            ShowUtil.print("onConsoleMessage ", consoleMessage.message());

            return super.onConsoleMessage(consoleMessage);
        }
    };

    class JsToAndroid extends Object {

        @JavascriptInterface
        public void hello(String msg) {

            // 此方法不需要开线程
//            ShowUtil.toastShow(msg);

            // 更新ui线程 必须开线程才能弹框
            final String url = "javascript:jsReceive('" + msg + "')";
            webView.post(new Runnable() {

                @Override
                public void run() {
                    webView.evaluateJavascript(url, new ValueCallback<String>() {

                        @Override
                        public void onReceiveValue(String s) {

                            ShowUtil.print("onReceiveValue ", s);

                        }
                    });
                }
            });


        }

    }

}
