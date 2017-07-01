package top.yunp.drivingtest.controllers;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import top.yunp.drivingtest.databinding.FragmentTrainingBinding;
import top.yunp.drivingtest.reader.Question;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class TrainingFragment extends Fragment {


    private FragmentTrainingBinding binding;
    private TrainingFragmentController controller;

    public TrainingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTrainingBinding.inflate(inflater);
        controller = new TrainingFragmentController(this, binding) {
            @Override
            public List<Question> getQuestions() {
                return TrainingFragment.this.getQuestions();
            }

            @Override
            public TrainingType getTrainingType() {
                return TrainingFragment.this.getTrainingType();
            }
        };
        binding.setController(controller);
        return binding.getRoot();
    }

    protected abstract TrainingFragmentController.TrainingType getTrainingType();

    public FragmentTrainingBinding getBinding() {
        return binding;
    }

    public TrainingFragmentController getController() {
        return controller;
    }

    public abstract List<Question> getQuestions();
}
