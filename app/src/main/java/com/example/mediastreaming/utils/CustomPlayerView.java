package com.example.mediastreaming.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import androidx.media3.ui.PlayerView;

public class CustomPlayerView extends PlayerView {

    private int videoWidth = 0;
    private int videoHeight = 0;

    public CustomPlayerView(Context context) {
        super(context);
    }

    public CustomPlayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomPlayerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setVideoSize(int width, int height) {
        videoWidth = width;
        videoHeight = height;
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (videoWidth > 0 && videoHeight > 0) {
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = MeasureSpec.getSize(heightMeasureSpec);
            float aspectRatio = (float) videoHeight / videoWidth;
            Log.d(getClass().getName(), "onMeasure: w,h,vw,vh:"+width+","+height+","+videoWidth+","+videoHeight);

            // Calculate the optimal size of the video surface
            if (videoWidth > videoHeight) { // Horizontal video
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            } else { // Vertical video
                int newWidth, newHeight;
                newHeight = videoHeight;
                newWidth = videoWidth;
                if (newWidth > width) {
                    // If new width is larger than screen, scale down both to fit screen
                    newWidth = width;
                    newHeight = (int) (width * aspectRatio);
                }
                int newWidthMeasureSpec = MeasureSpec.makeMeasureSpec(newWidth, MeasureSpec.EXACTLY);
                int newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(newHeight, MeasureSpec.EXACTLY);
                super.onMeasure(newWidthMeasureSpec, newHeightMeasureSpec);
            }
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}

