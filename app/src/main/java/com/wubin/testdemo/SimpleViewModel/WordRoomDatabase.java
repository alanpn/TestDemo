package com.wubin.testdemo.simpleViewModel;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * @author wubin
 * @description
 * @date 2019-11-11
 */

@Database(entities = Word.class, version = 1, exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

    public abstract WordDao wordDao();

    // volatile 是一个类型修饰符。volatile 的作用是作为指令关键字,确保本条指令不会因编译器的优化而省略
    private static volatile WordRoomDatabase INSTANCE;

    static WordRoomDatabase getWorkRoomDatabase(final Context context) {
        if (null == INSTANCE) {
            synchronized (WordRoomDatabase.class) {
                if (null == INSTANCE) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_table").build();
                }
            }
        }
        return INSTANCE;
    }


}
