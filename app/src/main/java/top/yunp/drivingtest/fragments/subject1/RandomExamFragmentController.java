package top.yunp.drivingtest.fragments.subject1;

import android.view.View;
import android.view.animation.Animation;

import top.yunp.drivingtest.R;
import top.yunp.drivingtest.anim.AnimationListenerAdapter;
import top.yunp.drivingtest.anim.Translate3D;
import top.yunp.drivingtest.databinding.FragmentRandomExamBinding;

/**
 * Created by plter on 6/22/17.
 */

public class RandomExamFragmentController {
    private final FragmentRandomExamBinding binding;
    private final RandomExamFragment fragment;

    public RandomExamFragmentController(RandomExamFragment fragment, FragmentRandomExamBinding binding) {
        this.binding = binding;
        this.fragment = fragment;
    }

    public void btnNavigateToMockFragmentClickedHandler(View v) {

        fragment.getView().startAnimation(new Translate3D(0, 90, true, 350, new AnimationListenerAdapter() {
            @Override
            public void onAnimationEnd(Animation animation) {
                MockExamFragment mockExamFragment = new MockExamFragment();
                mockExamFragment.setInitAnimation(new Translate3D(-90, 0, false, 350, null));
                fragment.getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.examContainer, mockExamFragment)
                        .commit();
            }
        }));


    }
}
