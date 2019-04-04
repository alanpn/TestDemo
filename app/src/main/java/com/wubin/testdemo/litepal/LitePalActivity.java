package com.wubin.testdemo.litepal;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.wubin.testdemo.R;

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

    private final int ID_FIND = R.id.find;
    private final int ID_FIND_All = R.id.find_all;
    private final int ID_INSERT = R.id.insert;
    private final int ID_UPDATE = R.id.update;
    private final int ID_UPDATE2 = R.id.update2;
    private final int ID_UPDATE3 = R.id.update3;
    private final int ID_DELETE = R.id.delete;
    private final int ID_DELETE2 = R.id.delete2;

    @BindView(R.id.tv)
    TextView tv;

    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.litepal);
        ButterKnife.bind(this);

    }

    @OnClick({ID_FIND, ID_FIND_All, ID_INSERT, ID_UPDATE, ID_UPDATE2, ID_UPDATE3, ID_DELETE, ID_DELETE2})
    void onClick(View view) {
        switch (view.getId()) {
            case ID_FIND:

                student = LitePal.find(Student.class, 1);
                if (null != student) {
                    tv.setText(student.toString());
                }

                break;

            case ID_FIND_All:

                String str = "";
                List<Student> list = LitePal.findAll(Student.class);
                if (null == list) return;
                for (Student stu : list) {
                    str += stu.toString() + "\n";
                }

                tv.setText(str);

                break;

            case ID_INSERT:

                student = new Student();
                student.id = "1";
                student.name = "aaa";
                student.save();

                break;

            case ID_UPDATE:

                student = LitePal.find(Student.class, 1);
                student.name = "bbb";
                student.save();

                break;

            case ID_UPDATE2:

                student = new Student();
                student.name = "ccc";
                student.update(1);

                break;

            case ID_UPDATE3:

                student = new Student();
                student.name = "ddd";
                student.updateAll("id = ?", "1");

                break;

            case ID_DELETE:

                LitePal.delete(Student.class, 1);

                break;

            case ID_DELETE2:

                LitePal.deleteAll(Student.class, "id = ?", "1");

                break;
        }
    }


}
