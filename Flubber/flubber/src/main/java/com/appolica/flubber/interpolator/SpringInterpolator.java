package com.appolica.flubber.interpolator;

import android.view.animation.Interpolator;

public class SpringInterpolator implements Interpolator {

    public static final int NUM_OF_POINTS = 500;

    private float damping;
    private float velocity;

    public SpringInterpolator(float damping, float velocity) {
        this.damping = damping;
        this.velocity = velocity;
    }

    @Override
    public float getInterpolation(float input) {
        return getSpringValues(input, 0, 1);
    }

    private float getSpringValues(float x, float fromValue, float toValue) {

        final float distance = toValue - fromValue;
        final float normalizedValue = normalizeValue(x, damping, velocity);

        return toValue - distance * normalizedValue;
    }

    private float normalizeValue(float x, float damping, float velocity) {
        return (float) (Math.pow(Math.E, -damping * x * 10) * Math.cos(velocity * x * 10));
    }
}
