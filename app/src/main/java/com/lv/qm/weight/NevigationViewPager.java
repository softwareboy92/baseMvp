package com.lv.qm.weight;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NevigationViewPager extends ViewPager {

    private boolean scrollble = false;

    public NevigationViewPager(Context context) {
        super(context);
    }

    public NevigationViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!scrollble) {
            return true;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (scrollble)
            return super.onInterceptTouchEvent(arg0);
        else
            return false;
    }

    public boolean isScrollble() {
        return scrollble;
    }


    public void setScrollble(boolean scrollble) {
        this.scrollble = scrollble;
    }


    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item, false);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }
}