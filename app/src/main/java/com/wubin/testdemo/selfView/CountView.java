package com.wubin.testdemo.selfView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author wubin
 * @description
 * @date 2019-06-17
 */
public class CountView extends View implements View.OnClickListener {

    private Paint mPaint;

    // 在android中Rect和RextF都是用来创建一个矩形的
    private Rect mBounds;

    private int mCount;

    public CountView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBounds = new Rect();
        setOnClickListener(this);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        // 画外框
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(0, 0, width, height, mPaint);

        // 数字
        mPaint.setColor(Color.YELLOW);
        mPaint.setTextSize(60);
        String text = String.valueOf(mCount);

        // getTextBounds 是将TextView 的文本放入一个矩形中， 测量TextView的高度和宽度
        mPaint.getTextBounds(text, 0, text.length(), mBounds);
        float textWidth = mBounds.width();
        float textHeight = mBounds.height();
        canvas.drawText(
                text,
                width / 2 - textWidth / 2, height / 2 + textHeight / 2,
                mPaint);

    }

    @Override
    public void onClick(View view) {

        mCount++;
        invalidate();

    }
}
