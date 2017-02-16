package com.appolica.flubber.animation.providers;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.appolica.flubber.AnimationBody;
import com.appolica.flubber.Flubber;

public abstract class BaseFadeIn extends BaseProvider {

    public BaseFadeIn() {
        super(Flubber.Curve.SPRING);
    }

    @Override
    public Animator createAnimationFor(AnimationBody animationBody) {
        initInterpolatorFor(animationBody);
        return getAnimationFor(animationBody);
    }

    @Override
    public Animator getAnimationFor(AnimationBody animationBody) {

        final AnimatorSet animatorSet = new AnimatorSet();

        final Animator alphaAnimation = getAlpha(animationBody);
        final Animator translateAnimation = getTranslation(animationBody);

        translateAnimation.setInterpolator(getInterpolatorProvider().createInterpolatorFor(animationBody));

        animatorSet.play(translateAnimation)
                .with(alphaAnimation);

        return animatorSet;
    }

    protected abstract Animator getTranslation(AnimationBody animationBody);

    protected Animator getAlpha(AnimationBody animationBody) {
        final View view = animationBody.getView();

        final ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(view, View.ALPHA, 0f, 1f);

        alphaAnimation.setInterpolator(new LinearInterpolator());

        return alphaAnimation;
    }
}
