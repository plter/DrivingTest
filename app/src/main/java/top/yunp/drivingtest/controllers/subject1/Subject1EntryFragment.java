package top.yunp.drivingtest.controllers.subject1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import top.yunp.drivingtest.databinding.FragmentSubject1EntryBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class Subject1EntryFragment extends Fragment {


    private FragmentSubject1EntryBinding binding;

    public Subject1EntryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSubject1EntryBinding.inflate(inflater);
        binding.setController(new Subject1EntryFragmentController(binding, this));
        return binding.getRoot();
    }

}
