package com.example.wubin.baselibrary.widget.recyclerViewAdapter.animation;

import android.animation.Animator;
import android.view.View;

/**
 * @author wubin
 * @description
 * @date 2019-06-03
 */
public interface BaseAnimation {
    Animator[] getAnimators(View view);
}
