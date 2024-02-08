package com.example.mediastreaming.base;

import android.Manifest;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {
    private static final int REQUEST_STORAGE_PERMISSION = 100;
    private OnCheckListener onCheckListener;

    public void askExternalStoragePermission(){

    }
    public void checkReadPermission(OnCheckListener onCheckListener){
        this.onCheckListener = onCheckListener;
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_STORAGE_PERMISSION);
        } else {
            onCheckListener.onChecked(true);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_STORAGE_PERMISSION && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            onCheckListener.onChecked(true);
        }
        else{
            onCheckListener.onChecked(false);
        }
    }


    public interface OnCheckListener{
        void onChecked(boolean isGranted);
    }
}
