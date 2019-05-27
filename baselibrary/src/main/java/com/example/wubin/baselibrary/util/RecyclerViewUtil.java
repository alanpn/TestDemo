package com.example.wubin.baselibrary.util;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wubin.baselibrary.activity.BaseActivity;

/**
 * @author wubin
 * @description
 * @date 2019-05-27
 */
public class RecyclerViewUtil {


    public static void setGridView(RecyclerView rv, int spanCount, Rect rect) {

        GridLayoutManager gridLayoutManager = new GridLayoutManager(BaseActivity.myActivity, spanCount);
        rv.setLayoutManager(gridLayoutManager);
        rv.addItemDecoration(new GridViewItemDecoration(rect));

    }

    /**
     * 防刷新卡顿
     */
    public static void setScrollingEnabled(RecyclerView rv, LinearLayoutManager layoutManager) {

        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        rv.setHasFixedSize(true);
        rv.setNestedScrollingEnabled(false);

    }

    private static class GridViewItemDecoration extends RecyclerView.ItemDecoration {

        private int left, top, right, bottom;

        private GridViewItemDecoration(Rect rect) {
            if (null != rect) {
                left = rect.left;
                top = rect.top;
                right = rect.right;
                bottom = rect.bottom;
            }
        }

        public GridViewItemDecoration setLeft(int left) {
            this.left = DeviceUtil.dp2Px(left);
            return this;
        }

        public GridViewItemDecoration setTop(int top) {
            this.top = DeviceUtil.dp2Px(top);
            return this;
        }

        public GridViewItemDecoration setRight(int right) {
            this.right = DeviceUtil.dp2Px(right);
            return this;
        }

        public GridViewItemDecoration setBottom(int bottom) {
            this.bottom = DeviceUtil.dp2Px(bottom);
            return this;
        }

        @Override
        public void getItemOffsets(android.graphics.Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.set(left, top, right, bottom);
        }

    }

    public class Rect {
        public int left, top, right, bottom;
    }
}
