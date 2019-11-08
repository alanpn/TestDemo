package com.example.wubin.glidemodule.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.example.wubin.glidemodule.R;
import com.example.wubin.glidemodule.util.ImageViewUtil;

public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test);

        ImageView iv = findViewById(R.id.activity_test_iv);

        String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531286707074&di=702b75f7c9a2dd445110f417f664e038&imgtype=0&src=http%3A%2F%2Ftva1.sinaimg.cn%2Fcrop.0.0.1278.720%2F90eb2137ly1fmn10bmtmuj20zk0k0mxx.jpg";

        //        ImageViewUtil.load(iv, url);

        ImageViewUtil.load(iv, url, ImageViewUtil.getOptions().circleCrop());

    }

}
