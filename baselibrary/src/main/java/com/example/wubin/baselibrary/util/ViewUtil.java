package com.example.wubin.baselibrary.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Field;

public class ViewUtil {

    /**
     * 设置父类为LinearLayout的控件的 宽度
     */
    public void setLinearLayoutViewSizeWidth(View view, int width) {
        setLinearLayoutViewSize(view, width, 0);
    }

    /**
     * 设置父类为LinearLayout的控件的 高度
     */
    public void setLinearLayoutViewSizeHeight(View view, int height) {
        setLinearLayoutViewSize(view, 0, height);
    }

    /**
     * 设置父类为LinearLayout的控件的 宽度和高度
     */
    public void setLinearLayoutViewSize(View view, int width, int height) {

        try {

            ObjectUtil.isNull(className, view, "view 为空");

            ll = new LinearLayout.LayoutParams(width, height);

            if (0 != width) ll.width = width;
            if (0 != height) ll.height = height;

            view.setLayoutParams(ll);

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

    }

    /**
     * 设置父类为RelativeLayout的控件的 宽度
     */
    public void setRelativeLayoutViewSizeWidth(View view, int width) {
        setRelativeLayoutViewSize(view, width, 0);
    }

    /**
     * 设置父类为RelativeLayout的控件的 高度
     */
    public void setRelativeLayoutViewSizeHeight(View view, int height) {
        setRelativeLayoutViewSize(view, 0, height);
    }

    /**
     * 设置父类为RelativeLayout的控件的 宽度和高度
     */
    public void setRelativeLayoutViewSize(View view, int width, int height) {

        try {

            ObjectUtil.isNull(className, view, "view 为空");

            rl = new RelativeLayout.LayoutParams(width, height);

            if (0 != width) rl.width = width;
            if (0 != height) rl.height = height;

            view.setLayoutParams(rl);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 设置父类为RelativeLayout的控件的 宽度
     */
    public void setViewGroupViewSizeWidth(View view, int width) {
        setViewGroupViewSize(view, width, 0);
    }

    /**
     * 设置父类为RelativeLayout的控件的 高度
     */
    public void setViewGroupViewSizeHeight(View view, int height) {
        setViewGroupViewSize(view, 0, height);
    }

    /**
     * 设置父类为RelativeLayout的控件的 宽度和高度
     */
    public void setViewGroupViewSize(View view, int width, int height) {

        try {

            ObjectUtil.isNull(className, view, "view 为空");

            vl = new ViewGroup.LayoutParams(width, height);

            if (0 != width) vl.width = width;
            if (0 != height) vl.height = height;

            view.setLayoutParams(vl);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setListViewHeight(ListView listView) {

        try {

            ObjectUtil.isNull(className, listView, "listView 为空");

            ListAdapter adapter = listView.getAdapter();

            setAbsListViewHight(listView, adapter, adapter.getCount(), listView.getDividerHeight());

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

    }

    public void setGridViewHeight(GridView gridView) {

        try {

            ObjectUtil.isNull(className, gridView, "listView 为空");

            ListAdapter listAdapter = gridView.getAdapter();
            if (null == listAdapter) throw new MyException(className, "ListAdapter 为空");

            Class<?> clazz = gridView.getClass();
            Field column = clazz.getDeclaredField("mRequestedNumColumns");
            column.setAccessible(true);
            int columns = (Integer) column.get(gridView);
            //利用反射，取得横向分割线高度
            Field horizontalSpacing = clazz.getDeclaredField("mRequestedHorizontalSpacing");
            horizontalSpacing.setAccessible(true);
            int horizontalBorderHeight = (Integer) horizontalSpacing.get(gridView);

            int rows;
            //判断数据总数除以每行个数是否整除。不能整除代表有多余，需要加一行
            int count = listAdapter.getCount();
            if (count % columns > 0) {
                rows = count / columns + 1;
            } else {
                rows = count / columns;
            }

            setAbsListViewHight(gridView, listAdapter, rows, horizontalBorderHeight);

        } catch (Exception e) {
            ShowUtil.showErrorMessage(e);
        }

    }

    //===========================

    private static final String className = ViewUtil.class.getName();

    private static LinearLayout.LayoutParams ll;
    private static RelativeLayout.LayoutParams rl;
    private static ViewGroup.LayoutParams vl;

    /**
     * 设置ListView GridView 高度
     *
     * @param rows          总行数
     * @param dividerHeight 分割线高度
     */
    private void setAbsListViewHight(AbsListView view, ListAdapter adapter, int rows, int dividerHeight) {

        int totalHeight = 0;
        View itemView;
        for (int i = 0; i < rows; i++) { // 只计算每项高度*行数

            itemView = adapter.getView(i, null, view);
            itemView.measure(0, 0); // 计算子项View 的宽高
            totalHeight += itemView.getMeasuredHeight(); // 统计所有子项的总高度

        }

        int height = totalHeight + (dividerHeight * (rows - 1));
        setViewGroupViewSizeHeight(view, height);

    }

}
