package com.appolica.sample.ui.main;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;

import com.appolica.flubber.AnimationBody;
import com.appolica.flubber.Flubber;
import com.appolica.sample.R;
import com.appolica.sample.databinding.ActivityMainBinding;
import com.appolica.sample.ui.animation.FABRevealProvider;
import com.appolica.sample.ui.animation.RevealProvider;
import com.appolica.sample.ui.animation.SimpleAnimatorListener;
import com.appolica.sample.ui.editor.EditorFragment;

public class MainActivity
        extends AppCompatActivity
        implements AddClickListener,
        FlubberClickListener {

    private static final String TAG = "MainActivity";

    public static final int SECOND = 1000;
    public static final int DURATION_REVEAL = 350;
    public static final int DURATION_FADE = 200;
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.mainPanelContainer, new MainPanelFragment(), MainPanelFragment.TAG)
                .commitNow();

        binding.setAddClickListener(this);
        binding.setFlubberClickListener(this);
    }

    @Override
    public void onFlubberClick(View view) {

        final EditorFragment editorFragment =
                (EditorFragment) getSupportFragmentManager().findFragmentByTag(EditorFragment.TAG);
        if (editorFragment != null && editorFragment.isVisible()) {

            editorFragment.onFlubberClick(view);

        } else {
            Flubber.with()
                    .animation(Flubber.AnimationPreset.WOBBLE)
                    .duration(SECOND)
                    .autoStart(true)
                    .build()
                    .createFor(view);
        }
    }

    @Override
    public void onAddClick() {
        if (isEditorOpen()) {
            closeEditor();
        } else {
            openEditor();
        }
    }

    private void openEditor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Flubber.with()
                    .animation(FABRevealProvider.create(R.drawable.ic_done_white_24dp))
                    .duration(DURATION_REVEAL)
                    .autoStart(true)
                    .createFor(binding.floatingActionButton);
        }

        final EditorFragment editorFragment = new EditorFragment();
        final FragmentManager fragmentManager = getSupportFragmentManager();

        editorFragment.setAnimationBody(new AnimationBody());

        final FragmentTransaction transaction =
                fragmentManager.beginTransaction()
                        .replace(R.id.editorPanelContainer, editorFragment, EditorFragment.TAG);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

            final Animator showAnimation = getShowWithReveal(binding.editorPanelContainer);
            showAnimation.start();

        } else {
            binding.editorPanelContainer.setVisibility(View.VISIBLE);
            transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        }

        transaction.commitNow();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private Animator getShowWithReveal(final View toShow) {
        final AnimatorSet animatorSet = new AnimatorSet();

        toShow.setVisibility(View.INVISIBLE);
        toShow.setAlpha(1);

        final Animator reveal = Flubber.with()
                .animation(RevealProvider.create(binding.floatingActionButton, binding.revealView, true))
                .duration(DURATION_REVEAL)
                .createFor(toShow);

        final Animator fadeOut = Flubber.with()
                .animation(Flubber.AnimationPreset.FADE_OUT)
                .interpolator(Flubber.Curve.BZR_EASE_OUT)
                .duration(DURATION_FADE)
                .createFor(binding.revealView);

        animatorSet.play(fadeOut).after(reveal);

        return animatorSet;
    }

    private void closeEditor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Flubber.with()
                    .animation(new FABRevealProvider(R.drawable.ic_add_white_48dp))
                    .autoStart(true)
                    .duration(DURATION_REVEAL)
                    .createFor(binding.floatingActionButton);
        }

        final EditorFragment editorFragment = getFragment(EditorFragment.TAG);
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                .remove(editorFragment);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            final AnimatorSet hideAnimation = getHideWithReveal(binding.editorPanelContainer);

            hideAnimation.addListener(new SimpleAnimatorListener() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    transaction.commitNow();
                }
            });

            hideAnimation.start();
        } else {
            transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
            binding.editorPanelContainer.setVisibility(View.INVISIBLE);
            transaction.commitNow();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private AnimatorSet getHideWithReveal(final View toHide) {
        final AnimatorSet animatorSet = new AnimatorSet();

        binding.revealView.setVisibility(View.VISIBLE);
        final Animator reveal = getReveal(binding.revealView, false);
        final Animator fadeOut = getFadeIn(binding.revealView);

        reveal.setDuration(DURATION_REVEAL);

        fadeOut.addListener(new SimpleAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                toHide.setVisibility(View.INVISIBLE);
            }
        });

        animatorSet.addListener(new SimpleAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                binding.revealView.setVisibility(View.INVISIBLE);
            }
        });

        animatorSet.play(reveal).after(fadeOut);

        return animatorSet;
    }

    private Animator getFadeIn(View toHide) {
        return Flubber.with()
                .animation(Flubber.AnimationPreset.FADE_IN)
                .interpolator(Flubber.Curve.BZR_EASE_IN)
                .duration(DURATION_FADE)
                .createFor(toHide);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private Animator getReveal(final View view, boolean isOpening) {
        final float fabY = binding.floatingActionButton.getY();
        final float revealY = binding.revealView.getY();
        final float dy = Math.abs(fabY - revealY);

        int cx = (int) (binding.floatingActionButton.getX() + binding.floatingActionButton.getWidth() / 2);
        int cy = (int) (dy + binding.floatingActionButton.getHeight() / 2);

        float radius = (float) Math.hypot(cx, cy);

        final Animator animator;
        if (isOpening) {
            animator = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, radius);
        } else {
            animator = ViewAnimationUtils.createCircularReveal(view, cx, cy, radius, 0);
        }

        animator.addListener(new SimpleAnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                view.setAlpha(1);
                view.setVisibility(View.VISIBLE);
            }
        });

        return animator;
    }

    private <T extends Fragment> T getFragment(String tag) {
        return (T) getSupportFragmentManager().findFragmentByTag(tag);
    }

    @Override
    public void onBackPressed() {
        if (isEditorOpen()) {
            closeEditor();
            return;
        }

        super.onBackPressed();
    }

    private boolean isEditorOpen() {
        return binding.editorPanelContainer.getVisibility() == View.VISIBLE;
    }
}
