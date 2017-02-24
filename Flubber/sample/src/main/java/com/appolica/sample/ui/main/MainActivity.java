package com.appolica.sample.ui.main;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
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

    public static final int SECOND = 1000;
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        getSupportFragmentManager().beginTransaction()
                .add(R.id.mainPanelContainer, new MainPanelFragment(), MainPanelFragment.TAG)
                .add(R.id.editorPanelContainer, new EditorFragment(), EditorFragment.TAG)
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
            showWithReveal(binding.editorPanelContainer);
        } else {
            binding.editorPanelContainer.setVisibility(View.VISIBLE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void showWithReveal(final View toShow) {
        final AnimatorSet animatorSet = new AnimatorSet();

        binding.revealView.setAlpha(1);
        toShow.setAlpha(1);
        binding.revealView.setVisibility(View.INVISIBLE);

        final float fabY = binding.floatingActionButton.getY();
        final float revealY = binding.revealView.getY();
        final float dy = Math.abs(fabY - revealY);

        int cx = (int) (binding.floatingActionButton.getX() + binding.floatingActionButton.getWidth() / 2);
        int cy = (int) (dy + binding.floatingActionButton.getHeight() / 2);

        float finalRadius = (float) Math.hypot(cx, cy);

        final Animator reveal = getReveal(binding.revealView, cx, cy, finalRadius, true);

        reveal.addListener(new SimpleAnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                toShow.setVisibility(View.VISIBLE);
            }
        });

        reveal.setDuration(500);

        final Animator fadeOut = Flubber.with(binding.revealView)
                .animation(Flubber.AnimationPreset.FADE_OUT)
                .interpolator(Flubber.Curve.BZR_EASE_OUT)
                .duration(300)
                .create();

        animatorSet.play(fadeOut).after(reveal);

        animatorSet.start();
        binding.revealView.setVisibility(View.VISIBLE);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void hideWithReveal(final View toHide) {
        final AnimatorSet animatorSet = new AnimatorSet();

        binding.revealView.setAlpha(1);
        binding.revealView.setVisibility(View.INVISIBLE);

        final float fabY = binding.floatingActionButton.getY();
        final float revealY = binding.revealView.getY();
        final float dy = Math.abs(fabY - revealY);

        int cx = (int) (binding.floatingActionButton.getX() + binding.floatingActionButton.getWidth() / 2);
        int cy = (int) (dy + binding.floatingActionButton.getHeight() / 2);

        float finalRadius = (float) Math.hypot(cx, cy);

        final Animator reveal = getReveal(binding.revealView, cx, cy, finalRadius, false);

        reveal.addListener(new SimpleAnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                toHide.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                binding.revealView.setVisibility(View.INVISIBLE);
            }
        });

        reveal.setDuration(500);

        final Animator fadeOut = Flubber.with(toHide)
                .animation(Flubber.AnimationPreset.FADE_OUT)
                .interpolator(Flubber.Curve.BZR_EASE_OUT)
                .duration(300)
                .create();

        animatorSet.play(reveal).after(fadeOut);

        animatorSet.start();
        binding.revealView.setVisibility(View.VISIBLE);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private Animator getReveal(View view, int cx, int cy, float radius, boolean isOpening) {
        if (isOpening) {
            return ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, radius);
        } else {
            return ViewAnimationUtils.createCircularReveal(view, cx, cy, radius, 0);
        }
    }


    @Override
    public void onBackPressed() {
        if (binding.editorPanelContainer.getVisibility() == View.VISIBLE) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                hideWithReveal(binding.editorPanelContainer);
                return;
            }
        }
        super.onBackPressed();

    }
}
