package top.yunp.drivingtest.fragments.subject1;

import top.yunp.drivingtest.R;
import top.yunp.drivingtest.databinding.FragmentSubject1Binding;

/**
 * Created by plter on 6/22/17.
 */

public class Subject1FragmentController {
    private final FragmentSubject1Binding binding;
    private final Subject1Fragment fragment;

    public Subject1FragmentController(Subject1Fragment fragment, FragmentSubject1Binding binding) {
        this.binding = binding;
        this.fragment = fragment;
    }

    public void onResume() {
        fragment.getChildFragmentManager().beginTransaction().replace(R.id.examContainer, new RandomExamFragment()).commit();
    }
}
