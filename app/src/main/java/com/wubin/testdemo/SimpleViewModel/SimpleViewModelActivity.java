package com.wubin.testdemo.simpleViewModel;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.example.wubin.baselibrary.util.ShowUtil;
import com.wubin.testdemo.R;
import com.wubin.testdemo.databinding.ActivityJetpackBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author wubin
 * @description
 * @date 2019-11-05
 */
public class SimpleViewModelActivity extends BaseActivity {

    private WordViewModel mViewModel;

    private List<Word> mWords = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityJetpackBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_jetpack);

//        setContentView(R.layout.activity_jetpack);

        ButterKnife.bind(this);

//        WordViewModel viewModel = new ViewModelProvider(this).get(WordViewModel.class);
        mViewModel = new ViewModelProvider(this, new WordViewModelFactory(this)).get(WordViewModel.class);
        binding.setModule(mViewModel);

//        binding.setName("1232");

    }

    @OnClick(R.id.activity_main_test1)
    void getWords() {
        mViewModel.getWords().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                mWords.clear();
                mWords.addAll(words);
                ShowUtil.print(words);
            }
        });
    }

    @OnClick(R.id.activity_main_test2)
    void insert() {
        String word = "word " + new Random().nextInt(100);
        mViewModel.insert(new Word(word));
    }

    @OnClick(R.id.activity_main_test3)
    void dealAll() {
        mViewModel.deleteAll();
    }

    @OnClick(R.id.activity_main_test4)
    void update() {
        if (null != mWords && mWords.size() != 0) {
            Word word = mWords.get(0);
            word.setWord("word33");
            mViewModel.updateWord(word);
        }
    }

    @OnClick(R.id.activity_main_test5)
    void delete() {
        if (null != mWords && mWords.size() != 0) {
            mViewModel.delete(mWords.get(0));
        }
    }


}
