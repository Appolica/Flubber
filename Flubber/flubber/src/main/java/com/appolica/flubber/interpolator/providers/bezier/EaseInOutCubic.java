package com.appolica.flubber.interpolator.providers.bezier;

import androidx.core.view.animation.PathInterpolatorCompat;
import android.view.animation.Interpolator;

import com.appolica.flubber.AnimationBody;
import com.appolica.flubber.Flubber;

public class EaseInOutCubic implements Flubber.InterpolatorProvider {
    @Override
    public Interpolator createInterpolatorFor(AnimationBody animationBody) {
        return PathInterpolatorCompat.create(0.645f, 0.045f, 0.355f, 1f);
    }
}
