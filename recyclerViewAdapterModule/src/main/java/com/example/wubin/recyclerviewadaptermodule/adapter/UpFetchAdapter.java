package com.example.wubin.recyclerviewadaptermodule.adapter;


import com.example.wubin.recyclerviewadaptermodule.R;
import com.example.wubin.recyclerviewadaptermodule.base.BaseDataBindingAdapter;
import com.example.wubin.recyclerviewadaptermodule.databinding.ItemMovieBinding;
import com.example.wubin.recyclerviewadaptermodule.entity.Movie;

/**
 * Created by tysheng
 * Date: 2017/5/25 10:47.
 * Email: tyshengsx@gmail.com
 */

public class UpFetchAdapter extends BaseDataBindingAdapter<Movie, ItemMovieBinding> {
    public UpFetchAdapter() {
        super(R.layout.item_movie, null);
    }

    @Override
    protected void convert(ItemMovieBinding binding, Movie item) {
        binding.setMovie(item);
    }
}
