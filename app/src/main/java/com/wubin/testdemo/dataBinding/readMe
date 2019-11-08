

1 添加
android {
    dataBinding {
        enabled = true
    }
}


2.
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android">

    <data>

        <!-- 导包 -->
        <import type="com.example.wubin.baselibrary.util.MoneyUtil" />

        <variable
            name="user"
            type="com.wubin.testdemo.dataBinding.UserPo" />
    </data>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:gravity="center_horizontal"
        android:orientation="vertical">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@{user.name}" />

        <!-- 默认值 -->
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@{user.name,default=defaultValue}" />

        <!-- 带id设值  -->
        <TextView
            android:id="@+id/tv"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content" />

        <!-- 使用类方法 -->
        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@{MoneyUtil.getCurrency(String.valueOf(user.age))}" />

        <!-- 复制 -->
        <LinearLayout
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:orientation="horizontal">

            <EditText
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="@={user.pass}" />

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginLeft="10dp"
                android:text="复制 : " />

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginLeft="5dp"
                android:text="@{user.pass}" />

        </LinearLayout>

        <Button
            android:id="@+id/activity_data_binding_btn"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="btn" />

        <FrameLayout
            android:id="@+id/fl"
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />

    </LinearLayout>
</layout>


3.activity
    UserPo user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ActivityDataBindingBinding 通过layout名字生成
        // 已经包含 setContentView 不需要再单独设置
        ActivityDataBindingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
        user = new UserPo("name", "pass", 18);
        binding.setUser(user);

        // 通过id设值
        binding.tv.setText("AA");

        ButterKnife.bind(this);

        getSupportFragmentManager().beginTransaction().add(R.id.fl, new MyFrgment()).commitAllowingStateLoss();

    }

    @OnClick(R.id.activity_data_binding_btn)
    void onClick(View view) {
        user.setName("sdfsdfsd");
    }

4.
public class MyFrgment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ActivityDataBindingBinding binding = DataBindingUtil.inflate(inflater, R.layout.activity_data_binding, container, false);

        UserPo user = new UserPo("name1", "pass1", 181);
        binding.setUser(user);

        binding.tv.setText("BB");

        return binding.getRoot();
    }
}

5
通过 @Bindable 进行注册  notifyPropertyChanged(BR.name); 通知xml 动态修改值
public class UserPo extends BaseObservable {

    String name;

    String pass;

    int age;

    public UserPo(String name, String pass, int age) {
        this.name = name;
        this.pass = pass;
        this.age = age;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
        notifyPropertyChanged(BR.pass);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}