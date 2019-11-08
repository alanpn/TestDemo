
1。
    Android动画可分为：View动画 帧动画 属性动画

2。
    View 动画作用是View 支持4种动画效果 平移动画TranslateAnimation 缩放动画ScaleAnimation
        旋转动画RotateAnimation 透明度动画AlphaAnimation
    帧动画也属于View动画

3。
    <?xml version="1.0" encoding="utf-8"?>
    <set xmlns:android="http://schemas.android.com/apk/res/android"
        android:interpolator="@android:anim/accelerate_decelerate_interpolator"
        android:shareInterpolator="true">

        <alpha
            android:fromAlpha="1"
            android:toAlpha="9" />

        <scale
            android:fromXScale="1"
            android:fromYScale="1"
            android:pivotX="1"
            android:pivotY="9"
            android:toXScale="9"
            android:toYScale="9" />

        <translate
            android:fromXScale="1"
            android:fromYScale="1"
            android:toXScale="9"
            android:toYScale="9" />

        <rotate
            android:fromDegrees="1"
            android:pivotX="1"
            android:pivotY="9"
            android:toDegrees="1" />

    </set>

1.set
    表示动画集合

2.interpolator
    表示动画集合所采用的插值器，插值器影响动画的速度，比如非匀速动画就需要通过插值器来控制动画的播放国产。
    这个属性可不指定 默认为@android:anim/accelerate_decelerate_interpolator 即加减插值器

    android:interpolator="@android:anim/accelerate_interpolator" 设置动画为加速动画(动画播放中越来越快)
    android:interpolator="@android:anim/decelerate_interpolator" 设置动画为减速动画(动画播放中越来越慢)
    android:interpolator="@android:anim/accelerate_decelerate_interpolator" 设置动画为先加速在减速(开始速度最快 逐渐减慢)
    android:interpolator="@android:anim/anticipate_interpolator" 先反向执行一段，然后再加速反向回来（相当于我们弹簧，先反向压缩一小段，然后在加速弹出）
    android:interpolator="@android:anim/anticipate_overshoot_interpolator" 同上先反向一段，然后加速反向回来，执行完毕自带回弹效果（更形象的弹簧效果）
    android:interpolator="@android:anim/bounce_interpolator" 执行完毕之后会回弹跳跃几段（相当于我们高空掉下一颗皮球，到地面是会跳动几下）
    android:interpolator="@android:anim/cycle_interpolator" 循环，动画循环一定次数，值的改变为一正弦函数：Math.sin(2* mCycles* Math.PI* input)
    android:interpolator="@android:anim/linear_interpolator" 线性均匀改变
    android:interpolator="@android:anim/overshoot_interpolator" 加速执行，结束之后回弹

3.shareInterpolator
    表示集合中的动画是否和集合共享同一个插值器。
    如果集合不指定插值器，那么子动画就需要单独指定所需的插值器或使用默认值

4.zAdjustment
    表示被设置动画的内容运行时在Z轴上的位置（top/bottom/normal），默认为normal

5.<translate>
    平移动画，对应TranslateAnimation。可以使View具有放大或缩小的动画效果
        fromXScale  x的起始值
        toXScale    x的结束值
        fromYScale  y的起始值
        toYScale    y的结束值

6.<scale>
    缩放动画，对应ScaleAnimation。可以使View具有放大或缩小的动画效果
        fromXScale  水平方向缩放的起始值
        toXScale    水平方向缩放的结束值
        fromYScale  竖直方向缩放的起始值
        toYScale    竖直方向缩放的结束值
        pivotX      缩放的轴点的x坐标，它会影响缩放的效果
        pivotY      缩放的轴点的y坐标
    注：默认情况下轴点是View的中心点，这个时候在水平方向进行缩放会导致View向左右两个方向同时进行缩放
       但如果轴点设为View的右边界，View只会向左边进行缩放，反之则向右边进行缩放

7.<rotate>
    旋转动画，对应RotateAnimation
        fromDegrees 旋转开始的角度
        toDegrees   旋转结束的角度
        pivotX      缩放的轴点的x坐标
        pivotY      缩放的轴点的y坐标

8.<alpha>
    透明度动画，对应AlphaAnimation
        fromAlpha   透明度的起始位值
        toAlpha     透明度的结束值

9.duration 动画持续时间

10.fillAfter 动画结束以后View是否停留在结束位置 true表示停留 false不停留
