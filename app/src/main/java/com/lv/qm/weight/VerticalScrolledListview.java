package com.lv.qm.weight;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 作者：created by albert on 2019/3/21 10:41
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class VerticalScrolledListview extends LinearLayout {

    private Context mContext;
    private List<String> data = new ArrayList<>();
    private OnItemClickListener mItemClickListener;
    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            traversaView(VerticalScrolledListview.this);
        }
    };


    public VerticalScrolledListview(Context context) {
        super(context);
        init(context);
    }

    public VerticalScrolledListview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public VerticalScrolledListview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    /**
     * 初始化
     *
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        this.setOrientation(VERTICAL);
    }


    public void setData(List<String> data) {
        this.data = data;
        initTextView();
    }

    private void initTextView() {
        if (data != null && data.size() != 0) {
            for (int i = 0; i < data.size(); i++) {
                TextView textView = new TextView(mContext);
                textView.setText(data.get(i));
                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(Color.BLACK);
                textView.setPadding(5, 5, 5, 5);
                this.addView(textView);
                textView.setClickable(true);
                final int index = i;
                textView.setOnClickListener(v -> {
                    mItemClickListener.onItemClick(index);
                });
            }
            startTimer();
        }
    }

    private void startTimer() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                mHandler.post(mRunnable);
            }
        };
        timer.schedule(timerTask, 2000, 2000);
    }

    /**
     * 遍历view的所有子控件设值
     *
     * @param viewGroup
     */
    private void traversaView(ViewGroup viewGroup) {
        int count = viewGroup.getChildCount();
        if (count != 0) {
            for (int i = 0; i < count; i++) {
                if (i == 0) {
                    mHandler.post(() -> {
                        TextView newView = (TextView) viewGroup.getChildAt(0);
                        viewGroup.removeView(viewGroup.getChildAt(0));
                        viewGroup.addView(newView);
                    });
                }
            }
        }

    }


    /**
     * 设置点击事件的监听
     *
     * @param itemClickListener
     */
    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }


    /**
     * 文本点击的监听
     */
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

}
