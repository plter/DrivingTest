package top.yunp.drivingtest.controllers.exam.subject1;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import top.yunp.drivingtest.R;
import top.yunp.drivingtest.databinding.FragmentSubject1Binding;
import top.yunp.drivingtest.controllers.exam.SubjectFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class Subject1Fragment extends SubjectFragment {


    private FragmentSubject1Binding binding;

    public Subject1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSubject1Binding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        getChildFragmentManager().beginTransaction().replace(R.id.subject1FragmentContainer, new Subject1EntryFragment()).commit();
        super.onResume();
    }
}
