package com.appolica.flubber.animation.providers;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.util.DisplayMetrics;
import android.view.View;

import com.appolica.flubber.AnimationBody;
import com.appolica.flubber.utils.DimensionUtils;

public class Fall extends BaseProvider {
    @Override
    public Animator createAnimationFor(AnimationBody animationBody, View view) {
        initInterpolatorFor(animationBody);
        return getAnimationFor(animationBody, view);
    }

    @Override
    public Animator getAnimationFor(AnimationBody animationBody, View view) {
        final AnimatorSet animatorSet = new AnimatorSet();

        final ObjectAnimator translateAnimation = getTranslation(animationBody, view);
        final ObjectAnimator rotateAnimation = getRotation(view);

        animatorSet
                .play(translateAnimation)
                .with(rotateAnimation);

        return animatorSet;
    }

    private ObjectAnimator getRotation(View view) {
        final float startRotation = view.getRotation();
        final float endRotation = (float) Math.toDegrees(15 * (Math.PI / 180));

        return ObjectAnimator.ofFloat(view, View.ROTATION, startRotation, endRotation);
    }

    private ObjectAnimator getTranslation(AnimationBody animationBody, View view) {
        final DisplayMetrics displayMetrics = DimensionUtils.getDisplayMetrics(view.getContext());

        final int startY = 0;
        final float endY = (displayMetrics.heightPixels - view.getY()) * animationBody.getForce();

        final ObjectAnimator animation = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y.getName(), startY, endY);

        animation.setInterpolator(getInterpolatorProvider().createInterpolatorFor(animationBody));

        return animation;
    }
}
