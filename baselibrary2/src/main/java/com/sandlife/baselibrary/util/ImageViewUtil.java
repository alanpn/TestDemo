package com.sandlife.baselibrary.util;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.sandlife.baselibrary.activity.BaseActivity;

public class ImageViewUtil {

    public static void loadImage(ImageView iv, String url) {
        load(TYPE_BITMAP, iv, url, 0);
    }

    public static void loadImage(ImageView iv, String url, int resourceId) {
        load(TYPE_BITMAP, iv, url, resourceId);
    }

    public static void loadGif(ImageView iv, String url) {
        load(TYPE_GIF, iv, url, 0);
    }

    public static void load(int type, ImageView iv, String url, int resourceId) {

        try {

            if (null == iv) {
                throw new Exception("ImageView 为空");
            }
            if (StringUtil.isBlank(url)) {
                throw new Exception("Url 为空");
            }

            manager = getRequestManager();

            switch (type) {
                case TYPE_GIF:
                    manager.asGif();
            }

            if (0 != resourceId) {
                manager.load(url).apply(getOptions()).into(iv);
            } else {
                manager.load(url).apply(getOptions().placeholder(resourceId).error(resourceId)).into(iv);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Util.print(e.getMessage());
        }

    }

    //===========================

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

    private static RequestManager getRequestManager() {

        if (null == requestManager) {
            requestManager = Glide.with(BaseActivity.myActivity);
        }

        return requestManager;
    }

}
