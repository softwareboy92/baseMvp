package com.lv.qm.weight;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * 作者：created by albert on 2019/2/25 13:48
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class WaveView extends SurfaceView implements SurfaceHolder.Callback {

    private Point mCentrePoint;
    private int mNowHeight = 0;//当前的水位高度
    private int mRadius = 0;
    private boolean mStart = false;//是否开始
    private float mTextSize = 60;//文字大小
    private Context mContext;
    private int mTranX = 0;//水平的平移量
    private Paint mCirclePaint;
    private Paint mOutCirclePaint;
    private Paint mWavePaint;
    private Paint mTextPaint;
    private SurfaceHolder holder;
    private RenderThread renderThread;
    private boolean isDraw = false;//控制绘制的开关
    private int mCircleColor = Color.parseColor("#ff6600");//背景内圆颜色
    private int mOutCircleColor = Color.parseColor("#ff944d");//背景外圆颜色
    private int mWaveColor = Color.parseColor("#ff944d");//水波颜色
    private int mWatterLevel;//水目标高度
    private int flowNum = 60;//水目标百分比，这里是整数
    private int mWaveSpeed = 10;//水波起伏速度
    private int mUpSpeed = 2;//水面上升速度


    public WaveView(Context context) {
        super(context);
        mContext = context;
        init(mContext);
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(mContext);
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init(mContext);
    }

    /**
     * 初始化
     *
     * @param context
     */
    private void init(Context context) {
        mContext = context;
        setZOrderOnTop(true);//控制窗口中表面的视图层是否放置在常规视图层的顶部。
        holder = this.getHolder();
        holder.addCallback(this);
        holder.setFormat(PixelFormat.TRANSLUCENT);
        renderThread = new RenderThread();

        mCirclePaint = new Paint();
        mCirclePaint.setColor(mCircleColor);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setAntiAlias(true);

        mOutCirclePaint = new Paint();
        mOutCirclePaint.setColor(mOutCircleColor);
        mOutCirclePaint.setStyle(Paint.Style.FILL);
        mOutCirclePaint.setAntiAlias(true);

        mWavePaint = new Paint();
        mWavePaint.setStrokeWidth(1.0F);
        mWavePaint.setColor(mWaveColor);
        mWavePaint.setStyle(Paint.Style.FILL);
        mWavePaint.setAntiAlias(true);

        mTextPaint = new Paint();
        mTextPaint.setStrokeWidth(1.0F);
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setAntiAlias(true);
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mRadius = (int) (0.5 * width * 0.92);
        mCentrePoint = new Point(width / 2, height / 2);
        mWatterLevel = (int) (2 * mRadius * flowNum / 100f);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isDraw = true;
        if (renderThread != null && !renderThread.isAlive())
            renderThread.start();

    }


    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isDraw = false;
    }


    /**
     * 绘制界面的线程
     */
    private class RenderThread extends Thread {

        @Override
        public void run() {
            while (isDraw) {
                if (mWatterLevel > mNowHeight) {
                    mNowHeight = mNowHeight + mUpSpeed;
                }
                if (mStart) {
                    if (mTranX > mRadius) {
                        mTranX = 0;
                    }
                    mTranX = mTranX - mWaveSpeed;
                }
                dawUI();
            }
            super.run();
        }
    }

    /**
     * 绘制界面
     */
    private void dawUI() {
        Canvas canvas = holder.lockCanvas();
        try {
            drawCanvas(canvas);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (canvas != null)
                holder.unlockCanvasAndPost(canvas);
        }
    }

    /**
     * @param canvas
     */
    private void drawCanvas(Canvas canvas) {
        canvas.drawCircle(mCentrePoint.x, mCentrePoint.y, mRadius / 0.92f, mOutCirclePaint);//画背景圆圈
        canvas.drawCircle(mCentrePoint.x, mCentrePoint.y, mRadius, mCirclePaint);
        if (mStart) {
            //计算正弦曲线的路径
            int mH = mCentrePoint.y + mRadius - mNowHeight;
            int left = mRadius / 2;
            int length = 4 * mRadius;
            Path path2 = new Path();
            path2.moveTo(left, mH);


            for (int i = left; i < left; i++) {
                int x = i;
                int y = (int) Math.sin(Math.toRadians(x + mTranX / 2) * mRadius / 4);
                path2.lineTo(x, mH + y);
            }

            path2.lineTo(length, mH);
            path2.lineTo(length, mCentrePoint.y + mRadius);
            path2.lineTo(0, mCentrePoint.y + mRadius);
            path2.lineTo(0, mH);

            canvas.save();
            //这里与圆形取交际，出去正选曲线多画出来的部分
            Path pc = new Path();
            pc.addCircle(mCentrePoint.x, mCentrePoint.y, mRadius, Path.Direction.CCW);
            canvas.clipPath(pc, Region.Op.INTERSECT);
            canvas.drawPath(path2, mWavePaint);
            canvas.restore();
            //绘制文字
            canvas.drawText(flowNum + "%", mCentrePoint.x, mCentrePoint.y, mTextPaint);
        }
    }


    public void setFlowNum(int num) {
        flowNum = num;
        mStart = true;
    }

    public void setTextSise(float s) {
        mTextSize = s;
        mTextPaint.setTextSize(s);
    }

    //设置水面上升速度
    public void setUpSpeed(int speed) {
        mUpSpeed = speed;
    }

    public void setColor(int waveColor, int circleColor, int outcircleColor) {
        mWaveColor = waveColor;
        mCircleColor = circleColor;
        mOutCircleColor = outcircleColor;
        mWavePaint.setColor(mWaveColor);
        mCirclePaint.setColor(mCircleColor);
        mOutCirclePaint.setColor(mOutCircleColor);
    }

}
