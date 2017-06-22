package top.yunp.drivingtest.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import top.yunp.drivingtest.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Subject1Fragment extends SubjectFragment {


    public Subject1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_subject1, container, false);
    }
}
