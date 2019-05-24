package com.wubin.testdemo.lambda;

import android.os.Bundle;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.example.wubin.baselibrary.util.ShowUtil;
import com.wubin.testdemo.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaActivity extends BaseActivity {

    private final int ID_JNI = R.id.activity_main_test1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        // listener();

        // runable();

        // sort();

    }

    private void sort() {

        List<Person> list = new ArrayList<>();
        list.add(new Person("1", "2"));
        list.add(new Person("3", "42"));

        Collections.sort(list, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.getName().compareTo(p2.getName());
            }
        });

        Collections.sort(list,
                (Person p1, Person p2) -> p1.getName().compareTo(p2.getName()));

        Collections.sort(list,
                (p1, p2) -> p1.getName().compareTo(p2.getName()));

    }

    private void runable() {

        Runnable r = () -> ShowUtil.print("xxx");

        r = new Runnable() {
            @Override
            public void run() {
                ShowUtil.print("xxx");
            }
        };

        r.run();

    }

    private void listener() {

        findViewById(ID_JNI).setOnClickListener(e -> ShowUtil.print("xxx"));

        findViewById(ID_JNI).setOnClickListener(v -> {
            ShowUtil.print("xx");
            ShowUtil.print("xx");
        });

    }

}

class Person {

    private String name;
    private String age;

    public Person(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
