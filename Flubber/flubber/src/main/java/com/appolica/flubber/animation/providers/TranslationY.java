package com.appolica.flubber.animation.providers;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

import com.appolica.flubber.AnimationBody;

public class TranslationY extends BaseProvider {
    @Override
    public Animator getAnimationFor(AnimationBody animationBody) {
        return ObjectAnimator.ofFloat(
                animationBody.getView(),
                View.TRANSLATION_Y,
                animationBody.getStartY(),
                animationBody.getEndY());
    }
}
