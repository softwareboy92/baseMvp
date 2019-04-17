package com.lv.qm.utils;


import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class ImageLoader {

    private static ImageLoader mInstance;

    public static ImageLoader getInstance() {
        if (mInstance == null) {
            mInstance = new ImageLoader();
        }
        return mInstance;
    }

    /**
     * 显示图片
     *
     * @param context
     * @param url
     * @param view
     */
    public void displayImage(Context context, String url, ImageView view) {
        Glide.with(context)
                .load(url)
                .into(view);
    }

    /**
     * 显示图片
     * @param context
     * @param url
     * @param view
     * @param defaultViewId
     * @param errorViewId
     */
    public void displayImage(Context context, String url, ImageView view, int defaultViewId, int errorViewId) {

        Glide.with(context)
                .load(url)
                .apply(RequestOptions.errorOf(errorViewId)
                        .placeholder(defaultViewId)
                        .centerCrop()
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(view);
    }


    /**
     * 显示图片
     * @param context
     * @param url
     * @param defaultViewId
     * @param errorViewId
     * @param view
     * @param width
     * @param height
     */
    public void displayImage(Context context, String url, int defaultViewId, int errorViewId, ImageView view, int width, int height) {

        Glide.with(context)
                .load(url)
                .apply(RequestOptions.errorOf(errorViewId)
                        .placeholder(defaultViewId)
                        .centerCrop()
                        .skipMemoryCache(true)
                        .override(width, height)
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(view);
    }


}