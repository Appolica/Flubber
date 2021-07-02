package com.appolica.flubber.interpolator.providers.bezier;

import android.view.animation.Interpolator;

import androidx.core.view.animation.PathInterpolatorCompat;

import com.appolica.flubber.AnimationBody;
import com.appolica.flubber.Flubber;

public class EaseInOutQuart implements Flubber.InterpolatorProvider {
    @Override
    public Interpolator createInterpolatorFor(AnimationBody animationBody) {
        return PathInterpolatorCompat.create(0.77f, 0f, 0.175f, 1f);
    }
}
