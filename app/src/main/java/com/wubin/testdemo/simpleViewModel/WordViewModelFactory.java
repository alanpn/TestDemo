package com.wubin.testdemo.simpleViewModel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * @author wubin
 * @description
 * @date 2019-11-11
 */
public class WordViewModelFactory implements ViewModelProvider.Factory {

    private Context mContext;

    WordViewModelFactory(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(WordViewModel.class)) {
            return (T) new WordViewModel(new WordRepository(WordRoomDatabase.getWorkRoomDatabase(mContext).wordDao()));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
