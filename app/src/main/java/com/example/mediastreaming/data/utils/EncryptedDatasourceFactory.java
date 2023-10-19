package com.example.mediastreaming.data.utils;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DefaultDataSourceFactory;

@UnstableApi
public class EncryptedDatasourceFactory implements DataSource.Factory {
    private final String key;

    public EncryptedDatasourceFactory(String key){
        this.key = key;
    }
    public EncryptedDatasourceFactory(Context context, DefaultDataSourceFactory defaultDataSourceFactory,String key){
        this.key = key;
    }

    @NonNull
    @Override
    public DataSource createDataSource() {
        return new EncryptedMediaSource(key);
    }
}
