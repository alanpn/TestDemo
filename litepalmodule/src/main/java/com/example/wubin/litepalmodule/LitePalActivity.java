package com.example.wubin.litepalmodule;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.example.wubin.baselibrary.util.ShowUtil;

import org.litepal.LitePal;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author wubin
 * @description
 * @date 2019/4/1
 */
public class LitePalActivity extends BaseActivity {

    @BindView(R2.id.tv)
    TextView tv;

    Student student;

    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.litepal);
        ButterKnife.bind(this);

    }

    @OnClick({R2.id.find, R2.id.find_all, R2.id.insert, R2.id.update, R2.id.update2, R2.id.update3, R2.id.delete, R2.id.delete2})
    void onClick(View view) {

        i = view.getId();

        if (i == R.id.find) {

            student = LitePal.find(Student.class, 1);
            if (null != student) {
                tv.setText(student.toString());
            }

        } else if (i == R.id.find_all) {

            String str = "";
            List<Student> list = LitePal.findAll(Student.class);
            if (null == list) return;
            for (Student stu : list) {
                str += stu.toString() + "\n";
            }

            tv.setText(str);

        } else if (i == R.id.insert) {

            student = new Student();
            student.no = "1";
            student.name = "aaa";
            boolean saveFlag = student.save();
            ShowUtil.print(saveFlag);

        } else if (i == R.id.update) {

            student = LitePal.find(Student.class, 1);
            student.name = "bbb";
            student.save();


        } else if (i == R.id.update2) {
            student = new Student();
            student.name = "ccc";
            student.update(1);


        } else if (i == R.id.update3) {
            student = new Student();
            student.name = "ddd";
            student.updateAll("id = ?", "1");


        } else if (i == R.id.delete) {
            LitePal.delete(Student.class, 1);


        } else if (i == R.id.delete2) {
            LitePal.deleteAll(Student.class, "id = ?", "1");


        }
    }


}
