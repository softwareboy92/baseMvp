package com.lv.qm.weight;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;

/**
 * 作者：created by albert on 2018/12/24 22:46
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class MyTabLayout extends TabLayout {

    public MyTabLayout(Context context) {
        super(context);
    }

    public MyTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * 设置指示器颜色
     * @param color
     */
    @Override
    public void setSelectedTabIndicatorColor(int color) {
        super.setSelectedTabIndicatorColor(color);
    }

    /**
     * 设置tab字体颜色
     * @param textColor
     */
    @Override
    public void setTabTextColors(@Nullable ColorStateList textColor) {
        super.setTabTextColors(textColor);
    }

    /**
     * 设置tab字体和选择后的字体颜色
     * @param normalColor
     * @param selectedColor
     */
    @Override
    public void setTabTextColors(int normalColor, int selectedColor) {
        super.setTabTextColors(normalColor, selectedColor);
    }

}
