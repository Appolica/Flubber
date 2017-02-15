package com.appolica.flubber.animation.providers;

import android.animation.Animator;

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
    public Animator createAnimationFor(AnimationBody animationBody) {
        initInterpolatorFor(animationBody);

        final Animator animation = getAnimationFor(animationBody);

        animation.setInterpolator(interpolatorProvider.createInterpolatorFor(animationBody));

        return animation;
    }

    protected void initInterpolatorFor(AnimationBody animationBody) {
        final Flubber.InterpolatorProvider provider = animationBody.getInterpolator();

        if (provider != null) {
            setInterpolatorProvider(provider);
        }
    }

    public abstract Animator getAnimationFor(AnimationBody animationBody);

    public Flubber.InterpolatorProvider getInterpolatorProvider() {
        return interpolatorProvider;
    }

    public void setInterpolatorProvider(Flubber.InterpolatorProvider interpolatorProvider) {
        this.interpolatorProvider = interpolatorProvider;
    }
}
