package com.example.wubin.baselibrary.widget;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.example.wubin.baselibrary.activity.BaseActivity.myActivity;

/**
 * @author wubin
 * @description
 * @date 2019-05-29
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewAdapter.Holder> {

    private int mResID;
    private List<T> mData = new ArrayList<>();

    private OnItemClickListener onItemClickListener;
    private OnLongItemClickListener onLongItemClickListener;

    public BaseRecyclerViewAdapter(int resID, List<T> data) {
        this.mResID = resID;
        setData(data);
    }

    public BaseRecyclerViewAdapter init() {
        return this;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public T getItem(final int position) {
        return mData.get(position);
    }

    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(myActivity).inflate(mResID, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerViewAdapter.Holder holder, final int position) {

        convert(holder, holder.view, getItem(position));
        setListener(holder, position);

    }

    private void setListener(@NonNull Holder holder, final int position) {

        setLongItemClick(holder, position);

        setItemClick(holder, position);

    }

    private void setItemClick(@NonNull Holder holder, final int position) {

        if (null == onItemClickListener) return;

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position);
            }
        });

    }

    private void setLongItemClick(@NonNull Holder holder, final int position) {

        if (null == onLongItemClickListener) return;

        holder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onLongItemClickListener.onLongItemClick(position);
                return false;
            }
        });

    }

    public class Holder extends RecyclerView.ViewHolder {

        View view;

        Holder(View view) {
            super(view);
            this.view = view;
        }
    }

    public void setData(List<T> data) {

        mData.clear();
        if (null != data && data.size() != 0) {
            mData.addAll(data);
        }

        notifyDataSetChanged();
    }

    protected abstract void convert(BaseRecyclerViewAdapter.Holder holder, View view, T t);

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public BaseRecyclerViewAdapter setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    public interface OnLongItemClickListener {
        void onLongItemClick(int position);
    }

    public BaseRecyclerViewAdapter setOnLongItemClickListener(OnLongItemClickListener onItemClickListener) {
        this.onLongItemClickListener = onLongItemClickListener;
        return this;
    }

}
