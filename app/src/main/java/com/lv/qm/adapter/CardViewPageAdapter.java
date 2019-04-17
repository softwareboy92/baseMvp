package com.lv.qm.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lv.qm.R;

/**
 * 作者：created by albert on 2018/10/19 17:25
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class CardViewPageAdapter extends PagerAdapter {

    private Context mContext;
    String[] imgRes = {"faga0","faga1","faga2","faga3","faga4","faga5"};


    public CardViewPageAdapter(Context context) {
        mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_cardview, null);
        TextView imageView = view.findViewById(R.id.item_wallet_pager_balance_tv);

        final int realPosition = getRealPosition(position);
        imageView.setText(imgRes[realPosition]);
        container.addView(view);

        return view;
    }


    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public void startUpdate(ViewGroup container) {
        super.startUpdate(container);
        ViewPager viewPager = (ViewPager) container;
        int position = viewPager.getCurrentItem();
        if (position == 0) {
            position = getFirstItemPosition();
        } else if (position == getCount() - 1) {
            position = getLastItemPosition();
        }
        viewPager.setCurrentItem(position, false);

    }

    private int getRealCount() {
        return imgRes.length;
    }

    private int getRealPosition(int position) {
        return position % getRealCount();
    }

    private int getFirstItemPosition() {
        return Integer.MAX_VALUE / getRealCount() / 2 * getRealCount();
    }

    private int getLastItemPosition() {
        return Integer.MAX_VALUE / getRealCount() / 2 * getRealCount() - 1;
    }
}

