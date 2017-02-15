package com.appolica.flubber.animation.providers;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.appolica.flubber.AnimationBody;
import com.appolica.flubber.Flubber;
import com.appolica.flubber.utils.DimensionUtils;
import com.appolica.flubber.utils.KeyFrameUtil;

import static com.appolica.flubber.Flubber.FRACTIONS;

public class Wobble extends BaseProvider {

    @Override
    public Animator createAnimationFor(AnimationBody animationBody) {
        initInterpolatorFor(animationBody);
        return getAnimationFor(animationBody);
    }

    @Override
    public Animator getAnimationFor(AnimationBody animationBody) {
        final AnimatorSet animatorSet = new AnimatorSet();

        final Animator rotationAnimator = getRotation(animationBody);
        final Animator translationAnimator = getShake(animationBody);

        animatorSet.play(translationAnimator)
                .with(rotationAnimator);

        return animatorSet;
    }

    private Animator getRotation(AnimationBody animationBody) {
        final float force = animationBody.getForce();

        float[] rotationValues = {
                (float) Math.toDegrees(0),
                (float) Math.toDegrees(0.3f * force),
                (float) Math.toDegrees(-0.3f * force),
                (float) Math.toDegrees(0.3f * force),
                (float) Math.toDegrees(0f),
                (float) Math.toDegrees(0f)
        };

        final PropertyValuesHolder pvhRotation =
                PropertyValuesHolder.ofKeyframe(View.ROTATION, KeyFrameUtil.getKeyFrames(Flubber.FRACTIONS, rotationValues));

        final ObjectAnimator animation =
                ObjectAnimator.ofPropertyValuesHolder(animationBody.getView(), pvhRotation);

        animation.setInterpolator(new LinearInterpolator());

        return animation;
    }

    private Animator getShake(AnimationBody animationBody) {
        final float dX = DimensionUtils.dp2px(30);
        final float force = animationBody.getForce();

        float[] translationValues = {0f, (dX * force), (-dX * force), (dX * force), 0f, 0f};
        final PropertyValuesHolder translationPVH =
                PropertyValuesHolder.ofKeyframe(View.TRANSLATION_X, KeyFrameUtil.getKeyFrames(FRACTIONS, translationValues));

        final ObjectAnimator animation =
                ObjectAnimator.ofPropertyValuesHolder(animationBody.getView(), translationPVH);

        animation.setInterpolator(getInterpolatorProvider().createInterpolatorFor(animationBody));

        return animation;
    }
}
