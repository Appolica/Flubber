package com.appolica.flubber.animation.providers;

import android.animation.ObjectAnimator;
import android.view.View;

import androidx.annotation.NonNull;

import com.appolica.flubber.AnimationBody;
import com.appolica.flubber.utils.DimensionUtils;

public class FadeOutDown extends BaseFadeOut {

    @NonNull
    protected ObjectAnimator getTranslation(AnimationBody animationBody, View view) {

        final float startY = 0f;
        final float endY = DimensionUtils.dp2px(300) * animationBody.getForce();

        final ObjectAnimator translateAnimation =
                ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, startY, endY);

        return translateAnimation;
    }
}
