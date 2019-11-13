package com.wubin.testdemo.simpleViewModel;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * @author wubin
 * @description
 * @date 2019-11-11
 */

@Dao
public interface WordDao {

    //    @Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Word word);

    @Delete
    void delete(Word mWord);

    @Query("delete from word_table")
    void deleteAll();

    @Update
    void updateWord(Word word);

    @Query("update word_table set word=:mNewWord WHERE word = :mWord")
    void updateWord(String mWord, String mNewWord);

    @Query("select * from word_table order by id asc")
    LiveData<List<Word>> getWords();

    @Query("select count(*) from word_table")
    int getCounts();

    /**
     * 去重复
     */
    @Query("select distinct * from word_table order by word asc")
    List<Word> getWordsByDistinct();

    @Query("select * from word_table where word = :mWord")
    Word getWord(String mWord);
}
