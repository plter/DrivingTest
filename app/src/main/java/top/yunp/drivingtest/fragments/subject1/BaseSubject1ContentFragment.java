package top.yunp.drivingtest.fragments.subject1;

import android.support.v4.app.Fragment;
import android.view.animation.Animation;

/**
 * Created by plter on 6/22/17.
 */

public class BaseSubject1ContentFragment extends Fragment {

    private Animation initAnimation = null;

    public Animation getInitAnimation() {
        return initAnimation;
    }

    public void setInitAnimation(Animation initAnimation) {
        this.initAnimation = initAnimation;
    }

    @Override
    public void onResume() {
        if (getInitAnimation() != null) {
            getView().startAnimation(getInitAnimation());
        }
        super.onResume();
    }
}
