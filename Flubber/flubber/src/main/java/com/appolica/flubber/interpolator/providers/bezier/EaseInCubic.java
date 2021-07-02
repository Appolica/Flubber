package com.appolica.flubber.interpolator.providers.bezier;

import androidx.core.view.animation.PathInterpolatorCompat;
import android.view.animation.Interpolator;

import com.appolica.flubber.AnimationBody;
import com.appolica.flubber.Flubber;

public class EaseInCubic implements Flubber.InterpolatorProvider {
    @Override
    public Interpolator createInterpolatorFor(AnimationBody animationBody) {
        return PathInterpolatorCompat.create(0.55f, 0.055f, 0.675f, 0.19f);
    }
}
