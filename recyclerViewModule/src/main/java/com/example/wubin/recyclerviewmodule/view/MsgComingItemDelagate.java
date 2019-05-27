package com.example.wubin.recyclerviewmodule.view;


import com.example.wubin.recyclerviewmodule.R;
import com.example.wubin.recyclerviewmodule.util.ItemViewDelegate;
import com.example.wubin.recyclerviewmodule.util.ViewHolder;

/**
 * Created by zhy on 16/6/22.
 */
public class MsgComingItemDelagate implements ItemViewDelegate<ChatMessage> {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.main_chat_from_msg;
    }

    @Override
    public boolean isForViewType(ChatMessage item, int position) {
        return item.isComMeg();
    }

    @Override
    public void convert(ViewHolder holder, ChatMessage chatMessage, int position) {
        holder.setText(R.id.chat_from_content, chatMessage.getContent());
        holder.setText(R.id.chat_from_name, chatMessage.getName());
        holder.setImageResource(R.id.chat_from_icon, chatMessage.getIcon());
    }
}
