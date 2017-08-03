package com.appolica.flubber.animation.providers;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;

import com.appolica.flubber.AnimationBody;
import com.appolica.flubber.Flubber;


public abstract class BaseProvider implements Flubber.AnimationProvider {

    private Flubber.InterpolatorProvider interpolatorProvider;

    public BaseProvider() {
        // default interpolator
        interpolatorProvider = Flubber.Curve.BZR_EASE_IN;
    }

    public BaseProvider(Flubber.InterpolatorProvider provider) {
        this.interpolatorProvider = provider;
    }

    @Override
    public Animator createAnimationFor(AnimationBody animationBody, View view) {
        initInterpolatorFor(animationBody);

        final Animator animation = getAnimationFor(animationBody, view);

        setupAnimation(animationBody, animation);

        return animation;
    }

    protected void setupAnimation(AnimationBody animationBody, Animator animation) {
        animation.setInterpolator(interpolatorProvider.createInterpolatorFor(animationBody));
        setupRepeating(animation, animationBody);
    }

    protected void setupRepeating(Animator animation, AnimationBody animationBody) {
        if (animation instanceof ValueAnimator) {
            ((ValueAnimator) animation).setRepeatCount(animationBody.getRepeatCount());
            ((ValueAnimator) animation).setRepeatMode(animationBody.getRepeatMode());
        }
    }

    public abstract Animator getAnimationFor(AnimationBody animationBody, View view);

    protected void initInterpolatorFor(AnimationBody animationBody) {
        final Flubber.InterpolatorProvider provider = animationBody.getInterpolator();

        if (provider != null) {
            setInterpolatorProvider(provider);
        }
    }

    public Flubber.InterpolatorProvider getInterpolatorProvider() {
        return interpolatorProvider;
    }

    public void setInterpolatorProvider(Flubber.InterpolatorProvider interpolatorProvider) {
        this.interpolatorProvider = interpolatorProvider;
    }
}
