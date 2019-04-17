package com.lv.qm.weight;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 作者：created by albert on 2018/10/8 14:00
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class ImgAndTextView extends LinearLayout {

    // 文本
    private String mRightText;
    // 文本颜色
    private int mRightTextColor;
    // 文本大小
    private float mRightTextSize;
    // Paint
    private TextPaint mPaint;
    // 宽高
    private int mWidth;
    private int mHalfWidth;
    private int mRightTextWidth;
    private int mRightTextX;

    public ImgAndTextView(Context context) {
        this(context, null);
    }

    public ImgAndTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //获取高度和宽度的模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int hightMode = MeasureSpec.getMode(heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST) {
            Rect bounds = new Rect();
            mPaint.getTextBounds(mRightText, 0, mRightText.length(), bounds);
            width = bounds.width();
        }

        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (hightMode == MeasureSpec.AT_MOST) {
            Rect bounds = new Rect();
            mPaint.getTextBounds(mRightText, 0, mRightText.length(), bounds);
            height = bounds.width();
        }

        setMeasuredDimension(width, height);
    }
}
