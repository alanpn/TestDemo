package com.wubin.testdemo.workManagerModel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

/**
 * @author wubin
 * @description
 * @date 2019-11-01
 */
public class TestWorker3 extends Worker {

    public TestWorker3(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        setProgressAsync(new Data.Builder().putInt("PROGRESS", 0).build());
    }

    @NonNull
    @Override
    public Result doWork() {

        try {
            // Doing work.
            Thread.sleep(1000L);
        } catch (InterruptedException exception) {
            // ... handle exception
        }
        // Set progress to 100 after you are done doing your work.
        setProgressAsync(new Data.Builder().putInt("PROGRESS", 100).build());

        return Result.success();
    }
}
