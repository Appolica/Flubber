package com.appolica.flubber.animation.providers;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

import com.appolica.flubber.AnimationBody;
import com.appolica.flubber.utils.DimensionUtils;

public class FadeOutRight extends BaseFadeOut{

    @Override
    protected Animator getTranslation(AnimationBody animationBody, View view) {

        final float startY = 0;
        final float endY = DimensionUtils.dp2px(300) * animationBody.getForce();

        final ObjectAnimator translateAnimation =
                ObjectAnimator.ofFloat(view, View.TRANSLATION_X, startY, endY);

        return translateAnimation;
    }
}
