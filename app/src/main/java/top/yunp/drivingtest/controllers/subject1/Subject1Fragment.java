package top.yunp.drivingtest.controllers.subject1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import top.yunp.drivingtest.databinding.FragmentSubject1Binding;
import top.yunp.drivingtest.controllers.SubjectFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class Subject1Fragment extends SubjectFragment {


    private FragmentSubject1Binding binding;
    private Subject1FragmentController controller;

    public Subject1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSubject1Binding.inflate(inflater);
        controller = new Subject1FragmentController(this, binding);
        binding.setController(controller);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        controller.onResume();
        super.onResume();
    }
}
