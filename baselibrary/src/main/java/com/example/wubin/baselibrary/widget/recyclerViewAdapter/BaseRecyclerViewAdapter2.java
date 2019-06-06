package com.example.wubin.baselibrary.widget.recyclerViewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.List;

import static com.example.wubin.baselibrary.activity.BaseActivity.myActivity;

/**
 * @author wubin
 * @description
 * @date 2019-05-29
 */
public abstract class BaseRecyclerViewAdapter2<T> extends BaseRecyclerViewAdapter<T> {

    public BaseRecyclerViewAdapter2(List<T> data) {
        super(-404, data);
    }

    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(myActivity).inflate(getLayoutId(viewType), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        if (holder.getItemViewType() == VIEW_TYPE_DEFAULT) {

            convert(holder, holder.view, getItem(position));
            setListener(holder, position);

        }

    }

    @Override
    protected void convert(View view, T t) {
    }

    @Override
    public int getItemViewType(int position) {
        return getType(position);
    }

    public abstract int getType(int position);

    public abstract int getLayoutId(int viewType);

    protected abstract void convert(Holder holder, View view, T t);

}
