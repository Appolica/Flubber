package com.appolica.flubber.interpolator.providers.bezier;

import android.view.animation.Interpolator;

import androidx.core.view.animation.PathInterpolatorCompat;

import com.appolica.flubber.AnimationBody;
import com.appolica.flubber.Flubber;

public class EaseInOutBack implements Flubber.InterpolatorProvider {
    @Override
    public Interpolator createInterpolatorFor(AnimationBody animationBody) {
        return PathInterpolatorCompat.create(0.68f, -0.55f, 0.265f, 1.55f);
    }
}
