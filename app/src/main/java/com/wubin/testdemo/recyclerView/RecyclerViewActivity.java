package com.wubin.testdemo.recyclerView;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.example.wubin.baselibrary.util.RecyclerViewUtil;
import com.example.wubin.baselibrary.util.ShowUtil;
import com.example.wubin.baselibrary.widget.recyclerViewAdapter.BaseDataBindingRecyclerViewAdapter;
import com.example.wubin.baselibrary.widget.recyclerViewAdapter.BaseRecyclerViewAdapter;
import com.wubin.testdemo.BR;
import com.wubin.testdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author wubin
 * @description
 * @date 2019-05-29
 */
public class RecyclerViewActivity extends BaseActivity {

    private List<UserPo> list = new ArrayList<>();

    BaseRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recyclerview);
        ButterKnife.bind(this);

        for (int i = 0; i < 10; i++) {
            list.add(new UserPo("xx " + i));
        }

        RecyclerView recyclerView;

        // 普通 linearlayout
        recyclerView = RecyclerViewUtil.getLinearLayoutRecyclerView(R.id.activity_recyclerview_rv);

        // gridlayout
//        recyclerView = RecyclerViewUtil.getGridRecyclerView(R.id.activity_recyclerview_rv, 3);

        // 可合并单元格
//        recyclerView = RecyclerViewUtil.getGridRecyclerView(R.id.activity_recyclerview_rv, 4,
//                new GridLayoutManager.SpanSizeLookup() {
//                    @Override
//                    public int getSpanSize(int position) {
//
//                        if (position == 0 || position == 1) {
//                            return 2;
//                        } else if (position == 2) {
//                            return 4;
//                        } else {
//                            return 1;
//                        }
//
//                    }
//                });

        // 带分割线
//        recyclerView = RecyclerViewUtil.getGridRecyclerView(R.id.activity_recyclerview_rv, 2, new Rect(1, 1, 1, 1));

        // 普通版
//        adapter = new BaseRecyclerViewAdapter<UserPo>(R.layout.item_recyclerview, list) {
//            @Override
//            protected void convert(View view, UserPo po) {
//                WidgetUtil.getButton(view, R.id.item_recyclerview_tv, po.name);
//            }
//        };

        // 不同viewType
//        adapter = new BaseRecyclerViewAdapter2<UserPo>(list) {
//
//            @Override
//            public int getType(int position) {
//                return position % 2 == 0 ? 0 : 1;
//            }
//
//            @Override
//            public int getLayoutId(int viewType) {
//
//                if (viewType == 0) {
//                    return R.layout.item_recyclerview;
//                }
//
//                return R.layout.item_recyclerview2;
//            }
//
//            @Override
//            protected void convert(Holder holder, View view, UserPo po) {
//                if (holder.getItemViewType() == 0) {
//                    WidgetUtil.getTextView(view, R.id.item_recyclerview_tv, "0000000");
//                } else {
//                    WidgetUtil.getTextView(view, R.id.item_recyclerview_tv, "1111111");
//                }
//
//            }
//        };

        // dataBinding
        adapter = new BaseDataBindingRecyclerViewAdapter<UserPo>(R.layout.item_recyclerview, list) {

            @Override
            protected void convert(ViewDataBinding binding, UserPo po) {
                binding.setVariable(BR.name, po.getName());
            }

        };

        // 开启动画
//        adapter.openAnimation(new AlphaInAnimation());

        // 头部 尾部
//        adapter.addHeadView(R.layout.recyclerview_head);
//        adapter.addFooterView(R.layout.recyclerview_footer);

        adapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public <T> void onItemClick(T t) {
                ShowUtil.toastShow(t);
            }
        });
        adapter.setOnItemClickListener2(new BaseRecyclerViewAdapter.OnItemClickListener2() {
            @Override
            public <T> void onItemClick(View view, T t) {
                if (view.getId() == R.id.item_recyclerview_tv) {
                    ShowUtil.toastShow("is button");
                } else {
                    ShowUtil.toastShow("isn't button");
                }
            }
        });
        adapter.setOnLongItemClickListener(new BaseRecyclerViewAdapter.OnLongItemClickListener() {
            @Override
            public <T> void onLongItemClick(T t) {
                ShowUtil.toastShow(t);
            }
        });

        // 是否可以左右滑动
        adapter.setDragAndSwipeEnable(recyclerView);

        adapter.setDiffDataEnable();

        recyclerView.setAdapter(adapter);

    }

    @OnClick(R.id.activity_recyclerview_add)
    void add() {

        // 数据 必须是新增 或 全新的 diff 才起作用
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new UserPo("bb " + i));
        }

//        list.add(new UserPo("hhhh"));


        adapter.setData(list);

    }
}
