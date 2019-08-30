package com.wubin.testdemo.banner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wubin.testdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luyanjun
 * @email lu.yj@sand.com.cn
 * @description
 */
public class PaletAdapter extends RecyclerView.Adapter<PaletAdapter.ViewHolder> {

    private List<PaletColor> mPaletColors = new ArrayList<>();

    public void setData(List<PaletColor> paletColors) {
        this.mPaletColors = paletColors;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_color, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PaletColor paletColor = mPaletColors.get(position);
        holder.colorView.setBackgroundColor(paletColor.getRgb());
        holder.methodTv.setText(paletColor.getMethod());
    }

    @Override
    public int getItemCount() {
        return mPaletColors != null ? mPaletColors.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        //        @BindView(R.id.view_color)
        View colorView;
        //        @BindView(R.id.tv_method)
        TextView methodTv;

        public ViewHolder(View itemView) {
            super(itemView);
            colorView = itemView.findViewById(R.id.view_color);
            methodTv = itemView.findViewById(R.id.tv_method);
        }
    }


}
