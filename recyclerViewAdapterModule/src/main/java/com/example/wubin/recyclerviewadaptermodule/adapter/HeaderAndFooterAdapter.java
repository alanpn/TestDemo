package com.example.wubin.recyclerviewadaptermodule.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.wubin.recyclerviewadaptermodule.R;
import com.example.wubin.recyclerviewadaptermodule.data.DataServer;
import com.example.wubin.recyclerviewadaptermodule.entity.Status;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class HeaderAndFooterAdapter extends BaseQuickAdapter<Status, BaseViewHolder> {

    public HeaderAndFooterAdapter(int dataSize) {
        super(R.layout.item_header_and_footer, DataServer.getSampleData(dataSize));
    }

    @Override
    protected void convert(BaseViewHolder helper, Status item) {
        switch (helper.getLayoutPosition() %
                3) {
            case 0:
                helper.setImageResource(R.id.iv, R.mipmap.animation_img1);
                break;
            case 1:
                helper.setImageResource(R.id.iv, R.mipmap.animation_img2);
                break;
            case 2:
                helper.setImageResource(R.id.iv, R.mipmap.animation_img3);
                break;
            default:
                break;
        }
    }


}
