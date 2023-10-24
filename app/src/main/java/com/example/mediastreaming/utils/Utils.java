package com.example.mediastreaming.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

public class Utils {
    public static float displayWidth(Context context){
        return displayMetrics(context).widthPixels;
    }
    public static float displayHeight(Context context){
        return displayMetrics(context).heightPixels;
    }
    private static DisplayMetrics displayMetrics(Context context){
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics;
    }
}
