package com.example.wubin.glidemodule.util;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.Headers;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.wubin.baselibrary.util.ShowUtil;

import java.util.Map;

import static com.example.wubin.baselibrary.activity.BaseActivity.myActivity;

public class ImageViewUtil {

    public static void load(ImageView iv, Object url) {

        mView = iv;
        mUrl = url;

        loadImage();
    }

    public static void load(ImageView iv, Object url, int resourceId, int width, int height) {

        mView = iv;
        mUrl = url;
        mResourceId = resourceId;
        mWidth = width;
        mHeight = height;

        loadImage();

    }

    public static void load(ImageView iv, Object url, RequestOptions options) {

        mView = iv;
        mUrl = url;
        mOptions = options;

        loadImage();

    }

    /**
     * 带返回值
     */
    public static void load(final ImageView iv, Object url, final Listener listener) {

        mView = iv;
        mUrl = url;
        mListener = listener;

        loadImage();

    }


    /**
     * 带请求头
     */
    private static void loadWithHead(ImageView iv, Object url, Map<String, String> map) {

        mView = iv;
        mUrl = url;
        mGlideUrl = getGlideUrl(url.toString(), map);

        loadImage();

    }

    private static void loadImage() {

        try {

            if (null == mView) throw new Exception("ImageView 为空");

            if (null == mOptions) mOptions = getOptions();

            if (mResourceId > 0) mOptions = mOptions.placeholder(mResourceId);

            if (mWidth > 0 && mHeight > 0) mOptions = mOptions.override(mWidth, mHeight);

            t_requestManager = Glide.with(myActivity);

            if (null == mListener) {
                t_builder = t_requestManager.asDrawable();
            } else {
                t_builder = t_requestManager.asBitmap();
            }

            if (null == mGlideUrl) {
                t_builder = t_builder.load(mUrl);
            } else {
                t_builder = t_builder.load(mGlideUrl);
            }

            t_builder = t_builder.apply(mOptions);

            if (null == mListener) {

                t_builder.into(mView);

            } else {

                t_builder.into(new SimpleTarget<Bitmap>() {

                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {

                        mView.setImageBitmap(resource);
                        mListener.ready(resource.getWidth(), resource.getHeight());

                    }
                });

            }


        } catch (Exception e) {
            ShowUtil.print(e);
        } finally {
            clearData();
        }

    }

    /**
     * 一个ImageView 设置 状态不同时显示不同图片 (clickable时红色 unClicked时绿色)
     * private final int[] selectSet = new int[]{android.R.attr.state_selected, android.R.attr.state_enabled};
     * private final int[] unSelectSet = new int[]{android.R.attr.state_enabled};
     */
    public static void loads(final ImageView iv, String url1, final int[] stateSet1, final String url2, final int[] stateSet2) {

        Glide.with(myActivity).load(url1).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {

                StateListDrawable drawable = new StateListDrawable();
                drawable.addState(stateSet1, resource);
                iv.setImageDrawable(drawable);

                Glide.with(myActivity).load(url2).into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {

                        StateListDrawable drawable = (StateListDrawable) iv.getDrawable();
                        drawable.addState(stateSet2, resource);
                        iv.setImageDrawable(drawable);

                    }
                });

            }
        });
    }

    //===========================

    private static final String className = ImageViewUtil.class.getName();

    private static ImageView mView;
    private static Object mUrl;
    private static int mResourceId, mWidth, mHeight;
    private static RequestOptions mOptions;
    private static Listener mListener;
    private static GlideUrl mGlideUrl;

    private static RequestBuilder t_builder;
    private static RequestManager t_requestManager;
    private static GlideUrl t_glideUrl;

    private static RequestOptions _options;

    public static RequestOptions getOptions() {

        if (null == _options) {

            _options = new RequestOptions()

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

        return _options;

    }

    public interface Listener {
        void ready(int x, int y);
    }

    private static void clearData() {

        mView = null;
        mUrl = null;
        mResourceId = 0;
        mWidth = 0;
        mHeight = 0;
        mOptions = null;
        mListener = null;
        mGlideUrl = null;
        t_builder = null;
        t_requestManager = null;
        t_glideUrl = null;

    }

    /**
     * 添加请求头
     * header.put("Referer", "https://hmpay.sandpay.com.cn");
     */
    private static GlideUrl getGlideUrl(String url, final Map<String, String> header) {

        t_glideUrl = new GlideUrl(url, new Headers() {
            @Override
            public Map<String, String> getHeaders() {
                return header;
            }
        });

        return t_glideUrl;

    }

}
