package com.example.mediastreaming;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class BindingAdapters {
    @BindingAdapter("imageURL")
    public static void loadImage(View view, String url){
        if (view!=null && url!=null && !url.isEmpty()){
            Glide.with(view.getContext()).load(url).into((ImageView)view);
        }
    }
    @BindingAdapter("clipOutline")
    public static void setClipOutline(View view, boolean val){
        if (view!=null){
            ((ImageView)view).setClipToOutline(val);
        }
    }
}
