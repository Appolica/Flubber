package com.appolica.flubber.animation.providers;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

import com.appolica.flubber.AnimationBody;
import com.appolica.flubber.Flubber;

public class FadeIn implements Flubber.AnimationProvider {
    @Override
    public Animator createAnimationFor(AnimationBody animationBody) {
        final View view = animationBody.getView();

        final ObjectAnimator animation = ObjectAnimator.ofFloat(view, View.ALPHA, 0f, 1f);

        animation.setInterpolator(animationBody.getInterpolator().createInterpolatorFor(animationBody));

        return animation;

    }
}
