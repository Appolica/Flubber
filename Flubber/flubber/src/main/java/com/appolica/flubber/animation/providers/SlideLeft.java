package com.appolica.flubber.animation.providers;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;

import com.appolica.flubber.AnimationBody;
import com.appolica.flubber.Flubber;
import com.appolica.flubber.utils.DimensionUtils;


public class SlideLeft implements Flubber.AnimationProvider {
    @Override
    public Animator createAnimationFor(final AnimationBody animationBody) {
        final View view = animationBody.getView();

        final float startY = DimensionUtils.dp2px(800);
        final float endY = 0f;

        final PropertyValuesHolder translationPVH =
                PropertyValuesHolder.ofFloat(View.TRANSLATION_X, startY, endY);

        final ObjectAnimator animation =
                ObjectAnimator.ofPropertyValuesHolder(view, translationPVH);

        animation.setInterpolator(animationBody.getInterpolator().createInterpolatorFor(animationBody));

        return animation;

    }


}
