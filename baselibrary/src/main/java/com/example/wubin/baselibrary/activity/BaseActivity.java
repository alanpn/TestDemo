package com.example.wubin.baselibrary.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wubin.baselibrary.util.DeviceUtil;

public class BaseActivity extends AppCompatActivity {

    public static BaseActivity myActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myActivity = this;

    }

    @Override
    protected void onResume() {
        super.onResume();

        myActivity = this;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == DeviceUtil.REQ_PERMISSION) {

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {

                // android 8.0 未知来源应用安装
                Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
                startActivity(intent);

            }

        }

    }

}
