package com.appolica.flubber.interpolator.providers.bezier;


import androidx.core.view.animation.PathInterpolatorCompat;
import android.view.animation.Interpolator;

import com.appolica.flubber.AnimationBody;
import com.appolica.flubber.Flubber;

public class BzrSpring implements Flubber.InterpolatorProvider {
    @Override
    public Interpolator createInterpolatorFor(AnimationBody animationBody) {
        final float force = animationBody.getForce();
        return PathInterpolatorCompat.create(0.5f, 1.1f + force / 3, 1f, 1f);
    }
}
