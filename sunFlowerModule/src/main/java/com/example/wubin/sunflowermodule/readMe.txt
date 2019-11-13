
https://github.com/googlesamples/android-sunflower

https://developer.android.com/jetpack/

https://clmirror.storage.googleapis.com/index.html

在build中
    Java调用Kotlin
    apply plugin: 'kotlin-android'

    用来替代findViewById的插件
    apply plugin: 'kotlin-android-extensions'

    如果你的Kotlin代码里面有使用到注解，那么需要加入这个插件（kapt 即 Kotlin annotation processing tool，Kotlin 注解处理工具的缩写）
    apply plugin: 'kotlin-kapt'

Foundation - Components for core system capabilities, Kotlin extensions and support for multidex and automated testing.
    AppCompat - Degrade gracefully on older versions of Android.
    Android KTX - Write more concise, idiomatic Kotlin code.
    Test - An Android testing framework for unit and runtime UI tests.
Architecture - A collection of libraries that help you design robust, testable, and maintainable apps. Start with classes for managing your UI component lifecycle and handling data persistence.
    Data Binding - Declaratively bind observable data to UI elements.
    Lifecycles - Create a UI that automatically responds to lifecycle events.
    LiveData - Build data objects that notify views when the underlying database changes.
    Navigation - Handle everything needed for in-app navigation.
    Room - Access your app's SQLite database with in-app objects and compile-time checks.
    ViewModel - Store UI-related data that isn't destroyed on app rotations. Easily schedule asynchronous tasks for optimal execution.
    WorkManager - Manage your Android background jobs.
UI - Details on why and how to use UI Components in your apps - together or separate
    Animations & Transitions - Move widgets and transition between screens.
    Fragment - A basic unit of composable UI.
    Layout - Lay out widgets using different algorithms.
Third party
    Glide for image loading
    Kotlin Coroutines for managing background threads with simplified code and reducing needs for callbacks

1.
    DrawerLayout（抽屉布局）
    NavigationView侧滑菜单


