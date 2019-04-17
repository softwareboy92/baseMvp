package com.lv.qm.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * 作者：created by albert on 2018/9/25 16:06
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class DrawCubicVIew extends View {

    private int leftX, leftY;
    private int rightX, rightY;
    private int centerX, centerY;
    private int startX, startY;
    private int endX, endY;
    private Paint paint;
    private boolean isMoveLeft;

    public DrawCubicVIew(Context context) {
        super(context);
        init();
    }


    public DrawCubicVIew(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        centerX = w / 2;
        centerY = h / 2;
        startX = centerX - 250;
        startY = centerY;
        endX = centerX + 250;
        endY = centerY;
        leftX = startX;
        leftY = centerY - 250;
        rightX = endX;
        rightY = endY - 250;

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setColor(Color.GRAY);

        canvas.drawCircle(startX, startY, 8, paint);
        canvas.drawCircle(endX, endY, 8, paint);
        canvas.drawCircle(leftX, leftY, 8, paint);
        canvas.drawCircle(rightX, rightY, 8, paint);


        paint.setStrokeWidth(3);
        canvas.drawLine(startX,startY,leftX,leftY,paint);
        canvas.drawLine(leftX,leftY,rightX,rightY,paint);
        canvas.drawLine(rightX,rightY,endX,endY,paint);


        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        Path path = new Path();
        path.moveTo(startX,startY);
        path.cubicTo(leftX,leftY,rightX,rightY,endX,endY);
        canvas.drawPath(path,paint);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                if (isMoveLeft){
                    leftX = (int) event.getX();
                    leftY = (int) event.getY();
                }else {
                    rightX = (int) event.getX();
                    rightY = (int) event.getY();
                }
                invalidate();
                break;

        }
        return true;
    }

    public void moveLeft(){
        isMoveLeft = true;
    }

    public void moveRight(){
        isMoveLeft = false;
    }
}
