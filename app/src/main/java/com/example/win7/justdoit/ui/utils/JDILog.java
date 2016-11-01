package com.example.win7.justdoit.ui.utils;

import android.util.Log;

/**
 * Created by win7 on 2016/11/1.
 */

public class JDILog {
    private static final String TAG = "justdoit";

    public static void i(String msg){
        Log.i(TAG, msg);
    }

    public static void d(String msg){
        Log.d(TAG, msg);
    }
}
