package top.yunp.drivingtest.controllers;

import android.view.View;
import android.view.animation.Animation;

import top.yunp.drivingtest.R;
import top.yunp.drivingtest.anim.AnimationListenerAdapter;
import top.yunp.drivingtest.anim.Translate3D;
import top.yunp.drivingtest.databinding.FragmentMockExamBinding;

/**
 * Created by plter on 6/22/17.
 */

public class MockExamFragmentController {
    private final MockExamFragment fragment;
    private final FragmentMockExamBinding binding;

    public MockExamFragmentController(MockExamFragment fragment, FragmentMockExamBinding binding) {
        this.fragment = fragment;
        this.binding = binding;
    }
}
