package com.wubin.testdemo.eraser;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.wubin.testdemo.R;

import java.util.Random;

/**
 * 类似刮刮卡效果
 */
public class EraserViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new EraserView(this));
    }

    class EraserView extends View {

        private Canvas mCanvas;
        private Bitmap mBitmapForeground;
        private Paint mPaint;

        private int mPreX, mPreY;

        private String[] rewards = new String[]{
                "一等奖", "二等奖", "三等奖", "没奖"
        };


        public EraserView(Context context) {
            super(context);
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaint.setStrokeWidth(80);
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            mPaint.setStrokeCap(Paint.Cap.ROUND);
            mPaint.setStrokeJoin(Paint.Join.ROUND);
            drawBackGround();
        }

        public EraserView(Context context, AttributeSet attrs) {
            super(context, attrs);

        }

        public EraserView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            mBitmapForeground = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas(mBitmapForeground);
            mCanvas.drawColor(Color.parseColor("#FF808080"));
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }

        /**
         * 绘制背景
         */
        private void drawBackGround() {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.new_qh_main_zoom_img);
            //bitmap不可编辑，故copy,并设置可编辑
            Bitmap bitmapBg = bitmap.copy(Bitmap.Config.ARGB_8888, true);
            Canvas canvas = new Canvas(bitmapBg);
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
            paint.setColor(Color.RED);
            paint.setTextSize(300);
            String content = rewards[new Random().nextInt(rewards.length)];
            Rect rect = new Rect();
            paint.getTextBounds(content, 0, content.length(), rect);
            canvas.drawText(content,
                    (bitmapBg.getWidth() - rect.width()) / 2,
                    (bitmapBg.getHeight() - rect.height()) / 2,
                    paint);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                this.setBackground(new BitmapDrawable(bitmapBg));
            } else {
                this.setBackgroundDrawable(new BitmapDrawable(bitmapBg));
            }
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawBitmap(mBitmapForeground, 0, 0, null);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            int currentX = (int) event.getX();
            int currentY = (int) event.getY();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mPreX = currentX;
                    mPreY = currentY;
                    break;
                case MotionEvent.ACTION_MOVE:
                    mCanvas.drawLine(mPreX, mPreY, currentX, currentY, mPaint);
                    mPreX = currentX;
                    mPreY = currentY;
                    invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
            return true;
        }
    }
}
