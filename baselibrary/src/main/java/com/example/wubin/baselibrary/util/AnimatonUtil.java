package com.example.wubin.baselibrary.util;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * @author wubin
 * @description
 * @date 2019-06-11
 */
public class AnimatonUtil {

    public static Animator getTranslationX(View view, float start, float end) {

        return ObjectAnimator.ofFloat(view, "translationX", start, end);

    }

    public static Animator getTranslationY(View view, float start, float end) {

        return ObjectAnimator.ofFloat(view, "translationY", start, end);

    }

    public static Animator getAlpha(View view, float start, float end) {

        return ObjectAnimator.ofFloat(view, "alpha", start, end);

    }

    public static Animator getRotation(View view, float start, float end) {

        return ObjectAnimator.ofFloat(view, "rotation", start, end);

    }

    public static Animator getRotationX(View view, float start, float end) {

        return ObjectAnimator.ofFloat(view, "rotationX", start, end);

    }

    public static Animator getRotationY(View view, float start, float end) {

        return ObjectAnimator.ofFloat(view, "rotationY", start, end);

    }

    public static Animator getScaleX(View view, float start, float end) {

        return ObjectAnimator.ofFloat(view, "scaleX", start, end);

    }

    public static Animator getScaleY(View view, float start, float end) {

        return ObjectAnimator.ofFloat(view, "scaleY", start, end);

    }
}
