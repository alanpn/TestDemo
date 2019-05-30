package com.example.wubin.baselibrary.util;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import static com.example.wubin.baselibrary.activity.BaseActivity.myActivity;

/**
 * @author wubin
 * @description
 * @date 2019-05-27
 */
public class RecyclerViewUtil {

    public static RecyclerView getLinearLayoutRecyclerView(int id) {

        RecyclerView recyclerView = myActivity.findViewById(id);
        recyclerView.setLayoutManager(new LinearLayoutManager(myActivity));
        return recyclerView;

    }

    public static RecyclerView getHorizontalLinearLayoutRecyclerView(int id) {

        RecyclerView recyclerView = myActivity.findViewById(id);
        recyclerView.setLayoutManager(new LinearLayoutManager(myActivity, RecyclerView.HORIZONTAL, false));
        return recyclerView;

    }

    public static RecyclerView getGridRecyclerView(int id, int spanCount, Rect rect) {

        RecyclerView recyclerView = myActivity.findViewById(id);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(myActivity, spanCount);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new GridViewItemDecoration(rect));
        return recyclerView;

    }

    public static RecyclerView getStaggeredGridRecyclerView(int id, int spanCount) {

        RecyclerView recyclerView = myActivity.findViewById(id);
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(spanCount, RecyclerView.VISIBLE);
        recyclerView.setLayoutManager(gridLayoutManager);
        return recyclerView;

    }

    public static RecyclerView getHorizontalStaggeredGridRecyclerView(int id, int spanCount) {

        RecyclerView recyclerView = myActivity.findViewById(id);
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(spanCount, RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        return recyclerView;

    }

    /**
     * 防刷新卡顿
     */
    public static void setScrollingEnabled(RecyclerView recyclerView) {

        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);

    }

    private static class GridViewItemDecoration extends RecyclerView.ItemDecoration {

        private Rect mRect;

        private GridViewItemDecoration(Rect rect) {
            setLeft(rect.left).setTop(rect.top).setRight(rect.right).setBottom(rect.bottom);
        }

        public GridViewItemDecoration setLeft(int left) {
            this.mRect.left = DeviceUtil.dp2Px(left);
            return this;
        }

        public GridViewItemDecoration setTop(int top) {
            this.mRect.top = DeviceUtil.dp2Px(top);
            return this;
        }

        public GridViewItemDecoration setRight(int right) {
            this.mRect.right = DeviceUtil.dp2Px(right);
            return this;
        }

        public GridViewItemDecoration setBottom(int bottom) {
            this.mRect.bottom = DeviceUtil.dp2Px(bottom);
            return this;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.set(mRect);
        }

    }


}
