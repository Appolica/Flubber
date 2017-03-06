package com.appolica.flubber.animation.providers;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;

import com.appolica.flubber.AnimationBody;
import com.appolica.flubber.utils.DimensionUtils;
import com.appolica.flubber.utils.KeyFrameUtil;

import static com.appolica.flubber.Flubber.FRACTIONS;

public class Shake extends BaseProvider {

    @Override
    public Animator getAnimationFor(AnimationBody animationBody, View view) {
        final float dX = DimensionUtils.dp2px(30);
        final float force = animationBody.getForce();

        float[] translationValues = {0f, (dX * force), (-dX * force), (dX * force), 0f, 0f};
        final PropertyValuesHolder translationPVH =
                PropertyValuesHolder.ofKeyframe(View.TRANSLATION_X, KeyFrameUtil.getKeyFrames(FRACTIONS, translationValues));

        final ObjectAnimator animation =
                ObjectAnimator.ofPropertyValuesHolder(view, translationPVH);

        return animation;
    }
}
