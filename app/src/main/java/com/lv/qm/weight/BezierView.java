package com.lv.qm.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者：created by albert on 2018/9/25 15:25
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class BezierView extends View {


    private float startXPoint;
    private float startYPoint;
    private float endXPoint;
    private float endYPoint;
    private float conXPoint;
    private float conYPoint;

    private Path mPath;
    private Paint mPaint;

    private Paint linePaint;
    private Paint textPaint;

    public BezierView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);


        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(3);
        linePaint.setColor(Color.GRAY);


        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setTextSize(20);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        startXPoint = w / 4;
        startYPoint = h - h / 4;

        endXPoint = w * 3 / 4;
        endYPoint = h - h / 4;

        conXPoint = w / 2;
        conYPoint = h - h / 4 - 300;

        mPath = new Path();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.moveTo(startXPoint, startYPoint);
        mPath.quadTo(conXPoint, conYPoint, endXPoint, endYPoint);

        canvas.drawLine(startXPoint, startYPoint, conXPoint, conYPoint, mPaint);
        canvas.drawLine(conXPoint, conYPoint, endXPoint, endYPoint, mPaint);


        canvas.drawPoint(startXPoint, startYPoint, mPaint);
        canvas.drawText("p0", startXPoint - 30, startYPoint, linePaint);
        canvas.drawPoint(endXPoint, endYPoint, linePaint);
        canvas.drawText("p1", endXPoint, endYPoint, textPaint);
        canvas.drawPoint(conXPoint, conYPoint, linePaint);
        canvas.drawText("p2", conXPoint, conYPoint, textPaint);


        canvas.drawPath(mPath, mPaint);


    }
}
