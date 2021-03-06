package com.appolica.flubber.interpolator.providers.bezier;

import android.view.animation.Interpolator;

import androidx.core.view.animation.PathInterpolatorCompat;

import com.appolica.flubber.AnimationBody;
import com.appolica.flubber.Flubber;

public class EaseOutCirc implements Flubber.InterpolatorProvider {
    @Override
    public Interpolator createInterpolatorFor(AnimationBody animationBody) {
        return PathInterpolatorCompat.create(0.075f, 0.82f, 0.165f, 1f);
    }
}
