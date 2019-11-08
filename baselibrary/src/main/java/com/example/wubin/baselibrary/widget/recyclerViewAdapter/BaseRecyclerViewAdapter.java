package com.example.wubin.baselibrary.widget.recyclerViewAdapter;

import android.animation.Animator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wubin.baselibrary.R;
import com.example.wubin.baselibrary.util.WidgetUtil;
import com.example.wubin.baselibrary.widget.recyclerViewAdapter.animation.AlphaInAnimation;
import com.example.wubin.baselibrary.widget.recyclerViewAdapter.animation.BaseAnimation;
import com.example.wubin.baselibrary.widget.recyclerViewAdapter.animation.ScaleInAnimation;
import com.example.wubin.baselibrary.widget.recyclerViewAdapter.animation.SlideInBottomAnimation;
import com.example.wubin.baselibrary.widget.recyclerViewAdapter.animation.SlideInLeftAnimation;
import com.example.wubin.baselibrary.widget.recyclerViewAdapter.animation.SlideInRightAnimation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.wubin.baselibrary.activity.BaseActivity.myActivity;

/**
 * @author wubin
 * @description
 * @date 2019-05-29
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<Holder> {

    private int mResID;
    private List<T> mData = new ArrayList<>();

    public BaseRecyclerViewAdapter(int resID, List<T> data) {

        this.mResID = resID;
        setData(data);

    }

    public BaseRecyclerViewAdapter init() {
        return this;
    }

    @Override
    public int getItemCount() {

        if (isShowEmptyView) return 1;

        int size = mData.size();
        if (isShowHeadView) size += 1;
        if (isShowFootView) size += 1;
        return size;

    }

    private T getItem(int position) {

        if (isShowHeadView) position -= 1;
        return mData.get(position);

    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {

            if (isShowEmptyView) return VIEW_TYPE_EMPTY;
            if (isShowHeadView) return VIEW_TYPE_HEAD;

        }

        if (isShowFootView && position == mData.size() + 1) return VIEW_TYPE_FOOTER;

        if (isShowDataBing) return VIEW_TYPE_DATA_BINDING;

        return VIEW_TYPE_DEFAULT;

    }

    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;

        switch (viewType) {

            case VIEW_TYPE_HEAD:

                if (null == mHeadView) mHeadView = WidgetUtil.createView(mHeadViewId, parent);

                view = mHeadView;

                break;

            case VIEW_TYPE_FOOTER:

                if (null == mFooterView) mFooterView = WidgetUtil.createView(mFooterViewID, parent);

                view = mFooterView;

                break;

            case VIEW_TYPE_EMPTY:

                if (null == mEmptyView) mEmptyView = WidgetUtil.createView(mEmptyViewID, parent);

                view = mEmptyView;

                break;

            case VIEW_TYPE_DATA_BINDING:

                ViewDataBinding binding = DataBindingUtil.inflate(
                        LayoutInflater.from(myActivity), mResID, parent, false);
                view = binding.getRoot();
                view.setTag(binding);

                break;

            default:
                view = WidgetUtil.createView(mResID, parent);

        }

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        if (holder.getItemViewType() == VIEW_TYPE_DEFAULT) {

            convert(holder.view, getItem(position));
            setListener(holder, position);

        }

    }

    protected abstract void convert(View view, T t);


    //=====================================
    // listener
    //=====================================

    private OnItemClickListener onItemClickListener;
    private OnItemClickListener2 onItemClickListener2;
    private OnLongItemClickListener onLongItemClickListener;

    void setListener(@NonNull Holder holder, final int position) {

        setItemClick(holder, position);

        setItemClick2(holder, position);

        setLongItemClick(holder, position);

    }

    private void setItemClick(@NonNull Holder holder, final int position) {

        if (null == onItemClickListener) return;

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(getItem(position));
            }
        });

    }

    private void setItemClick2(@NonNull final Holder holder, final int position) {

        if (null == onItemClickListener2) return;

        View view;
        ViewGroup group = (ViewGroup) holder.view;
        for (int i = 0, size = group.getChildCount(); i < size; i++) {
            view = group.getChildAt(i);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener2.onItemClick(v, getItem(position));
                }
            });

        }

    }

    private void setLongItemClick(@NonNull Holder holder, final int position) {

        if (null == onLongItemClickListener) return;

        holder.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onLongItemClickListener.onLongItemClick(getItem(position));
                return false;
            }
        });

    }

    public interface OnItemClickListener {
        <T> void onItemClick(T t);
    }

    public BaseRecyclerViewAdapter setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    public interface OnItemClickListener2 {
        <T> void onItemClick(View view, T t);
    }

    public BaseRecyclerViewAdapter setOnItemClickListener2(OnItemClickListener2 onItemClickListener2) {
        this.onItemClickListener2 = onItemClickListener2;
        return this;
    }

    public interface OnLongItemClickListener {
        <T> void onLongItemClick(T t);
    }

    public BaseRecyclerViewAdapter setOnLongItemClickListener(OnLongItemClickListener onItemClickListener) {
        this.onLongItemClickListener = onLongItemClickListener;
        return this;
    }

    //=====================================
    // head & footer & empty
    //=====================================

    protected final int VIEW_TYPE_DEFAULT = 0x00000111;
    private final int VIEW_TYPE_HEAD = 0x00000222;
    private final int VIEW_TYPE_FOOTER = 0x00000333;
    private final int VIEW_TYPE_EMPTY = 0x00000444;

    private boolean isShowHeadView, isShowFootView, isShowEmptyView;
    private int mHeadViewId, mFooterViewID, mEmptyViewID;
    private View mHeadView, mFooterView, mEmptyView;

    public void addHeadView(int layoutID) {

        isShowHeadView = true;
        this.mHeadViewId = layoutID;

        notifyItemInserted(0);

    }

    public View addHeadView(RecyclerView recyclerView, int layoutID) {

        addHeadView(layoutID);

        mHeadView = WidgetUtil.createView(layoutID, (ViewGroup) recyclerView.getParent());
        return mHeadView;

    }

    public void removeHeadView() {

        if (!isShowHeadView) return;

        isShowHeadView = false;
        notifyItemRemoved(0);

    }

    public void addFooterView(int layoutID) {

        isShowFootView = true;
        this.mFooterViewID = layoutID;

        int pos = mData.size();
        if (isShowHeadView) pos += 1;
        notifyItemInserted(pos);

    }

    public View addFooterView(RecyclerView recyclerView, int layoutID) {

        addFooterView(layoutID);

        mFooterView = WidgetUtil.createView(layoutID, (ViewGroup) recyclerView.getParent());
        return mFooterView;

    }

    public void removeFooterView() {

        if (!isShowFootView) return;

        isShowFootView = false;

        int pos = mData.size();
        if (isShowHeadView) pos += 1;
        notifyItemRemoved(pos);

    }

    public void addEmptyView(int layoutID) {

        isShowEmptyView = true;
        this.mEmptyViewID = layoutID;

        notifyItemInserted(0);

    }

    public View addEmptyView(RecyclerView recyclerView, int layoutID) {

        addEmptyView(layoutID);

        mEmptyView = WidgetUtil.createView(layoutID, (ViewGroup) recyclerView.getParent());
        return mEmptyView;

    }

    public void removeEmptyView() {

        if (!isShowEmptyView) return;

        isShowEmptyView = false;
        notifyItemRemoved(0);

    }

    //=====================================
    // DataBingd
    //=====================================

    protected static final int VIEW_TYPE_DATA_BINDING = 0x00000555;

    private boolean isShowDataBing;

    public void setDataBingEnable() {
        isShowDataBing = true;
    }

    //=====================================
    // animation
    //=====================================

    private boolean isAnimationEnable = false; // 是否开启动画
    private BaseAnimation mAnimation;

    private int mDuration = 300;
    private Interpolator mInterpolator = new LinearInterpolator();

    public void openAnimation(BaseAnimation animation) {

        isAnimationEnable = true;
        this.mAnimation = animation;

    }

    public void openAnimation(MyAnimation animation) {

        isAnimationEnable = true;

        switch (animation) {
            case Alpha:
                this.mAnimation = new AlphaInAnimation();
                break;

            case Scale:
                this.mAnimation = new ScaleInAnimation();
                break;

            case SlideInLeft:
                this.mAnimation = new SlideInLeftAnimation();
                break;

            case SlideInRight:
                this.mAnimation = new SlideInRightAnimation();
                break;

            case SlideInBottom:
                this.mAnimation = new SlideInBottomAnimation();
                break;
        }

    }

    @Override
    public void onViewAttachedToWindow(@NonNull Holder holder) {
        super.onViewAttachedToWindow(holder);

        if (isAnimationEnable) {
            addAnimation(holder);
        }
    }

    private void addAnimation(Holder holder) {
        for (Animator anim : mAnimation.getAnimators(holder.itemView)) {
            anim.setDuration(mDuration).start();
            anim.setInterpolator(mInterpolator);
        }
    }

    public enum MyAnimation {
        Alpha, Scale, SlideInBottom, SlideInLeft, SlideInRight
    }

    //=====================================
    // ItemTouchHelper
    //=====================================

    private boolean isDragEnable, isSwipeEnable;
    private ItemTouchHelper itemTouchHelper;

    /**
     * 是否可以拖拽
     */
    public void setDragEnable(RecyclerView recyclerView) {

        isDragEnable = true;
        setItemTouchHelper(recyclerView);

    }

    /**
     * 是否可以滑动
     */
    public void setSwipeEnable(RecyclerView recyclerView) {

        isSwipeEnable = true;
        setItemTouchHelper(recyclerView);

    }

    /**
     * 是否可以拖拽 滑动
     */
    public void setDragAndSwipeEnable(RecyclerView recyclerView) {

        setDragEnable(recyclerView);
        setSwipeEnable(recyclerView);

    }

    private void setItemTouchHelper(RecyclerView recyclerView) {

        if (null == itemTouchHelper) {
            itemTouchHelper = new ItemTouchHelper(new SimpItemTouchHelper());
            itemTouchHelper.attachToRecyclerView(recyclerView);
        }

    }

    private class SimpItemTouchHelper extends ItemTouchHelper.Callback {

        /**
         * Item是否支持长按拖动
         */
        @Override
        public boolean isLongPressDragEnabled() {
            return isDragEnable;
        }

        /**
         * Item是否支持滑动
         */
        @Override
        public boolean isItemViewSwipeEnabled() {
            return isSwipeEnable;
        }

        /**
         * 设置滑动类型标记
         */
        @Override
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {

            // 允许上下的拖动
            int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;

            // 允许左右侧滑
            int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;

            return makeMovementFlags(dragFlags, swipeFlags);

        }

        /**
         * 拖拽切换Item的回调
         */
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView,
                              @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

            int fromPosition = viewHolder.getAdapterPosition(); // 拖动ViewHolder的position
            int toPosition = target.getAdapterPosition(); // 目标ViewHolder的position
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(mData, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(mData, i, i - 1);
                }
            }

            notifyItemMoved(fromPosition, toPosition);

            return true;

        }

        /**
         * 滑动删除Item
         */
        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            int position = viewHolder.getAdapterPosition();

            if (isShowHeadView) {
                mData.remove(position - 1);
            } else {
                mData.remove(position);
            }

            notifyItemRemoved(position);

        }

        /**
         * Item被选中时候回调
         */
        @Override
        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
            //  item被选中的操作
            if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
                viewHolder.itemView.setBackgroundResource(R.color.bg_FF5B10);
            }
            super.onSelectedChanged(viewHolder, actionState);
        }

        /**
         * 用户操作完毕或者动画完毕后会被调用
         */
        @Override
        public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            // 操作完毕后恢复颜色
            viewHolder.itemView.setBackgroundResource(R.color.bg_FF4400);
            viewHolder.itemView.setAlpha(1.0f);
            super.clearView(recyclerView, viewHolder);
        }

    }


    //=====================================
    // DiffUtil
    // 数据 必须是新增 或 全新的才起作用
    // Google 官方同时也指出，如果是对大数据集的比对，最好是方在子线程中去完成计算，也就是其实是存在堵塞 UI 的情况的。
    //      所以如果你遇见了使用 DiffUtil 之后，每次刷新有卡顿的情况，可以考虑是否数据集太大，是否应该在子线程中完成计算。
    //=====================================

    private AdapterDiffCallBack mDiffCallBack;
    private DiffUtil.DiffResult mResult;
    private CallBack mCallBack;
    private boolean mDiffDataEnable = false; // 是否开启 diff 功能

    public void setData(List<T> data) {

        if (mDiffDataEnable) {

            mDiffCallBack.setData(mData, data);
            mResult = DiffUtil.calculateDiff(mDiffCallBack);
            addData(data);
            mResult.dispatchUpdatesTo(this);

        } else {

            addData(data);
            notifyDataSetChanged();

        }

    }

    private void addData(List<T> data) {

        mData.clear();
        mData.addAll(data);

    }

    public class AdapterDiffCallBack extends DiffUtil.Callback {

        private List<T> mOldList, mNewList;

        void setData(List<T> oldList, List<T> newList) {

            this.mOldList = oldList;
            this.mNewList = newList;

        }

        @Override
        public int getOldListSize() {
            return mOldList.size();
        }

        @Override
        public int getNewListSize() {
            return mNewList.size();
        }

        private T getItem(List<T> list, int position) {
            return list.get(position);
        }

        /**
         * class 对比 是否可以直接 return true
         */
        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {

            if (null == mCallBack) {
                return getItem(mOldList, oldItemPosition).getClass().equals(getItem(mNewList, newItemPosition).getClass());
            }
            return mCallBack.isSameItem(getItem(mOldList, oldItemPosition), getItem(mNewList, newItemPosition));

        }

        /**
         * content 对比
         */
        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {

            if (null == mCallBack) {
                return getItem(mOldList, oldItemPosition).equals(getItem(mNewList, newItemPosition));
            }
            return mCallBack.isSameContent(getItem(mOldList, oldItemPosition), getItem(mNewList, newItemPosition));


        }
    }

    public interface CallBack<T> {

        boolean isSameItem(T oldT, T newT);

        boolean isSameContent(T oldT, T newT);

    }

    public void setCallBack(CallBack callBack) {

        this.mCallBack = callBack;

        setDiffDataEnable();
        if (null == mDiffCallBack) mDiffCallBack = new AdapterDiffCallBack();

    }

    /**
     * 是否开启对比数据
     */
    public void setDiffDataEnable() {
        this.mDiffDataEnable = true;
    }

}
