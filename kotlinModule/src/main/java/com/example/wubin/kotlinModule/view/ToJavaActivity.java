package com.example.wubin.kotlinModule.view;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.example.wubin.baselibrary.util.ShowUtil;
import com.example.wubin.kotlinModule.R;

import java.text.MessageFormat;
import java.util.List;

import butterknife.ButterKnife;

public class ToJavaActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tojava);
        ButterKnife.bind(this);

//        findViewById(R.id.activity_tojava_btn).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                IntentUtil.startActivity(KotlinActivity.class);
//            }
//        });
//
//        String str = String.format("%1$s %2$s", "Hello", "World!");
//        System.out.println(str);
//
//        str = String.format("{0}", "sfdaf");

        loadApps();

    }

    /**
     * 获取应用所有 名称 包名 启动类名
     */
    private void loadApps() {
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> apps = getPackageManager().queryIntentActivities(intent, 0);
        //for循环遍历ResolveInfo对象获取包名和类名
        for (int i = 0; i < apps.size(); i++) {
            ResolveInfo info = apps.get(i);
            String packageName = info.activityInfo.packageName;
            CharSequence cls = info.activityInfo.name;
            CharSequence name = info.activityInfo.loadLabel(getPackageManager());
            ShowUtil.print("！！！！！", name + "----" + packageName + "----" + cls);
        }
    }

    public static void main(String[] args) {
        String str = String.format("%1$s %2$s", "Hello", "World!");
        System.out.println(str);

        str = MessageFormat.format("{0}", "sfdaf");
        System.out.println(str);
    }
}
