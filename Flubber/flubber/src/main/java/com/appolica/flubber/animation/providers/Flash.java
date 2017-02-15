package com.appolica.flubber.animation.providers;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.view.View;

import com.appolica.flubber.AnimationBody;

public class Flash extends BaseProvider {

    @Override
    public Animator getAnimationFor(AnimationBody animationBody) {
        final PropertyValuesHolder alphaPVH = PropertyValuesHolder.ofFloat(View.ALPHA, 1f, 0f);
        final ValueAnimator animation =
                ObjectAnimator.ofPropertyValuesHolder(animationBody.getView(), alphaPVH);

        animation.setRepeatCount(3);
        animation.setRepeatMode(ValueAnimator.REVERSE);

        return animation;

    }
}
