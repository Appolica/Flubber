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

        return animation;
    }

    @Override
    protected void setupRepeating(Animator animation, AnimationBody animationBody) {
        int repeatCount = animationBody.getRepeatCount();
        if (repeatCount == 0) {
            repeatCount = 3;
        } else {
            repeatCount *= 3;
        }

        ((ObjectAnimator) animation).setRepeatCount(repeatCount);
        ((ObjectAnimator) animation).setRepeatMode(ValueAnimator.REVERSE);
    }
}
