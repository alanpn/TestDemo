



==========在aar中 (如sdkApp)

因为aar类似于jar包模式 所以不是一个独立的应用

1.在 app/build.gradle 中

    apply plugin: 'com.android.application' 改成 apply plugin: 'com.android.library'

    在defaultConfig 中 去掉 applicationId "你的额包名"

2.在manifest 中

    去掉
        android:name="你的application的名字"
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />

    添加
        <action android:name="android.intent.action.VIEW" />
        <category android:name="android.intent.category.DEFAULT" />
        <category android:name="android.intent.category.BROWSABLE" />

3.代码中

    不能使用ButterKnife 绑定控件 控件必须自己findViewById

    不能使用switch/case 只能使用if/else

    App.java 无法使用 请将原本写在里面的初始化操作移到起始页面中


=========在使用的app中 (如customerApp)

    implementation(name: '你要集成的aar名字', ext: 'aar')

