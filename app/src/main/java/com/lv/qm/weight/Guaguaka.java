package com.lv.qm.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者：created by albert on 2018/10/17 14:37
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class Guaguaka extends View {

    private Paint paint;


    public Guaguaka(Context context) {
        super(context);
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(5);
    }

    public Guaguaka(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    public Guaguaka(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect rect1 = new Rect(100, 100, 400, 200);
        Rect rect2 = new Rect(200, 0, 300, 300);

        Region region1 = new Region(rect1);
        Region region2 = new Region(rect2);

        region1.op(region2, Region.Op.UNION);

        drawRegion(canvas, region1, paint);

    }

    private void drawRegion(Canvas canvas, Region region1, Paint paint) {
        RegionIterator iterator = new RegionIterator(region1);
        Rect r = new Rect();
        while (iterator.next(r)) {
            canvas.drawRect(r, paint);
        }
    }

}
