

管理fragment跳转

1.res 新建 navigation文件夹 新建 nav_fragment.xml文件

    <?xml version="1.0" encoding="utf-8"?>
    <navigation xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:id="@+id/res_navigation"
        app:startDestination="@id/firstFragment">

        <fragment
            android:id="@+id/firstFragment"
            android:name="com.wubin.testdemo.navigation.NavigationFristFragment" />

        <fragment
            android:id="@+id/secondFragment"
            android:name="com.wubin.testdemo.navigation.NavigationSecondFragment" />

        <fragment
            android:id="@+id/thirdFragment"
            android:name="com.wubin.testdemo.navigation.NavigationThirdFragment" />

        <action
            android:id="@+id/to_firstFragment"
            app:destination="@id/firstFragment" />

        <action
            android:id="@+id/to_secondFragment"
            app:destination="@id/secondFragment" />

        <action
            android:id="@+id/to_thirdFragment"
            app:destination="@id/thirdFragment" />

    </navigation>

    startDestination 开始时的fragment
    以下下是fragment的配置 和 action跳转的配置

2.NavigationActivity

    app:defaultNavHost="true" 此属性可确保您NavHostFragment拦截系统返回按钮也可以重写AppCompatActivity.onSupportNavigateUp()方法:

    layout.xml
        fragment name必须写 navGraph部署配置文件
        <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
            xmlns:app="http://schemas.android.com/apk/res-auto"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical">

            <fragment
                android:id="@+id/fragment"
                android:name="androidx.navigation.fragment.NavHostFragment"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                app:defaultNavHost="true"
                app:navGraph="@navigation/nav_fragment" />

        </LinearLayout>

    @Override
    public boolean onSupportNavigateUp() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment);
        return NavHostFragment.findNavController(fragment).navigateUp();
    }

3.fragment 跳转
     Navigation.findNavController(view).navigate(R.id.to_secondFragment, new Bundle());
