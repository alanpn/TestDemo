package com.wubin.testdemo.simpleViewModel;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * @author wubin
 * @description
 * @date 2019-11-11
 */
public class WordRepository {

    private WordDao mWordDao;

    WordRepository(WordDao dao) {
        this.mWordDao = dao;
    }

    public void deleteAll() {
        new DeleteAllAsyncTask(mWordDao).execute();
    }

    public void delete(Word word) {
        new DeleteAsyncTask(mWordDao).execute(word);
    }

    public void insert(Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }

    public void updateWord(Word word) {
        new UpdateAsyncTask(mWordDao).execute(word);
    }

    /**
     * 本来都需要线程 但查询有liveData
     */
    public LiveData<List<Word>> getWords() {
        return mWordDao.getWords();
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {

        private WordDao mAsyncTaskDao;

        DeleteAllAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        DeleteAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }


        @Override
        protected Void doInBackground(Word... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        UpdateAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }


        @Override
        protected Void doInBackground(Word... params) {
            mAsyncTaskDao.updateWord(params[0]);
            return null;
        }
    }

}
