package com.test.nice.baselibrary.utils.glideutil;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;

/**
 * ImageLoader class
 *
 * @author CJX
 * @date 2017/10/24
 */
public class ImageLoader {

    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";

    private static class ImageLoaderHolder {
        private static final ImageLoader INSTANCE = new ImageLoader();

    }

    private ImageLoader(){

    }

    public static final ImageLoader getInstance(){
        return ImageLoaderHolder.INSTANCE;
    }

    /**
     * 直接加载网络图片
     * @param context
     * @param url
     * @param imageView
     */
    public void displayImage(Context context, String url, ImageView imageView){
        Glide.with(context)
                .load(url)
                .centerCrop()
                .crossFade()
                .into(imageView);
    }

    /**
     * 加载sd卡图片
     * @param context
     * @param file
     * @param imageView
     */
    public void displayImage(Context context, File file, ImageView imageView){
        Glide.with(context)
                .load(file)
                .centerCrop()
                .into(imageView);
    }

    /**
     * 加载sd卡图片并设置大小
     * @param context
     * @param file
     * @param imageView
     * @param width
     * @param height
     */
    public void displayImage(Context context, File file, ImageView imageView, int width, int height){
        Glide.with(context)
                .load(file)
                .centerCrop()
                .override(width, height)
                .crossFade()
                .into(imageView);
    }

    /**
     * 加载网络图片并设置大小
     * @param context
     * @param url
     * @param imageView
     * @param width
     * @param height
     */
    public void displayImage(Context context, String url, ImageView imageView, int width, int height) {
        Glide
                .with(context)
                .load(url)
                .centerCrop()
                .override(width, height)
                .crossFade()
                .into(imageView);
    }


}
