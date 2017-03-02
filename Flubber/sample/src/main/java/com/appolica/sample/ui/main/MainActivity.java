package com.appolica.sample.ui.main;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;

import com.appolica.flubber.AnimationBody;
import com.appolica.flubber.Flubber;
import com.appolica.sample.R;
import com.appolica.sample.databinding.ActivityMainBinding;
import com.appolica.sample.ui.animation.SimpleAnimatorListener;
import com.appolica.sample.ui.editor.EditorFragment;

public class MainActivity extends AppCompatActivity implements MainActivityClickListener {

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

        binding.setClickListener(this);
    }

    @Override
    public void onViewClick(View view) {
        final AnimationBody animationBody = Flubber.with(view)
                .animation(Flubber.AnimationPreset.WOBBLE)
                .duration(SECOND)
                .build();

        animationBody
                .create()
                .start();
    }

    @Override
    public void onAddClick() {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            openEditor();
        } else {
            binding.editorPanelContainer.setVisibility(View.VISIBLE);
        }
    }

    private void openEditor() {
        final EditorFragment editorFragment = new EditorFragment();
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction()
                .add(R.id.editorPanelContainer, editorFragment, EditorFragment.TAG);

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

        final Animator reveal = getReveal(binding.revealView, true);
        final Animator fadeOut = getFadeOut(binding.revealView);

        reveal.setDuration(DURATION_REVEAL);
        reveal.addListener(new SimpleAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                toShow.setVisibility(View.VISIBLE);
            }
        });

        animatorSet.play(fadeOut).after(reveal);

        return animatorSet;
    }

    private void closeEditor() {
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

    private Animator getFadeOut(View toHide) {
        return Flubber.with(toHide)
                .animation(Flubber.AnimationPreset.FADE_OUT)
                .interpolator(Flubber.Curve.BZR_EASE_OUT)
                .duration(DURATION_FADE)
                .create();
    }

    private Animator getFadeIn(View toHide) {
        return Flubber.with(toHide)
                .animation(Flubber.AnimationPreset.FADE_IN)
                .interpolator(Flubber.Curve.BZR_EASE_IN)
                .duration(DURATION_FADE)
                .create();
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
        if (binding.editorPanelContainer.getVisibility() == View.VISIBLE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                closeEditor();
                return;
            }
        }

        super.onBackPressed();
    }
}
