package com.wubin.testdemo.simpleViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.wubin.baselibrary.util.ShowUtil;

import java.util.List;

/**
 * @author wubin
 * @description
 * @date 2019-11-05
 */
public class WordViewModel extends ViewModel {

    private WordRepository mWordRepository;

    WordViewModel(WordRepository repository) {
        this.mWordRepository = repository;
    }

    public LiveData<List<Word>> getWords() {
//        liveData.setValue(Arrays.asList(new UserPo("1", "haha"), new UserPo("2", "hehe")));
        return mWordRepository.getWords();
    }

    public void insert(Word word) {
        mWordRepository.insert(word);
    }

    public void deleteAll() {
        mWordRepository.deleteAll();
    }

    public void delete(Word word) {
        mWordRepository.delete(word);
    }

    public void updateWord(Word word) {
        mWordRepository.updateWord(word);
    }

    public void show() {
        ShowUtil.print("xxxx");
    }

}
