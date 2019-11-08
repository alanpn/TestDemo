package com.wubin.testdemo.animation;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.wubin.testdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimationLayoutActivity extends BaseActivity {

    @BindView(R.id.activity_animation_layout_list)
    ListView lv;

    private List<String> list = new ArrayList<>();

    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_animation_layout);
        ButterKnife.bind(this);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);

        // 在代码中设置
//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_item);
//        LayoutAnimationController controller = new LayoutAnimationController(animation);
//        controller.setDelay(0.3f);
//        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
//        lv.setLayoutAnimation(controller);
    }

    @OnClick(R.id.activity_animation_layout_btn)
    public void onClick(View v) {
        if (0 != list.size()) {
            list.clear();
        } else {
            for (int i = 0; i < 10; i++) {
                list.add(i + "结束两地分居两色风景是否");
            }
        }
        lv.startLayoutAnimation();
        adapter.notifyDataSetChanged();
    }

}
