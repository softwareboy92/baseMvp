package com.lv.qm.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.Pools;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lv.qm.R;


/**
 * 作者：created by albert on 2018/10/8 11:40
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class SecondActivity extends FragmentActivity {


    int index = 0;

    private LinearLayout llContainer;
    private String[] texts = new String[]{
            "火来我在灰烬中等你。",
            "我对这个世界没什么可说的。",
            "侠之大者，为国为民。",
            "为往圣而继绝学"
    };


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0 && llContainer.getChildCount() == 4) {
                llContainer.removeViewAt(0);
            }
            if (index == texts.length) {
                index = 0;
            }
            TextView textView = obtainTextView();

//            switch (index) {
//                case 0:
//                    textView.setBackgroundDrawable(ContextCompat.getDrawable(SecondActivity.this, R.drawable.rect_black));
//                    break;
//                case 1:
//                    textView.setBackgroundDrawable(ContextCompat.getDrawable(SecondActivity.this, R.drawable.rect_blue));
//                    break;
//                case 2:
//                    textView.setBackgroundDrawable(ContextCompat.getDrawable(SecondActivity.this, R.drawable.rect_green));
//                    break;
//                case 3:
//                    textView.setBackgroundDrawable(ContextCompat.getDrawable(SecondActivity.this, R.drawable.rect_yellow));
//                    break;
//                default:
//                    textView.setBackgroundDrawable(ContextCompat.getDrawable(SecondActivity.this, R.drawable.rect_yellow));
//                    break;
//            }
            textView.setText(texts[index]);
            llContainer.addView(textView);
            sendEmptyMessageDelayed(0, 2000);
            index++;
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        mHandler.sendEmptyMessage(0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeMessages(0);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        llContainer = findViewById(R.id.ll_container);

        LayoutTransition transition = new LayoutTransition();

        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 0, 1);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 0, 1);

        ObjectAnimator valueAnimator = ObjectAnimator.ofPropertyValuesHolder(null, new PropertyValuesHolder[]{scaleX, scaleY})
                .setDuration(transition.getDuration(LayoutTransition.APPEARING));

        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                ObjectAnimator objectAnimator = (ObjectAnimator) animation;
                View view = (View) objectAnimator.getTarget();
                view.setPivotX(0f);
                view.setPivotY(view.getMeasuredHeight());
            }
        });

        transition.setAnimator(LayoutTransition.APPEARING, valueAnimator);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(null, "alpha", 0, 1).setDuration(LayoutTransition.DISAPPEARING);
        transition.setAnimator(LayoutTransition.DISAPPEARING, objectAnimator);
        llContainer.setLayoutTransition(transition);


    }


    Pools.SimplePool<TextView> mTextViewSimplePool = new Pools.SimplePool<>(texts.length);


    private TextView obtainTextView() {
        TextView textView = mTextViewSimplePool.acquire();
        if (textView == null) {
            textView = new TextView(this);
            textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            textView.setTextColor(0xffffffff);
            textView.setTextSize(16);
            textView.setGravity(View.TEXT_ALIGNMENT_CENTER);
//            Drawable img_on = null;
//            img_on = this.getResources().getDrawable(R.drawable.ic_emotion);
//            int imgsize = dp2px(30);
//            img_on.setBounds(0, 0, imgsize, imgsize);
//            textView.setCompoundDrawables(img_on, null, null, null);

            int padding = dp2px(10);
            textView.setPadding(padding, padding, padding, padding);
            textView.setTextColor(0xffffffff);
            textView.setOnClickListener(v -> {
                Toast.makeText(this, texts[index], Toast.LENGTH_SHORT).show();
            });
        }
        return textView;
    }


    private int dp2px(float dp) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics);
    }


}
