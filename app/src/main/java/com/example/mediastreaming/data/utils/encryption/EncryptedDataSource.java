package com.example.mediastreaming.data.utils.encryption;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.media3.common.C;
import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.TransferListener;

import java.io.EOFException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

@UnstableApi
public class EncryptedDataSource implements DataSource {
    private static final String TAG = "EncryptedDataSource";
    private static final int BUFFER_SIZE = 64 * 1024; // 64 KB buffer size

    private final DataSource upstream;
    private final String encryptionKey; // Replace with your encryption key

    private boolean opened;
    private long bytesRemaining;
    private byte[] readBuffer;
    private int readBufferOffset;
    private int readBufferLength;

    public EncryptedDataSource(DataSource upstream, String encryptionKey) {
        this.upstream = upstream;
        this.encryptionKey = encryptionKey;
        this.opened = false;
        this.bytesRemaining = C.LENGTH_UNSET;
        this.readBuffer = new byte[BUFFER_SIZE];
    }


    @Nullable
    @Override
    public Uri getUri() {
        return null;
    }

    @Override
    public int read(byte[] buffer, int offset, int length) throws IOException {
        Assertions.checkState(opened);

        if (bytesRemaining == 0) {
            return C.RESULT_END_OF_INPUT;
        }

        int bytesRead = upstream.read(readBuffer, 0, Math.min(length, (int) bytesRemaining));

        if (bytesRead == C.RESULT_END_OF_INPUT) {
            if (bytesRemaining != C.LENGTH_UNSET) {
                throw new EOFException();
            }
            return C.RESULT_END_OF_INPUT;
        }

        // Implement your decryption logic here using the AES encryption key
        // Replace this with your actual decryption logic
        byte[] decryptedData;
        try {
            decryptedData = decryptData(readBuffer, 0, bytesRead);
        } catch (NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException |
                 InvalidKeyException | InvalidAlgorithmParameterException |
                 NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        // Copy the decrypted data to the output buffer
        System.arraycopy(decryptedData, 0, buffer, offset, decryptedData.length);

        if (bytesRemaining != C.LENGTH_UNSET) {
            bytesRemaining -= decryptedData.length;
        }

        return decryptedData.length;
    }

    @Override
    public void addTransferListener(@NonNull TransferListener transferListener) {

    }

    @Override
    public long open(@NonNull DataSpec dataSpec) throws IOException {
        Assertions.checkState(!opened);

        upstream.open(dataSpec);

        // Set the number of bytes remaining to be read
        if (dataSpec.length == C.LENGTH_UNSET) {
            bytesRemaining = C.LENGTH_UNSET;
        } else {
            bytesRemaining = dataSpec.length;
        }

        opened = true;
        return dataSpec.length;
    }

    @Override
    public void close() throws IOException {
        Assertions.checkState(opened);
        upstream.close();
        opened = false;
    }

    // Implement your decryption logic here using the AES encryption key
    private byte[] decryptData(byte[] encryptedData, int offset, int length) throws NoSuchPaddingException, NoSuchAlgorithmException, UnsupportedEncodingException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] keyBytes = encryptionKey.getBytes(StandardCharsets.UTF_8);

        // Create the AES key spec
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

        // Create an initialization vector (IV) of all zeros (16 bytes)
        byte[] ivBytes = new byte[16];
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

        // Initialize the Cipher for decryption
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);

        // Decrypt the data
        return cipher.doFinal(encryptedData);
    }
}

