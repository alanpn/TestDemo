package com.wubin.testdemo.banner;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.wubin.testdemo.R;
import com.youth.banner.Banner;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wubin
 * @description
 * @date 2019-08-19
 */
public class BannerActivity extends BaseActivity {

    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.rv_palette)
    RecyclerView mPaletteRv;

    private PaletAdapter mPaletAdapter = new PaletAdapter();
    private List<PaletColor> mPaletColors = new ArrayList<>();
    private List<Integer> images = new ArrayList();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_banner);
        ButterKnife.bind(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mPaletteRv.setLayoutManager(linearLayoutManager);
        mPaletteRv.setAdapter(mPaletAdapter);

        images.add(R.mipmap.img_01);
        images.add(R.mipmap.img_02);
        images.add(R.mipmap.img_03);
        images.add(R.mipmap.img_04);
        images.add(R.mipmap.img_05);
        mBanner.setImages(images).setImageLoader(new GlideImageLoader()).setDelayTime(2000).start();

        mBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                Log.d("LYJTECH","onPageScrolled "+position);
            }

            @Override
            public void onPageSelected(int position) {

                Bitmap bmp = ((BitmapDrawable) getResources().getDrawable(images.get(position))).getBitmap();
                if (bmp == null) return;

                Palette.from(bmp).generate(new Palette.PaletteAsyncListener() {

                    @Override
                    public void onGenerated(@Nullable Palette palette) {

                        if (palette == null) return;

                        showColorStructure(palette);

                        changeTitleColor(palette);

                    }
                });

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void showColorStructure(@NotNull Palette palette) {

        int dominant = palette.getDominantColor(Color.BLACK);
        int vibrant = palette.getVibrantColor(Color.BLACK);//有活力的
        int vibrantDark = palette.getDarkVibrantColor(Color.BLACK);//有活力的，暗色
        int vibrantLight = palette.getLightVibrantColor(Color.BLACK);//有活力的，亮色
        int muted = palette.getMutedColor(Color.BLACK);//柔和的
        int mutedDark = palette.getDarkMutedColor(Color.BLACK);//柔和的，暗色
        int mutedLight = palette.getLightMutedColor(Color.BLACK);//柔和的,亮色
        mPaletColors.clear();
        mPaletColors.add(new PaletColor(dominant, "palette.getDominantColor(Color.BLACK);"));
        mPaletColors.add(new PaletColor(vibrant, " palette.getVibrantColor(Color.BLACK);//有活力的"));
        mPaletColors.add(new PaletColor(vibrantDark, "palette.getDarkVibrantColor(Color.BLACK);//有活力的，暗色"));
        mPaletColors.add(new PaletColor(vibrantLight, "palette.getLightVibrantColor(Color.BLACK);//有活力的，亮色"));
        mPaletColors.add(new PaletColor(muted, "palette.getMutedColor(Color.BLACK);//柔和的"));
        mPaletColors.add(new PaletColor(mutedDark, "palette.getDarkMutedColor(Color.BLACK);//柔和的，暗色"));
        mPaletColors.add(new PaletColor(mutedLight, "palette.getLightMutedColor(Color.BLACK);//柔和的,亮色"));
        mPaletAdapter.setData(mPaletColors);

    }

    private void changeTitleColor(@NotNull Palette palette) {
        Palette.Swatch paletteSwatch = palette.getVibrantSwatch();
        if (paletteSwatch == null) {
            for (Palette.Swatch swatch : palette.getSwatches()) {
                paletteSwatch = swatch;
                break;
            }
        }
        // 这样获取的颜色可以进行改变。
        int rbg = paletteSwatch.getRgb();

// tabLayout.setBackgroundColor(rbg);
// toolbar.setBackgroundColor(rbg);
        if (Build.VERSION.SDK_INT > 21) {
            Window window = getWindow();
            //状态栏改变颜色。
            int color = changeColor(rbg);
            window.setStatusBarColor(color);
        }
    }

    // 对获取到的RGB颜色进行修改。（涉及到位运算，我也不是很懂这块）
    private int changeColor(int rgb) {
        int red = rgb >> 16 & 0xFF;
        int green = rgb >> 8 & 0xFF;
        int blue = rgb & 0xFF;
        red = (int) Math.floor(red * (1 - 0.2));
        green = (int) Math.floor(green * (1 - 0.2));
        blue = (int) Math.floor(blue * (1 - 0.2));
        return Color.rgb(red, green, blue);
    }

}
