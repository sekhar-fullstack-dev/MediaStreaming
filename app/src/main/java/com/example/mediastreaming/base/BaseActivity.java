package com.example.mediastreaming.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import kotlin.NotImplementedError;

abstract public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected int getLayout(){
        throw new NotImplementedError();
    };

    public void checkPermission(String permission){

    }

}
