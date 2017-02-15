package com.appolica.flubber.animation.providers;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

import com.appolica.flubber.AnimationBody;

public class FadeOut extends BaseProvider {

    @Override
    public Animator getAnimationFor(AnimationBody animationBody) {
        final View view = animationBody.getView();

        final ObjectAnimator animation = ObjectAnimator.ofFloat(view, View.ALPHA, 1f, 0f);

        return animation;
    }
}
