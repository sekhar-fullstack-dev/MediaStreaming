package com.example.mediastreaming.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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

    public static String readRawTextFile(Context ctx, int resId) {
        InputStream inputStream = ctx.getResources().openRawResource(resId);
        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line;
        StringBuilder text = new StringBuilder();

        try {
            while ((line = buffreader.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
        } catch (IOException e) {
            return null;
        } finally {
            try {
                buffreader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return text.toString();
    }
}
