package com.appolica.flubber.animation.providers;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

import com.appolica.flubber.AnimationBody;

public class TranslationX extends BaseProvider {
    @Override
    public Animator getAnimationFor(AnimationBody animationBody) {

        return ObjectAnimator.ofFloat(
                animationBody.getView(),
                View.TRANSLATION_X,
                animationBody.getStartX(),
                animationBody.getEndX());
    }
}
