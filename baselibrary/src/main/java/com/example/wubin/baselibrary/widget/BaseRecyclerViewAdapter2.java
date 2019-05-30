package com.example.wubin.baselibrary.widget;

import java.util.List;

/**
 * @author wubin
 * @description
 * @date 2019-05-29
 */
public abstract class BaseRecyclerViewAdapter2<T> extends BaseRecyclerViewAdapter<T> {

    public BaseRecyclerViewAdapter2(int resID, List<T> data) {
        super(resID, data);
    }

    @Override
    public int getItemViewType(int position) {
        return getType(position);
    }

    public abstract int getType(int position);
}
