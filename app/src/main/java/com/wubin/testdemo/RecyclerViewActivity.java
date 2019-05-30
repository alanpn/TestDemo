package com.wubin.testdemo;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.example.wubin.baselibrary.util.RecyclerViewUtil;
import com.example.wubin.baselibrary.util.ShowUtil;
import com.example.wubin.baselibrary.util.WidgetUtil;
import com.example.wubin.baselibrary.widget.BaseRecyclerViewAdapter;
import com.example.wubin.baselibrary.widget.BaseRecyclerViewAdapter2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wubin
 * @description
 * @date 2019-05-29
 */
public class RecyclerViewActivity extends BaseActivity {

    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recyclerview);

        for (int i = 0; i < 10; i++) {
            list.add("sdfsdf " + i);
        }

        RecyclerView recyclerView = RecyclerViewUtil.getLinearLayoutRecyclerView(R.id.activity_recyclerview_rv);

//        recyclerView = RecyclerViewUtil.getGridRecyclerView(R.id.activity_recyclerview_rv, 2, new Rect(1, 1, 1, 1));

        BaseRecyclerViewAdapter adapter;
//          adapter = new BaseRecyclerViewAdapter<String>(R.layout.item_recyclerview, list) {
//            @Override
//            protected void convert(BaseRecyclerViewAdapter.Holder holder, View view, String str) {
//                WidgetUtil.getTextView(view, R.id.item_recyclerview_tv, str);
//            }
//        };

        adapter = new BaseRecyclerViewAdapter2<String>(R.layout.item_recyclerview, list) {

            @Override
            public int getType(int position) {
                return position % 2 == 0 ? 0 : 1;
            }

            @Override
            protected void convert(BaseRecyclerViewAdapter.Holder holder, View view, String str) {
                if (holder.getItemViewType() == 0) {
                    WidgetUtil.getTextView(view, R.id.item_recyclerview_tv, "0000000");
                } else {
                    WidgetUtil.getTextView(view, R.id.item_recyclerview_tv, "1111111");
                }

            }
        };

        adapter.setOnItemClickListener((int position) -> {

            ShowUtil.toastShow("sdfsdfsfdf");

        }).setOnLongItemClickListener((int position) -> {

            ShowUtil.toastShow("sdfsdfsfdf");

        });

        recyclerView.setAdapter(adapter);

    }
}
