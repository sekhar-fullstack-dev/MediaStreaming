package com.example.mediastreaming.ui.activities.dashboard.ui.classRoom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.mediastreaming.R;
import com.example.mediastreaming.databinding.FragmentClassRoomBinding;

public class ClassRoomFragment extends Fragment {
    private FragmentClassRoomBinding binding;
    private ClassRoomFragmentViewModel model;
    private NavController navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_class_room, container, false);
        binding.setLifecycleOwner(requireActivity());
        model = new ViewModelProvider(this).get(ClassRoomFragmentViewModel.class);
        binding.setModel(model);
        observe();
        return binding.getRoot();
    }

    private void observe() {
        model.getStartExamClicked().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean!=null && aBoolean){
                    Navigation.findNavController(requireActivity(),R.id.nav_host_fragment_activity_dashboard).navigate(R.id.action_navigation_classroom_to_navigation_create_exam);
                    model.setStartExamClicked(false);
                }
            }
        });
        model.getMyQuestions().observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean!=null && aBoolean){
                Navigation.findNavController(requireActivity(),R.id.nav_host_fragment_activity_dashboard).navigate(R.id.action_navigation_classroom_to_navigation_my_questions);
                model.setMyQuestions(false);
            }
        });
    }
}
