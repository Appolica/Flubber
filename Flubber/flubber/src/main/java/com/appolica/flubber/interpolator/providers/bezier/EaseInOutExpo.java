package com.appolica.flubber.interpolator.providers.bezier;

import androidx.core.view.animation.PathInterpolatorCompat;
import android.view.animation.Interpolator;

import com.appolica.flubber.AnimationBody;
import com.appolica.flubber.Flubber;

public class EaseInOutExpo implements Flubber.InterpolatorProvider {
    @Override
    public Interpolator createInterpolatorFor(AnimationBody animationBody) {
        return PathInterpolatorCompat.create(1f, 0f, 0f, 1f);
    }
}
