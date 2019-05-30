package com.example.wubin.recyclerviewadaptermodule;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.wubin.recyclerviewadaptermodule.adapter.SectionMultipleItemAdapter;
import com.example.wubin.recyclerviewadaptermodule.base.BaseActivity;
import com.example.wubin.recyclerviewadaptermodule.data.DataServer;
import com.example.wubin.recyclerviewadaptermodule.entity.SectionMultipleItem;

import java.util.List;

/**
 * to get SectionMultipleItem you need follow two things
 * 1.create entity which extend SectionMultiEntity
 * 2.create adapter which extend BaseSectionMultiItemQuickAdapter
 */
public class SectionMultipleItemUseActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_uer);
        setBackBtn();
        setTitle("SectionMultiple Use");
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 1. create entityList which item data extend SectionMultiEntity
        List<SectionMultipleItem> mData = DataServer.getSectionMultiData();

        // create adapter which extend BaseSectionMultiItemQuickAdapter provide your headerResId
        SectionMultipleItemAdapter sectionAdapter = new SectionMultipleItemAdapter(R.layout.def_section_head, mData);
        sectionAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SectionMultipleItem item = (SectionMultipleItem) adapter.getData().get(position);
                if (view.getId() == R.id.card_view) {// 获取主体item相应数据给后期使用
                    if (item.getVideo() != null) {
                        Toast.makeText(SectionMultipleItemUseActivity.this, item.getVideo().getName(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(SectionMultipleItemUseActivity.this, "OnItemChildClickListener " + position, Toast.LENGTH_LONG).show();
                }
            }
        });
        mRecyclerView.setAdapter(sectionAdapter);
    }
}
