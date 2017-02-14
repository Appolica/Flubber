package com.appolica.flubber.animation.providers;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;

import com.appolica.flubber.AnimationBody;
import com.appolica.flubber.Flubber;

public class FlipX implements Flubber.AnimationProvider {
    @Override
    public Animator createAnimationFor(AnimationBody animationBody) {
        final View view = animationBody.getView();

        final float startRotation = view.getRotationX();
        final float endRotation = startRotation + 180f;

        final PropertyValuesHolder rotationPVH =
                PropertyValuesHolder.ofFloat(View.ROTATION_X, startRotation, endRotation);

        final ObjectAnimator animation =
                ObjectAnimator.ofPropertyValuesHolder(view, rotationPVH);

        animation.setInterpolator(animationBody.getInterpolator().createInterpolatorFor(animationBody));

        return animation;

    }
}
