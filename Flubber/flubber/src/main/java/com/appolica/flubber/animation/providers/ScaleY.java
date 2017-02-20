package com.appolica.flubber.animation.providers;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

import com.appolica.flubber.AnimationBody;

public class ScaleY extends BaseProvider {
    @Override
    public Animator getAnimationFor(AnimationBody animationBody) {
        return ObjectAnimator.ofFloat(
                animationBody.getView(),
                View.SCALE_Y,
                animationBody.getStartScaleY(),
                animationBody.getEndScaleY());
    }
}
