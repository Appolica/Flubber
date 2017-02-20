package com.appolica.flubber.animation.providers;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

import com.appolica.flubber.AnimationBody;

public class ScaleX extends BaseProvider {
    @Override
    public Animator getAnimationFor(AnimationBody animationBody) {
        return ObjectAnimator.ofFloat(
                animationBody.getView(),
                View.SCALE_X,
                animationBody.getStartScaleX(),
                animationBody.getEndScaleX());
    }
}
