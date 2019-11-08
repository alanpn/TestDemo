
底层实现
    Backwards compatible up to API 14
    Uses JobScheduler on devices with API 23+
    Uses a combination of BroadcastReceiver + AlarmManager on devices with API 14-22

https://blog.csdn.net/s127838498/article/details/93631031
WorkManager提供了四种工作场景：

Worker ,是最简单的一个实现。前面我们已经有了大概的了解。WorkManager 自动地在一个后台线程中允许它。
CoroutineWorker ， 是针对Kotlin 的一种实现。CoroutineWorker后台任务暴露了一个suspending（可挂起）的方法。默认运行在一个默认的Dispatcher（可重写）中。
RxWorker , 是针对RxJava2的用户设计的一个实现。
ListenableWorker ， 是Woker、CoroutineWorker、RxWorker的基类，它专门为使用异步回调的java开发者设计。

dependencies {
  def work_version = "2.2.0"

    // (Java only)
    implementation "androidx.work:work-runtime:$work_version"

    // Kotlin + coroutines
    implementation "androidx.work:work-runtime-ktx:$work_version"

    // optional - RxJava2 support
    implementation "androidx.work:work-rxjava2:$work_version"

    // optional - GCMNetworkManager support
    implementation "androidx.work:work-gcm:$work_version"

    // optional - Test helpers
    androidTestImplementation "androidx.work:work-testing:$work_version"
  }

组件里面也给两个相应的子类：OneTimeWorkRequest(任务只执行一遍)、PeriodicWorkRequest(任务周期性的执行)。

WorkRequest.Builder: 创建WorkRequest对象的帮助类。
Constraints：指定任务运行的限制条件（例如，"仅当连接到网络时"）。使用Constraint.Builder来创建Constraints,并在创建WorkRequest之前把Constraints
    传给WorkRequest.Builder的setConstraints()函数。
