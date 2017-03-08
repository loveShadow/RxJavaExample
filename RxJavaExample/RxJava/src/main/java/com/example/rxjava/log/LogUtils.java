package com.example.rxjava.log;

import android.util.Log;

/**
 * Created by zhaoli on 2017/2/16.
 */
public class LogUtils {

    private final static String TAG = "LogUtils";

    public static String getTag(Class c) {
        return TAG + "[" + c.getSimpleName() + "]";
    }

    public static void d(String msg) {
        Log.d(TAG, msg);
    }

    public static void d(String tag, String msg) {
        Log.d(tag, msg);
    }
}
