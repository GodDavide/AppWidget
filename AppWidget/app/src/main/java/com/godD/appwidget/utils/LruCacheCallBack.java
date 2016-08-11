package com.godD.appwidget.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class LruCacheCallBack {

    public static Bitmap bitmapCallMap(String key, Context context) {

        LruCache<String, Bitmap> lruBit = new LruCache<String, Bitmap>(
                ((int) (Runtime.getRuntime().maxMemory()) / 8)) {
            @Override
            protected int sizeOf(String key, Bitmap value) {

                return value.getByteCount();
            }
        };
        Bitmap bitmap = lruBit.get(key);

        if (bitmap == null) {
            bitmap = SDCardBitmap.getBitmapFromSD(key, context);
            if (bitmap == null) {
                URL picUrl = null;
                try {
                    picUrl = new URL(key);
                    bitmap = BitmapFactory.decodeStream(picUrl.openStream());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (bitmap != null) {
                    lruBit.put(key, bitmap);
                    SDCardBitmap.setBitmapToSD(key, bitmap, context);
                }
            } else {
                lruBit.put(key, bitmap);
            }
        }
        return bitmap;
    }

}



