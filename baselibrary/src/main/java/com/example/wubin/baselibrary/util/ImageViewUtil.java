package com.example.wubin.baselibrary.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.Headers;
import com.bumptech.glide.request.RequestOptions;

import java.util.HashMap;
import java.util.Map;

import static com.example.wubin.baselibrary.activity.BaseActivity.myActivity;

public class ImageViewUtil {

    public static void loadImage(ImageView iv, String url) {
        load(TYPE_BITMAP, iv, url);
    }

    public static void loadGif(ImageView iv, String url) {
        load(TYPE_GIF, iv, url);
    }

    public static void load(int type, ImageView iv, String url) {

        try {

            if (null == iv) throw new MyException(className, "ImageView 为空");

            ActivityUtil.checkActivity(className);

            manager = Glide.with(myActivity);

            switch (type) {
                case TYPE_GIF:
                    manager.asGif();
            }

            manager.load(url).apply(getOptions()).into(iv);

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

    }

    private static void load2(ImageView iv, Object url, int resourceId, int width, int height) {

        try {

            if (null == iv) throw new Exception("ImageView 为空");

            RequestOptions options = getOptions();

            if (resourceId != 0) options = options.placeholder(resourceId);

            if (width != 0 && height != 0) options = options.override(width, height);

            GlideUrl glideUrl = getGlideUrl(url.toString());

            Glide.with(myActivity).load(glideUrl).apply(options).into(iv);

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

    }

    /**
     * 带请求头
     */
    public static void loadWithHead(int type, ImageView iv, String url) {

        try {

            if (null == iv) {
                throw new MyException(className, "ImageView 为空");
            }

            ActivityUtil.checkActivity(className);

            manager = Glide.with(myActivity);

            switch (type) {
                case TYPE_GIF:
                    manager.asGif();
            }

            GlideUrl glideUrl = getGlideUrl(url.toString());
            Glide.with(myActivity).load(glideUrl).apply(options).into(iv);

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

    }

    /**
     * 添加请求头
     */
    private static GlideUrl getGlideUrl(String url) {
        GlideUrl glideUrl = new GlideUrl(url, new Headers() {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> header = new HashMap<>();
                //不一定都要添加，具体看原站的请求信息
                header.put("Referer", "https://hmpay.sandpay.com.cn");
                return header;
            }
        });

        return glideUrl;
    }

    //===========================

    private static final String className = ImageViewUtil.class.getName();

    private static RequestOptions options;

    private static RequestManager requestManager;
    private static RequestManager manager;

    private static final int TYPE_GIF = 1 << 1;
    private static final int TYPE_BITMAP = 1 << 2;

    private static RequestOptions getOptions() {

        if (null == options) {

            options = new RequestOptions()

                    // 设置占位图会影响加载后的图片大小
//                    .placeholder(R.mipmap.ic_launcher) // 加载成功之前占位图
//                    .error(R.mipmap.ic_launcher) // 加载错误之后的错误图

//                    .format(DecodeFormat.PREFER_ARGB_8888)

//                    .priority(Priority.HIGH) // 图片请求的优先级

//                    .override(500,500)
//                    .override(100)

//                    // 指定图片的缩放类型为fitCenter（等比例缩放图片，宽或者是高等于ImageView的宽或者是高）
                    .fitCenter()
//                    //指定图片的缩放类型为centerCrop（等比例缩放图片，直到图片的宽高都大于等于ImageView的宽度，
//                    // 然后截取中间的显示。）
//                    .centerCrop()
//                    .circleCrop()//指定图片的缩放类型为centerCrop （圆形）

//                    .skipMemoryCache(true)    //跳过内存缓存
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)    //缓存所有版本的图像
//                    .diskCacheStrategy(DiskCacheStrategy.NONE)    //跳过磁盘缓存
//                    .diskCacheStrategy(DiskCacheStrategy.DATA)    //只缓存原来分辨率的图片
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)    //只缓存最终的图片

            ;

        }

        return options;

    }

}
