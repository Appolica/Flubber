package com.appolica.flubber.animation.providers;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;

import com.appolica.flubber.AnimationBody;

public class ZoomOut extends BaseProvider {

    @Override
    public Animator getAnimationFor(AnimationBody animationBody, View view) {

        final float scale = 2 * animationBody.getForce();

        final PropertyValuesHolder alphaPVH = PropertyValuesHolder.ofFloat(View.ALPHA, 1f, 0f);
        final PropertyValuesHolder scaleXPVH = PropertyValuesHolder.ofFloat(View.SCALE_X, 1f, scale);
        final PropertyValuesHolder scaleYPVH = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f, scale);

        final ObjectAnimator animation =
                ObjectAnimator.ofPropertyValuesHolder(view, alphaPVH, scaleXPVH, scaleYPVH);

        return animation;
    }
}
