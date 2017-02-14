package com.appolica.flubber.animation.providers;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.view.View;

import com.appolica.flubber.AnimationBody;
import com.appolica.flubber.Flubber;

public class Flash implements Flubber.AnimationProvider {
    @Override
    public Animator createAnimationFor(AnimationBody animationBody) {
        final PropertyValuesHolder alphaPVH = PropertyValuesHolder.ofFloat(View.ALPHA, 1f, 0f);
        final ValueAnimator animation =
                ObjectAnimator.ofPropertyValuesHolder(animationBody.getView(), alphaPVH);

        animation.setRepeatCount(3);
        animation.setRepeatMode(ValueAnimator.REVERSE);

        animation.setInterpolator(animationBody.getInterpolator().createInterpolatorFor(animationBody));

        return animation;

    }
}
