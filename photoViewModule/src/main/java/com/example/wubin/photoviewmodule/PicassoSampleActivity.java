package com.example.wubin.photoviewmodule;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

public class PicassoSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_simple);

        final PhotoView photoView = findViewById(R.id.iv_photo);

        String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1531286707074&di=702b75f7c9a2dd445110f417f664e038&imgtype=0&src=http%3A%2F%2Ftva1.sinaimg.cn%2Fcrop.0.0.1278.720%2F90eb2137ly1fmn10bmtmuj20zk0k0mxx.jpg";

        Picasso.with(this)
                .load(url)
                .into(photoView);
    }
}
