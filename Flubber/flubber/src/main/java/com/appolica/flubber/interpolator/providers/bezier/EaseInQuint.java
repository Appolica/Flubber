package com.appolica.flubber.interpolator.providers.bezier;

import androidx.core.view.animation.PathInterpolatorCompat;
import android.view.animation.Interpolator;

import com.appolica.flubber.AnimationBody;
import com.appolica.flubber.Flubber;

public class EaseInQuint implements Flubber.InterpolatorProvider {
    @Override
    public Interpolator createInterpolatorFor(AnimationBody animationBody) {
        return PathInterpolatorCompat.create(0.755f, 0.05f, 0.855f, 0.06f);
    }
}
