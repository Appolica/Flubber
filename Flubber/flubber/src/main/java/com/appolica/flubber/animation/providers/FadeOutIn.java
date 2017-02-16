package com.appolica.flubber.animation.providers;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;

import com.appolica.flubber.AnimationBody;

public class FadeOutIn extends BaseProvider {

    @Override
    public Animator getAnimationFor(AnimationBody animationBody) {
        final View view = animationBody.getView();

        final ObjectAnimator animation = ObjectAnimator.ofFloat(view, View.ALPHA, 1f, 0f);

        return animation;
    }

    @Override
    protected void setupRepeating(Animator animation, AnimationBody animationBody) {
        ((ObjectAnimator) animation).setRepeatCount(animationBody.getRepeatCount() * 2 + 1);
        ((ObjectAnimator) animation).setRepeatMode(ValueAnimator.REVERSE);

    }
}
