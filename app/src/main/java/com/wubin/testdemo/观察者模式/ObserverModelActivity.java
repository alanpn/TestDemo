package com.wubin.testdemo.观察者模式;

import android.os.Bundle;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.wubin.testdemo.R;

/**
 * @author wubin
 * @description
 * @date 2019-11-01
 */
public class ObserverModelActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        DevTechFrontier frontier = new DevTechFrontier();
        for (int i = 0; i < 5; i++) {
            Coder coder = new Coder(i + "");
            frontier.addObserver(coder);
        }

        // 最好使用线程 放在click中考虑不能执行太长时间
        // 越后面订阅的人先执行 栈模式?
        frontier.postNewPublication("新一期的开发周报发布了");

    }


}
