package com.wubin.testdemo.fingerPrint;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.wubin.testdemo.MainActivity;
import com.wubin.testdemo.R;

/**
 * @author wubin
 * @description
 * @date 2019-05-09
 */
public class FingerPrintActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        // android 6.0 开始支持指纹支付
        // android 8.0 更换为新的类 老的废弃 但实测 android9.0 可以使用
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {

            new BiometricPromptController(this).init();

        } else {

            new FingerPrintController(this).init();

        }

    }

    public void onAuthenticated() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
