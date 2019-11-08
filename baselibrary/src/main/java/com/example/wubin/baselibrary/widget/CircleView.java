package com.example.wubin.baselibrary.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.wubin.baselibrary.R;

/**
 * 圆形
 */
public class CircleView extends View {

    private int mColor = Color.RED; // 默认颜色
    private final int DEFAULT_SIZE = 200; // 默认控件大小

    //    Paint.FILTER_BITMAP_FLAG  是使位图过滤的位掩码标志
//    Paint.ANTI_ALIAS_FLAG     是使位图抗锯齿的标志
//    Paint.DITHER_FLAG         是使位图进行有利的抖动的位掩码标志
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        mColor = a.getColor(R.styleable.CircleView_circle_color, Color.RED);
        a.recycle();

        init();
    }

    private void init() {
        mPaint.setColor(mColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // method 1
//        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
//        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        // 倒过来判断 如果为 wrap_content
//        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
//
//            setMeasuredDimension(DEFAULT_SIZE, DEFAULT_SIZE);
//
//        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
//
//            int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
//            setMeasuredDimension(DEFAULT_SIZE, heightSpecSize);
//
//        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
//
//            int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
//            setMeasuredDimension(widthSpecSize, DEFAULT_SIZE);
//
//        }


        // method2 by self
        int widthSpecSize = getSize(widthMeasureSpec);
        int heightSpecSize = getSize(heightMeasureSpec);
        setMeasuredDimension(widthSpecSize, heightSpecSize);

    }

    private int getSize(int measureSpec) {
        int mode = MeasureSpec.getMode(measureSpec);
        return mode == MeasureSpec.AT_MOST ? DEFAULT_SIZE : MeasureSpec.getSize(measureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // method 1
//        int width = getWidth();
//        int height = getHeight();
//        int radius = Math.min(width, height) / 2;
//        canvas.drawCircle(width / 2, height / 2, radius, mPaint);

        // method 2 by self
//        int cx = getWidth() / 2;
//        int cy = getHeight() / 2;
//        int radius = Math.min(cx, cy);
//        canvas.drawCircle(cx, cy, radius, mPaint);

        // method 3
//        final int paddingLeft = getPaddingLeft();
//        final int paddingRight = getPaddingRight();
//        final int paddingTop = getPaddingTop();
//        final int paddingBottom = getPaddingBottom();
//        int width = getWidth() - paddingLeft - paddingRight;
//        int height = getHeight() - paddingLeft - paddingBottom;
//        int radius = Math.min(width, height) / 2;
//        canvas.drawCircle(paddingLeft + width / 2, paddingTop + height / 2, radius, mPaint);

        // method 4 by self from method 3
        final int paddingLeft = getPaddingLeft();
        final int paddingRight = getPaddingRight();
        final int paddingTop = getPaddingTop();
        final int paddingBottom = getPaddingBottom();
        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingLeft - paddingBottom;
        int cx = width / 2;
        int cy = height / 2;
        int radius = Math.min(cx, cy);
        canvas.drawCircle(paddingLeft + cx, paddingTop + cy, radius, mPaint);

    }
}
