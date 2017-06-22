package top.yunp.drivingtest.fragments.subject1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import top.yunp.drivingtest.R;
import top.yunp.drivingtest.databinding.FragmentRandomExamBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class RandomExamFragment extends BaseSubject1ContentFragment {


    private FragmentRandomExamBinding binding;
    private RandomExamFragmentController controller;

    public RandomExamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRandomExamBinding.inflate(inflater);
        controller = new RandomExamFragmentController(this, binding);
        binding.setController(controller);
        return binding.getRoot();
    }

}
