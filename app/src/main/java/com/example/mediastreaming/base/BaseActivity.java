package com.example.mediastreaming.base;

import android.Manifest;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public abstract class BaseActivity extends AppCompatActivity {
    private final int CAMERA_MIC_REQUEST_PERMISSION_CODE = 100;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void askCameraAndMicPermission() {
        requestPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.RECORD_AUDIO},CAMERA_MIC_REQUEST_PERMISSION_CODE);
    }
    protected void onCameraMicPermissionResult(boolean isPermission){

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CAMERA_MIC_REQUEST_PERMISSION_CODE) {
            if (grantResults.length > 0) {
                int count = 0;
                for (int i:grantResults){
                    if (i==0){
                        count++;
                    }
                }
                onCameraMicPermissionResult(count == 2);
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
