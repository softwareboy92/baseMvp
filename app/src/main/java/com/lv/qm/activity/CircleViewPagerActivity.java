package com.lv.qm.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.lv.qm.R;
import com.lv.qm.adapter.CardViewPageAdapter;
import com.lv.viewpager.transformer.AlphaPageTransformer;
import com.lv.viewpager.transformer.NonPageTransformer;
import com.lv.viewpager.transformer.RotateDownPageTransformer;
import com.lv.viewpager.transformer.RotateUpPageTransformer;
import com.lv.viewpager.transformer.RotateYTransformer;
import com.lv.viewpager.transformer.ScaleInTransformer;

public class CircleViewPagerActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private PagerAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_viewpager);

        mViewPager = findViewById(R.id.id_viewpager);

        mViewPager.setPageMargin(40);
        mViewPager.setAdapter(new CardViewPageAdapter(this));

        mViewPager.setPageTransformer(true, new AlphaPageTransformer());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        String[] effects = this.getResources().getStringArray(R.array.magic_effect);
        for (String effect : effects)
            menu.add(effect);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String title = item.getTitle().toString();
        mViewPager.setAdapter(mAdapter);

        if ("RotateDown".equals(title)) {
            mViewPager.setPageTransformer(true, new RotateDownPageTransformer());
        } else if ("RotateUp".equals(title)) {
            mViewPager.setPageTransformer(true, new RotateUpPageTransformer());
        } else if ("RotateY".equals(title)) {
            mViewPager.setPageTransformer(true, new RotateYTransformer(45));
        } else if ("Standard".equals(title)) {
//            mViewPager.setClipChildren(false);
            mViewPager.setPageTransformer(true, NonPageTransformer.INSTANCE);
        } else if ("Alpha".equals(title)) {
//            mViewPager.setClipChildren(false);
            mViewPager.setPageTransformer(true, new AlphaPageTransformer());
        } else if ("ScaleIn".equals(title)) {
            mViewPager.setPageTransformer(true, new ScaleInTransformer());
        } else if ("RotateDown and Alpha".equals(title)) {
            mViewPager.setPageTransformer(true, new RotateDownPageTransformer(new AlphaPageTransformer()));
        } else if ("RotateDown and Alpha And ScaleIn".equals(title)) {
            mViewPager.setPageTransformer(true, new RotateDownPageTransformer(new AlphaPageTransformer(new ScaleInTransformer())));
        }

        setTitle(title);

        return true;
    }
}
