package top.yunp.drivingtest.controllers.subject1;

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

    public void btnNavigateToRandomExamFragmentClickedHandler(View v) {

        fragment.getView().startAnimation(new Translate3D(0, -90, true, 350, new AnimationListenerAdapter() {
            @Override
            public void onAnimationEnd(Animation animation) {
                RandomExamFragment randomExamFragment = new RandomExamFragment();
                randomExamFragment.setInitAnimation(new Translate3D(90, 0, false, 350, null));
                fragment.getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.examContainer, randomExamFragment)
                        .commit();
            }
        }));

    }
}
