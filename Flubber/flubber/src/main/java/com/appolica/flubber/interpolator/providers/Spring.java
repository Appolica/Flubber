package com.appolica.flubber.interpolator.providers;

import android.view.animation.Interpolator;

import com.appolica.flubber.AnimationBody;
import com.appolica.flubber.SpringInterpolator;

public class Spring implements com.appolica.flubber.Flubber.InterpolatorProvider {
    @Override
    public Interpolator createInterpolatorFor(AnimationBody animationBody) {
        final float damping = animationBody.getDamping();
        final float velocity = animationBody.getVelocity();

        return new SpringInterpolator(damping, velocity);
    }
}
