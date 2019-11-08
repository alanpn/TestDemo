package com.wubin.testdemo.WorkManagerModel;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.example.wubin.baselibrary.util.ShowUtil;
import com.wubin.testdemo.R;

import java.util.concurrent.TimeUnit;

/**
 * @author wubin
 * @description
 * @date 2019-11-01
 */
public class WorkManagerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        setContentView(R.layout.activity_main);

//        约束条件();
//
//        可延迟执行();
//
//        输入参数();
//
//        带tag();
//
//        OneTimeWorkRequest workRequest = 只执行一次();
//
//        定时任务();

//        执行顺序();

//        观察者();

        进度();

    }

    private void 进度() {

        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(TestWorker3.class).build();
        WorkManager.getInstance(this).enqueue(workRequest);
        WorkManager.getInstance(getApplicationContext())
                // requestId is the WorkRequest id
                .getWorkInfoByIdLiveData(workRequest.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(@Nullable WorkInfo workInfo) {
                        if (workInfo != null) {
                            Data progress = workInfo.getProgress();
                            int value = progress.getInt("PROGRESS", 0);
                            // Do something with progress
                            ShowUtil.print(value);
                        }
                    }
                });

    }

    private void 观察者() {

        // 执行步骤 1. work enqueued 2. work running 3. work succeeded
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(TestWorker.class).build();
        WorkManager.getInstance(this).enqueue(workRequest);
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(workRequest.getId()).observe(this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {
                switch (workInfo.getState()) {
                    case FAILED:
                        ShowUtil.print("work failed");
                        break;

                    case BLOCKED:
                        ShowUtil.print("work blocked");
                        break;

                    case RUNNING:
                        ShowUtil.print("work running");
                        break;

                    case ENQUEUED:
                        ShowUtil.print("work enqueued");
                        break;

                    case CANCELLED:
                        ShowUtil.print("work cancelled");
                        break;

                    case SUCCEEDED:
                        ShowUtil.print("work succeeded");
                        break;
                }
            }
        });
    }

    private void 执行顺序() {

        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(TestWorker.class).build();
        OneTimeWorkRequest workRequest2 = new OneTimeWorkRequest.Builder(TestWorker2.class).build();

        // 单个
        WorkManager.getInstance(this).beginWith(workRequest2).then(workRequest).enqueue();

        // 多个 但好像 workRequest 不能重复
//        WorkManager.getInstance(this).beginWith(Arrays.asList(workRequest2)).then(Arrays.asList(workRequest)).enqueue();

    }

    /**
     * 定时任务 最小15分钟左右轮询一次
     */
    private void 定时任务() {

        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(TestWorker.class, 15, TimeUnit.MINUTES).build();

        WorkManager.getInstance(this).enqueue(workRequest);

    }

    private void 只执行一次() {

        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(TestWorker.class).build();

        WorkManager.getInstance(this).enqueue(workRequest);

    }

    /**
     * 带tag 的约束条件 可以被 cancel 掉
     */
    private void 带tag() {

        Constraints constraints = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();

        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(TestWorker.class)
                .setConstraints(constraints).addTag("123").build();

        WorkManager.getInstance(this).enqueue(workRequest);

        // 可以被cancel tag
        findViewById(R.id.activity_main_test1).setOnClickListener(view -> {

            WorkManager.getInstance(this).cancelAllWorkByTag("123");

        });
    }

    private void 输入参数() {

        Data inputData = new Data.Builder().putString("url", "http://www.baidu.com").build();

        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(TestWorker.class).setInputData(inputData).build();

        WorkManager.getInstance(this).enqueue(workRequest);

    }

    /**
     * 最少10分钟 多次执行?
     */
    private void 可延迟执行() {

        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(TestWorker.class)
                .setInitialDelay(10, TimeUnit.MINUTES).build();

        WorkManager.getInstance(this).enqueue(workRequest);

    }

    private void 约束条件() {

//        Constraints constraints = new Constraints.Builder()
//                .setRequiredNetworkType(NetworkType.CONNECTED) // 判断网络状态
//                .setRequiresBatteryNotLow(true) // 不在电量不足时执行
//                .setRequiresCharging(true) // 在充电时执行
//                .setRequiresStorageNotLow(true) // 不在存储容量不足时执行
//                .setRequiresDeviceIdle(true) // 在待机状态执行
//                .setRequiresDeviceIdle(true) // 手机是否空闲
//                .build();

        // 约束条件 有网络才执行
        Constraints constraints = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();

        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(TestWorker.class).setConstraints(constraints).build();

        WorkManager.getInstance(this).enqueue(workRequest);

    }
}
