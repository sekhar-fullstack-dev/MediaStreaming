package com.example.mediastreaming.data.utils.encryption;

import androidx.annotation.NonNull;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.datasource.DataSource;


@UnstableApi
public class EncryptedDataSourceFactory implements DataSource.Factory {
    private final DataSource.Factory upstreamFactory;
    private final String encryptionKey;

    public EncryptedDataSourceFactory(DataSource.Factory upstreamFactory, String encryptionKey) {
        this.upstreamFactory = upstreamFactory;
        this.encryptionKey = encryptionKey;
    }

    @NonNull
    @Override
    public DataSource createDataSource() {
        // Create a new instance of EncryptedDataSource for each request
        return new EncryptedDataSource(upstreamFactory.createDataSource(), encryptionKey);
    }
}

