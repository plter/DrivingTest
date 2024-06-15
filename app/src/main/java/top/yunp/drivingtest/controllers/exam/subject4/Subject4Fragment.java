package top.yunp.drivingtest.controllers.exam.subject4;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import top.yunp.drivingtest.R;
import top.yunp.drivingtest.controllers.exam.SubjectFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class Subject4Fragment extends SubjectFragment {


    public Subject4Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_subject4, container, false);
    }

    @Override
    public void onResume() {
        getChildFragmentManager()
                .beginTransaction()
                .replace(R.id.subject4FragmentContainer, new Subject4EntryFragment())
                .commit();
        super.onResume();
    }
}
