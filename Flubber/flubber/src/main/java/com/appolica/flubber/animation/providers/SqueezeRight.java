package com.appolica.flubber.animation.providers;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;

import com.appolica.flubber.AnimationBody;
import com.appolica.flubber.Flubber;
import com.appolica.flubber.utils.DimensionUtils;

public class SqueezeRight implements Flubber.AnimationProvider {
    @Override
    public Animator createAnimationFor(AnimationBody animationBody) {
        final View view = animationBody.getView();

        final float startX = DimensionUtils.dp2px(-800);
        final float endX = 0f;

        final PropertyValuesHolder translationPVH =
                PropertyValuesHolder.ofFloat(View.TRANSLATION_X, startX, endX);


        final float startScaleY = 3 * animationBody.getForce();
        final float endScaleY = 1f;

        final PropertyValuesHolder scalePVH =
                PropertyValuesHolder.ofFloat(View.SCALE_X, startScaleY, endScaleY);


        final ObjectAnimator animation =
                ObjectAnimator.ofPropertyValuesHolder(view, translationPVH, scalePVH);

        animation.setInterpolator(animationBody.getInterpolator().createInterpolatorFor(animationBody));

        return animation;

    }
}
