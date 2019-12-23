package com.wubin.testdemo.workManagerModel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.wubin.baselibrary.util.ShowUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wubin
 * @description
 * @date 2019-11-01
 */
public class TestWorker2 extends Worker {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");// HH:mm:ss

    public TestWorker2(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        //------------- 获得值 -------------

//    Data    There is a maximum size limit of 10KB for Data objects.
//        Data inputData = getInputData();
//        if (null != inputData) {
//            String url = inputData.getString("url");
//            ShowUtil.print(url);
//        }

        //------------- do something -------------

        ShowUtil.print("TestWork2 ", simpleDateFormat.format(new Date()));


        //------------- 返回 -------------

//        finished successfully via Result.success()
//        failed via Result.failure()
//        needs to be retried at a later time via Result.retry()

        // Optionally, you can open the Device File Explorer in Android Studio
        // and navigate to data/data/com.example.background/files/blur_filter_outputs/<URI>
//        Data outputData = new Data.Builder().putString("state", "success").build();
//        return Result.success(outputData);

        return Result.success();
    }
}
