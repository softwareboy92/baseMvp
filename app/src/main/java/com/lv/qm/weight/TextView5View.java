package com.lv.qm.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者：create by albert on 2018/11/9 9:19 PM
 * 邮箱：lvhzongdi@iclou.com
 */
public class TextView5View extends View {

    private Paint mPaint;

    public TextView5View(Context context) {
        super(context);
        initView();
    }

    public TextView5View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TextView5View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public void initView(){

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
