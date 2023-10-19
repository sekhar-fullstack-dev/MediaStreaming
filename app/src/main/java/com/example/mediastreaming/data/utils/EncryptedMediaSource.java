package com.example.mediastreaming.data.utils;

import android.net.Uri;
import android.os.Build;
import android.security.keystore.KeyProperties;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.TransferListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

@UnstableApi
public class EncryptedMediaSource implements DataSource {
    private final String key;
    private Uri uri;
    private CipherInputStream inputStream;

    public EncryptedMediaSource(String key){
        this.key = key;
    }
    @Override
    public void addTransferListener(TransferListener transferListener) {

    }

    @Override
    public long open(DataSpec dataSpec) throws IOException {
        this.uri = dataSpec.uri;
        try {
            byte[] aByte = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), KeyProperties.KEY_ALGORITHM_AES);
            IvParameterSpec ivSpec = new IvParameterSpec(aByte);
            Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivSpec);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                InputStream in = new URL(String.valueOf(uri)).openStream();
                inputStream = new CipherInputStream(in, cipher);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSpec.length;
    }

    @Nullable
    @Override
    public Uri getUri() {
        return uri;
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
    }

    @Override
    public int read(@NonNull byte[] buffer, int offset, int length) throws IOException {
        if (length == 0) {
            return 0;
        } else {
            if (inputStream!=null){
                return inputStream.read(buffer, offset, length);
            }
            else{
                return 0;
            }
        }
    }
}
