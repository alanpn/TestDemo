package com.example.wubin.baselibrary.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wubin.baselibrary.util.DeviceUtil;

public class BaseActivity extends AppCompatActivity {

    public static BaseActivity myActivity;

    private boolean openNetFilter = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myActivity = this;

        if (openNetFilter) {
            startNetFilter();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        myActivity = this;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (openNetFilter) {
            unregisterNet();
        }

    }

    //========================================
    // 网络监听
    //========================================

    private boolean NetListenerFlag = true; // 是否需要监听网络
    //检测网络连接状态
    private ConnectivityManager manager;

    private void startNetFilter() {

        if (!NetListenerFlag) return;

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(NetworkReceiver, intentFilter);

    }

    private BroadcastReceiver NetworkReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (NetworkAvailable()) {
                DeviceUtil.getNetworkState(manager);
            } else {
                Toast.makeText(BaseActivity.this, "请检查网络环境", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private void unregisterNet() {

        if (!NetListenerFlag) return;

        if (null == NetworkReceiver) return;

        unregisterReceiver(NetworkReceiver);

    }

    /**
     * 检测网络是否连接
     */
    private boolean NetworkAvailable() {
        try {
            Thread.sleep(600);
            //得到网络连接信息
            manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            if (manager != null) {
                // 获取NetworkInfo对象
                NetworkInfo networkInfo = manager.getActiveNetworkInfo();
                //去进行判断网络是否连接
                if (networkInfo != null || networkInfo.isAvailable()) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
