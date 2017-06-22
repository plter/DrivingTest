package top.yunp.drivingtest.controllers.subject1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import top.yunp.drivingtest.databinding.FragmentMockExamBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class MockExamFragment extends BaseSubject1ContentFragment {


    private FragmentMockExamBinding binding;
    private MockExamFragmentController controller;

    public MockExamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMockExamBinding.inflate(inflater);
        controller = new MockExamFragmentController(this, binding);
        binding.setController(controller);
        return binding.getRoot();
    }
}
