package com.wubin.testdemo.NestedScrollParentView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.wubin.testdemo.R;

/**
 * @author wubin
 * @description
 * @date 2019-04-29
 */
public class NestedScrollParentViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_newsted);

        RecyclerView rv = findViewById(R.id.list);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View item = LayoutInflater.from(NestedScrollParentViewActivity.this)
                        .inflate(R.layout.recyclerview_item, parent, false);
                return new Holder(item);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                Holder h = (Holder) holder;
                h.textView.setText("item " + position);
            }

            @Override
            public int getItemCount() {
                return 100;
            }

            class Holder extends RecyclerView.ViewHolder {
                TextView textView;

                public Holder(View itemView) {
                    super(itemView);
                    textView = (TextView) itemView.findViewById(R.id.text);
                }
            }
        });
    }

}
