package com.appolica.sample.ui.animation;

import android.animation.Animator;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewAnimationUtils;

import com.appolica.flubber.AnimationBody;
import com.appolica.flubber.Flubber;
import com.appolica.flubber.listener.SimpleAnimatorListener;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class RevealProvider implements Flubber.AnimationProvider {

    private final boolean isOpening;
    private View anchor;
    private View revealView;

    public static RevealProvider create(View anchor, View revealView, boolean isOpening) {
        return new RevealProvider(anchor, revealView, isOpening);
    }

    public RevealProvider(View anchor, View revealView, boolean isOpening) {
        this.anchor = anchor;
        this.revealView = revealView;
        this.isOpening = isOpening;
    }

    @Override
    public Animator createAnimationFor(AnimationBody animationBody, View view) {
        return getReveal(view);
    }

    private Animator getReveal(final View view) {
        final float anchorY = anchor.getY();
        final float revealY = revealView.getY();
        final float dy = Math.abs(anchorY - revealY);

        int cx = (int) (anchor.getX() + anchor.getWidth() / 2);
        int cy = (int) (dy + anchor.getHeight() / 2);

        float radius = (float) Math.hypot(cx, cy);

        final Animator animator;
        final Animator.AnimatorListener listener;
        if (isOpening) {
            animator = ViewAnimationUtils.createCircularReveal(revealView, cx, cy, 0, radius);
            listener = SimpleAnimatorListener.forEnd(animation -> view.setVisibility(View.VISIBLE));
        } else {
            animator = ViewAnimationUtils.createCircularReveal(revealView, cx, cy, radius, 0);
            listener = SimpleAnimatorListener.forEnd(animation -> revealView.setVisibility(View.INVISIBLE));
        }

        animator.addListener(listener);

        animator.addListener(SimpleAnimatorListener.forStart(animation -> {
            revealView.setAlpha(1);
            revealView.setVisibility(View.VISIBLE);
        }));

        return animator;
    }
}
