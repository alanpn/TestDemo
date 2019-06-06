package com.example.wubin.baselibrary.widget.recyclerViewAdapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import java.util.List;

/**
 * @author wubin
 * @description
 * @date 2019-05-29
 */
public abstract class BaseDataBindingRecyclerViewAdapter<T> extends BaseRecyclerViewAdapter<T> {

    ViewDataBinding binding;

    public BaseDataBindingRecyclerViewAdapter(int resId, List<T> data) {
        super(resId, data);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        if (holder.getItemViewType() == BaseRecyclerViewAdapter.VIEW_TYPE_DATA_BINDING) {

            binding = (ViewDataBinding) holder.view.getTag();
            setListener(holder, position);

            convert(binding, getItem(position));
        }

    }

    @Override
    protected void convert(View view, T t) {
    }

    protected abstract void convert(ViewDataBinding binding, T t);
}
