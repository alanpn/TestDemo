package com.wubin.testdemo;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.example.wubin.baselibrary.util.ShowUtil;

/**
 * @author wubin
 * @description
 * @date 2019-10-17
 */
public class BroadcastTestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myreceiver recevier = new myreceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.sand.sandbao.sps.broadcast");
        intentFilter.addAction("comsandpaySPS");
        //当网络发生变化的时候，系统广播会发出值为android.net.conn.CONNECTIVITY_CHANGE这样的一条广播
        registerReceiver(recevier, intentFilter);

    }

    /**
     * 发送请求
     */
    public void openApp() {

        Intent var4 = new Intent("com.sand.sandbao.sps.action");
        var4.putExtra("action", "com.sand.sandbao.sps.broadcast");

        // 设置此选择 发送广播的时候接收到 然后设置 var4.setPackage(getPackageName());  广播只有打开方才能收到
        var4.putExtra("package", getPackageName());

        var4.putExtra("tn", "xxx");
        ComponentName var5 = new ComponentName("com.sand.sandbao", "com.sand.sandbao.sps.SpsLaunchActivity");
        var4.setComponent(var5);

    }


    public void sendBroadcast() {

        Intent intent = new Intent();
        intent.setPackage(getPackageName());
        sendBroadcast(intent);

    }

    public class myreceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //在这里写上相关的处理代码，一般来说，不要此添加过多的逻辑或者是进行任何的耗时操作
            //因为广播接收器中是不允许开启多线程的，过久的操作就会出现报错
            //因此广播接收器更多的是扮演一种打开程序其他组件的角色，比如创建一条状态栏通知，或者启动某个服务
            ShowUtil.print("sdfsdfsf");
        }
    }
}
