package com.appolica.flubber.animation.providers;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

import com.appolica.flubber.AnimationBody;
import com.appolica.flubber.Flubber;
import com.appolica.flubber.utils.DimensionUtils;

public class FadeInLeft implements Flubber.AnimationProvider {
    @Override
    public Animator createAnimationFor(AnimationBody animationBody) {
        final View view = animationBody.getView();

        final AnimatorSet animatorSet = new AnimatorSet();

        final float startY = DimensionUtils.dp2px(300) * animationBody.getForce();
        final float endY = 0;

        final Animator alphaAnimation = Flubber.AnimationPreset.ALPHA.createAnimationFor(animationBody);

        final ObjectAnimator translateAnimation =
                ObjectAnimator.ofFloat(view, View.TRANSLATION_X, startY, endY);

        translateAnimation.setInterpolator(animationBody.getInterpolator().createInterpolatorFor(animationBody));

        animatorSet.play(translateAnimation)
                .with(alphaAnimation);

        return animatorSet;

    }
}
