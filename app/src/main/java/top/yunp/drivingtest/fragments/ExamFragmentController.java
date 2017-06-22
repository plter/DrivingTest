package top.yunp.drivingtest.fragments;

import top.yunp.drivingtest.adapters.ExamViewPagerAdapter;
import top.yunp.drivingtest.databinding.FragmentExamBinding;

/**
 * Created by plter on 6/22/17.
 */

public class ExamFragmentController {

    private ExamFragment fragment;
    private FragmentExamBinding binding;

    public ExamFragmentController(ExamFragment fragment, FragmentExamBinding binding) {
        this.fragment = fragment;
        this.binding = binding;
        configVP();
    }

    private void configVP() {
        binding.vp.setAdapter(new ExamViewPagerAdapter(fragment.getActivity(), fragment.getChildFragmentManager()));
        binding.tab.setupWithViewPager(binding.vp);
    }
}
