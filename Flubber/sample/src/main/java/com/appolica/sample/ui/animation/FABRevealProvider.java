package com.appolica.sample.ui.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.ViewAnimationUtils;

import com.appolica.flubber.AnimationBody;
import com.appolica.flubber.Flubber;
import com.appolica.flubber.listener.SimpleAnimatorListener;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class FABRevealProvider implements Flubber.AnimationProvider {

    private int newIconResId;

    public static FABRevealProvider create(int iconResId) {
        return new FABRevealProvider(iconResId);
    }

    public FABRevealProvider(int newIconResId) {
        this.newIconResId = newIconResId;
    }

    @Override
    public Animator createAnimationFor(AnimationBody animationBody, final View view) {
        final AnimatorSet animatorSet = new AnimatorSet();

        final Animator animationClose = getCloseReveal(view);
        final Animator animationOpen = getOpenReveal(view);

        animationClose.addListener(new SimpleAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                ((FloatingActionButton) view).setImageResource(newIconResId);
            }
        });

        animatorSet.play(animationOpen).after(animationClose);

        return animatorSet;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private Animator getCloseReveal(final View view) {
        int cx = view.getWidth() / 2;
        int cy = view.getHeight() / 2;

        float radius = (float) Math.hypot(cx, cy);

        final Animator animator = ViewAnimationUtils.createCircularReveal(view, cx, cy, radius, 0);

        return animator;
    }

    private Animator getOpenReveal(final View view) {
        int cx = view.getWidth() / 2;
        int cy = view.getHeight() / 2;

        float radius = (float) Math.hypot(cx, cy);

        final Animator animator = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, radius);

        return animator;
    }
}
