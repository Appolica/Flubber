package com.appolica.flubber.animation.providers;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.util.DisplayMetrics;
import android.view.View;

import com.appolica.flubber.AnimationBody;
import com.appolica.flubber.Flubber;
import com.appolica.flubber.utils.DimensionUtils;

public class Fall implements Flubber.AnimationProvider {
    @Override
    public Animator createAnimationFor(AnimationBody animationBody) {
        final AnimatorSet animatorSet = new AnimatorSet();

        final View view = animationBody.getView();
        final DisplayMetrics displayMetrics = DimensionUtils.getDisplayMetrics(view.getContext());

        final int startY = 0;
        final float endY = (displayMetrics.heightPixels - view.getY()) * animationBody.getForce();

        final ObjectAnimator translateAnimation =
                ObjectAnimator.ofFloat(view, View.TRANSLATION_Y.getName(), startY, endY);

        translateAnimation.setInterpolator(animationBody.getInterpolator().createInterpolatorFor(animationBody));


        final float startRotation = view.getRotation();
        final float endRotation = (float) Math.toDegrees(15 * (Math.PI / 180));

        final ObjectAnimator rotateAnimation =
                ObjectAnimator.ofFloat(view, View.ROTATION, startRotation, endRotation);

        animatorSet
                .play(translateAnimation)
                .with(rotateAnimation);

        return animatorSet;

    }
}
