package com.feicuiedu.sharedemo;

import android.os.Handler;
import android.os.Looper;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;

/**
 * Created by 123 on 2016/11/28.
 */

public abstract class CustomActionListener implements PlatformActionListener {

    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void onComplete(final Platform platform, final int i, final HashMap<String, Object> hashMap) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                onUIComplete(platform, i, hashMap);
            }
        });
    }

    @Override
    public void onError(final Platform platform, final int i, final Throwable throwable) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                onUIError(platform, i, throwable);
            }
        });
    }

    @Override
    public void onCancel(final Platform platform, final int i) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                onUICancel(platform, i);
            }
        });
    }

    abstract void onUIComplete(Platform platform, int i, HashMap<String, Object> hashMap);

    abstract void onUIError(Platform platform, int i, Throwable throwable);

    abstract void onUICancel(Platform platform, int i);
}
