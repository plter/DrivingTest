package top.yunp.drivingtest.anim;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by plter on 6/22/17.
 */

public class Translate3D extends Animation {


    private Camera camera;
    private int halfParentWidth;
    private int halfParentHeight;
    private float from, to, distance;
    private boolean farAway = true;

    public Translate3D(float from, float to, boolean farAway, long duration, AnimationListenerAdapter animationListener) {
        this.from = from;
        this.to = to;
        distance = to - from;
        this.farAway = farAway;
        setDuration(duration);
        setAnimationListener(animationListener);
    }

    public Translate3D(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        camera = new Camera();
        halfParentWidth = parentWidth / 2;
        halfParentHeight = parentHeight / 2;

        super.initialize(width, height, parentWidth, parentHeight);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {

        Matrix matrix = t.getMatrix();

        camera.save();
        camera.translate(0, 0, (farAway ? interpolatedTime : 1 - interpolatedTime) * halfParentWidth);
        camera.rotateY(interpolatedTime * distance + from);
        camera.getMatrix(matrix);
        camera.restore();

        matrix.preTranslate(-halfParentWidth, -halfParentHeight);
        matrix.postTranslate(halfParentWidth, halfParentHeight);
    }
}
