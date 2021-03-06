package com.wubin.testdemo.animation;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.wubin.baselibrary.activity.BaseActivity;
import com.example.wubin.baselibrary.util.DeviceUtil;
import com.example.wubin.baselibrary.util.ShowUtil;
import com.wubin.testdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimationActivity extends BaseActivity {

    private final int ID_VIEW = R.id.activity_animation_view;
    @BindView(ID_VIEW)
    Button btn_view;

    private final int ID_ALPHA = R.id.activity_animation_alpha;
    @BindView(ID_ALPHA)
    Button btn_alpha;

    private final int ID_LIST = R.id.activity_animation_list;
    @BindView(ID_LIST)
    Button btn_list;

    private final int ID_VALUE = R.id.activity_animation_value;
    @BindView(ID_VALUE)
    Button btn_value;

    private final int ID_OBJECT = R.id.activity_animation_object;
    @BindView(ID_OBJECT)
    Button btn_object;

    private final int ID_SET = R.id.activity_animation_set;
    @BindView(ID_SET)
    Button btn_set;

    private final int ID_SET2 = R.id.activity_animation_set2;
    @BindView(ID_SET2)
    Button btn_set2;

    private final int ID_VALUE2 = R.id.activity_animation_value2;
    @BindView(ID_VALUE2)
    Button btn_value2;

    private final int ID_COMPOSE = R.id.activity_animation_compose;
    @BindView(ID_COMPOSE)
    Button btn_compose;

    private final int ID_COMPOSE2 = R.id.activity_animation_compose2;
    @BindView(ID_COMPOSE2)
    Button btn_compose2;

    Animation animation_view, animation_alpha;
    AnimationDrawable animation_list;
    private boolean isHead = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);

    }

    @OnClick(ID_VIEW)
    public void viewClick(View view) {
        if (null == animation_view) {
            animation_view = AnimationUtils.loadAnimation(this, R.anim.anim_view);
        }
        btn_view.startAnimation(animation_view);
    }

    @OnClick(ID_ALPHA)
    public void alphaClick(View view) {
        if (null == animation_alpha) {

            animation_alpha = new AlphaAnimation(0, 1);
            animation_alpha.setDuration(300);

            animation_alpha.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    ShowUtil.print("onAnimationStart......");
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    ShowUtil.print("onAnimationEnd......");
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                    ShowUtil.print("onAnimationRepeat......");
                }
            });
        }
        btn_alpha.startAnimation(animation_alpha);
    }

    @OnClick(ID_LIST)
    public void listClick(View view) {
        if (null == animation_list) {
            animation_list = (AnimationDrawable) btn_list.getBackground();
        }
        if (animation_list.isRunning()) {
            animation_list.stop();
        } else {
            animation_list.start();
        }
    }

    /**
     * 改变View的背景色，3秒内从0xFFFF8080到 0xFF8080FF的渐变
     */
    @OnClick(ID_VALUE)
    public void valueClick(View v) {
        ValueAnimator colorAnim = ObjectAnimator.ofInt(btn_value, "backgroundColor", 0xFFFF8080, 0xFF8080FF);
        colorAnim.setDuration(3000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();
//        colorAnim.pause();
//        colorAnim.end();
//        colorAnim.cancel();
//        colorAnim.reverse();

    }

    /**
     * 沿着X轴平移
     */
    @OnClick(ID_OBJECT)
    public void objectClick(View v) {
        if (isHead) {
            int offWidth = DeviceUtil.getDisplayWidth() - btn_value.getWidth();
            ObjectAnimator.ofFloat(btn_object, "translationX", offWidth).start();
            isHead = false;
        } else {
            ObjectAnimator.ofFloat(btn_object, "translationX", 0).start();
            isHead = true;
        }
    }

    @OnClick(ID_SET)
    public void setClick(View v) {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
                ObjectAnimator.ofFloat(btn_set, "rotationX", 0, 360),
                ObjectAnimator.ofFloat(btn_set, "rotationY", 0, 180),
                ObjectAnimator.ofFloat(btn_set, "rotation", 0, -90),
                ObjectAnimator.ofFloat(btn_set, "translationX", 0, 90),
                ObjectAnimator.ofFloat(btn_set, "translationY", 0, 90),
                ObjectAnimator.ofFloat(btn_set, "scaleX", 0, 1.5f),
                ObjectAnimator.ofFloat(btn_set, "scaleY", 0, 0.5f),
                ObjectAnimator.ofFloat(btn_set, "alpha", 0, 0.25f, 1)
        );
        set.setDuration(5 * 1000).start();
    }

    @OnClick(ID_SET2)
    public void setClick2(View v) {
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.animator_value);
        set.setTarget(btn_set2);
        set.start();
    }

    @OnClick(ID_VALUE2)
    void setValue2(View v) {

        ValueAnimator anim = ValueAnimator.ofInt(5, 0);
        anim.setDuration(5000);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                int currentValue = (int) animation.getAnimatedValue();
                btn_value2.setText(currentValue + "");

            }
        });
        anim.start();
    }


    @OnClick(ID_COMPOSE)
    void setCompose() {

//        after(Animator anim)   将现有动画插入到传入的动画之后执行
//        after(long delay)   将现有动画延迟指定毫秒后执行
//        before(Animator anim)   将现有动画插入到传入的动画之前执行
//        with(Animator anim)   将现有动画和传入的动画同时执行

        ObjectAnimator moveIn = ObjectAnimator.ofFloat(btn_compose, "translationX", 0, 100f);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(btn_compose, "rotation", 0f, 360f);
        ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(btn_compose, "alpha", 1f, 0f, 1f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(rotate).with(fadeInOut).after(moveIn);
//        animSet.playTogether(rotate,fadeInOut);
        animSet.setDuration(5000);

//        animSet.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animator) {
//
//            }
//        });

        animSet.start();

    }

    @OnClick(ID_COMPOSE2)
    void setCompose2() {

        ObjectAnimator moveIn = ObjectAnimator.ofFloat(btn_compose, "translationX", -500f, 0f);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(btn_compose, "rotation", 0f, 360f);
        ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(btn_compose, "alpha", 1f, 0f, 1f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(rotate).with(fadeInOut).after(moveIn);
        animSet.setDuration(5000);

        animSet.start();

    }


}
