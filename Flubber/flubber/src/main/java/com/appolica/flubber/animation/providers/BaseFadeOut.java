package com.appolica.flubber.animation.providers;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.appolica.flubber.AnimationBody;
import com.appolica.flubber.Flubber;

public abstract class BaseFadeOut extends BaseProvider {

    public BaseFadeOut() {
        super(Flubber.Curve.SPRING);
    }

    @Override
    public Animator createAnimationFor(AnimationBody animationBody, View view) {
        initInterpolatorFor(animationBody);
        return getAnimationFor(animationBody, view);
    }

    @Override
    public Animator getAnimationFor(AnimationBody animationBody, View view) {

        final AnimatorSet animatorSet = new AnimatorSet();

        final Animator alphaAnimation = getAlpha(animationBody, view);
        final Animator translateAnimation = getTranslation(animationBody, view);

        translateAnimation.setInterpolator(getInterpolatorProvider().createInterpolatorFor(animationBody));

        animatorSet.play(translateAnimation)
                .with(alphaAnimation);

        return animatorSet;
    }

    protected abstract Animator getTranslation(AnimationBody animationBody, View view);

    protected Animator getAlpha(AnimationBody animationBody, View view) {

        final ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(view, View.ALPHA, 1f, 0f);

        alphaAnimation.setInterpolator(new LinearInterpolator());

        return alphaAnimation;
    }
}