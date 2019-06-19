package com.wubin.testdemo.jd;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wubin.baselibrary.util.DeviceUtil;
import com.wubin.testdemo.R;

/**
 * @author wubin
 * @description
 * @date 2019-06-19
 */
public class SearchActivity extends AppCompatActivity {

    private AnimationNestedScrollView sv_view;
    private LinearLayout ll_search;
    private TextView tv_title;
    private float LL_SEARCH_MIN_TOP_MARGIN, LL_SEARCH_MAX_TOP_MARGIN, LL_SEARCH_MAX_WIDTH, LL_SEARCH_MIN_WIDTH, TV_TITLE_MAX_TOP_MARGIN;
    private ViewGroup.MarginLayoutParams searchLayoutParams, titleLayoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        initView();

    }

    private void initView() {

        sv_view = findViewById(R.id.search_sv_view);
        ll_search = findViewById(R.id.search_ll_search);
        tv_title = findViewById(R.id.search_tv_title);
        searchLayoutParams = (ViewGroup.MarginLayoutParams) ll_search.getLayoutParams();
        titleLayoutParams = (ViewGroup.MarginLayoutParams) tv_title.getLayoutParams();

        LL_SEARCH_MIN_TOP_MARGIN = DeviceUtil.dp2Px(4.5f);//布局关闭时顶部距离
        LL_SEARCH_MAX_TOP_MARGIN = DeviceUtil.dp2Px(49f);//布局默认展开时顶部距离
        LL_SEARCH_MAX_WIDTH = DeviceUtil.getDisplayWidth() - DeviceUtil.dp2Px(30f);//布局默认展开时的宽度
        LL_SEARCH_MIN_WIDTH = DeviceUtil.getDisplayWidth() - DeviceUtil.dp2Px(82f);//布局关闭时的宽度
        TV_TITLE_MAX_TOP_MARGIN = DeviceUtil.dp2Px(11.5f);

        sv_view.setOnAnimationScrollListener(new AnimationNestedScrollView.OnAnimationScrollChangeListener() {
            @Override
            public void onScrollChanged(float dy) {
                float searchLayoutNewTopMargin = LL_SEARCH_MAX_TOP_MARGIN - dy;
                float searchLayoutNewWidth = LL_SEARCH_MAX_WIDTH - dy * 1.3f;//此处 * 1.3f 可以设置搜索框宽度缩放的速率

                float titleNewTopMargin = (float) (TV_TITLE_MAX_TOP_MARGIN - dy * 0.5);

                //处理布局的边界问题
                searchLayoutNewWidth = searchLayoutNewWidth < LL_SEARCH_MIN_WIDTH ? LL_SEARCH_MIN_WIDTH : searchLayoutNewWidth;

                if (searchLayoutNewTopMargin < LL_SEARCH_MIN_TOP_MARGIN) {
                    searchLayoutNewTopMargin = LL_SEARCH_MIN_TOP_MARGIN;
                }

                if (searchLayoutNewWidth < LL_SEARCH_MIN_WIDTH) {
                    searchLayoutNewWidth = LL_SEARCH_MIN_WIDTH;
                }

                float titleAlpha = 255 * titleNewTopMargin / TV_TITLE_MAX_TOP_MARGIN;
                if (titleAlpha < 0) {
                    titleAlpha = 0;
                }

                //设置相关控件的LayoutParams  此处使用的是MarginLayoutParams，便于设置params的topMargin属性
                tv_title.setTextColor(tv_title.getTextColors().withAlpha((int) titleAlpha));
                titleLayoutParams.topMargin = (int) titleNewTopMargin;
                tv_title.setLayoutParams(titleLayoutParams);

                searchLayoutParams.topMargin = (int) searchLayoutNewTopMargin;
                searchLayoutParams.width = (int) searchLayoutNewWidth;
                ll_search.setLayoutParams(searchLayoutParams);
            }
        });
    }

}
