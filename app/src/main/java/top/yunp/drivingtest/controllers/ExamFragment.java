package top.yunp.drivingtest.controllers;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import top.yunp.drivingtest.databinding.FragmentExamBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExamFragment extends Fragment {


    public ExamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentExamBinding binding = FragmentExamBinding.inflate(inflater);
        binding.setController(new ExamFragmentController(this, binding));
        return binding.getRoot();
    }

}
