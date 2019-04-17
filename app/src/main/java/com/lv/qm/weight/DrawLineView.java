package com.lv.qm.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者：created by albert on 2018/12/10 11:58
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param258309
 **/
public class DrawLineView extends View {

    private Paint mPaint;
    private int mWidth;
    private int mHeight;

    public DrawLineView(Context context) {
        super(context, null);
        initPaint();
    }

    private void initPaint() {
        mPaint.setTextSize(12);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(10);

    }

    public DrawLineView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public DrawLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mWidthModel = MeasureSpec.getMode(widthMeasureSpec);
        int mHeigheModel = MeasureSpec.getMode(heightMeasureSpec);

        int mWidth = MeasureSpec.getSize(widthMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0, mWidth, 0, 0, mPaint);
    }

}
