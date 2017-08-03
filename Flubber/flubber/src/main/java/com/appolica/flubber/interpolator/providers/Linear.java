package com.appolica.flubber.interpolator.providers;

import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.appolica.flubber.AnimationBody;
import com.appolica.flubber.Flubber;

public class Linear implements Flubber.InterpolatorProvider {
    @Override
    public Interpolator createInterpolatorFor(AnimationBody animationBody) {
        return new LinearInterpolator();
    }
}
