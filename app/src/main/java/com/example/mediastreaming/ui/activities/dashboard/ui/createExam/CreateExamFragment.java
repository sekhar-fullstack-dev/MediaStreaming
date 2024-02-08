package com.example.mediastreaming.ui.activities.dashboard.ui.createExam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mediastreaming.base.BaseFragment;
import com.example.mediastreaming.databinding.FragmentCreateExamBinding;

public class CreateExamFragment extends BaseFragment {
    private FragmentCreateExamBinding binding;
    private CreateExamFragViewmodel model;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCreateExamBinding.inflate(inflater,container,false);
        model = new ViewModelProvider(requireActivity()).get(CreateExamFragViewmodel.class);
        binding.setModel(model);
        binding.setLifecycleOwner(requireActivity());
        observe();
        return binding.getRoot();
    }

    private void observe(){
        model.addQuestions.observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean!=null && aBoolean){

            }
        });
    }
}
