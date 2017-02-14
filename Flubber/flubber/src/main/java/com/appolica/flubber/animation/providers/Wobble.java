package com.appolica.flubber.animation.providers;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.view.animation.LinearInterpolator;

import com.appolica.flubber.AnimationBody;
import com.appolica.flubber.Flubber;

public class Wobble implements Flubber.AnimationProvider {
    @Override
    public Animator createAnimationFor(AnimationBody animationBody) {
        final AnimatorSet animatorSet = new AnimatorSet();

        final Animator rotationAnimator = Flubber.AnimationPreset.ROTATION.createAnimationFor(animationBody);
        final Animator translationAnimator = Flubber.AnimationPreset.SHAKE.createAnimationFor(animationBody);

        rotationAnimator.setInterpolator(new LinearInterpolator());

        animatorSet.play(translationAnimator)
                .with(rotationAnimator);

        return animatorSet;

    }
}
