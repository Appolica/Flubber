package com.appolica.flubber.animation.providers;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.appolica.flubber.AnimationBody;
import com.appolica.flubber.Flubber;

public class Alpha implements Flubber.AnimationProvider {
    @Override
    public Animator createAnimationFor(AnimationBody animationBody) {
        final View view = animationBody.getView();

        final ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(view, View.ALPHA, 0f, 1f);
        alphaAnimation.setInterpolator(new LinearInterpolator());

        return alphaAnimation;

    }
}
