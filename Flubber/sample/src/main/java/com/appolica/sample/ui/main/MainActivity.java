package com.appolica.sample.ui.main;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
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
        final AnimatorSet animatorSet = new AnimatorSet();

        binding.revealView.setAlpha(1);
        binding.revealView.setVisibility(View.INVISIBLE);

        final float fabY = binding.floatingActionButton.getY();
        final float revealY = binding.revealView.getY();
        final float dy = Math.abs(fabY - revealY);

        int cx = (int) (binding.floatingActionButton.getX() + binding.floatingActionButton.getWidth() / 2);
        int cy = (int) (dy + binding.floatingActionButton.getHeight() / 2);

        float finalRadius = (float) Math.hypot(cx, cy);

        Animator anim = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

            anim = ViewAnimationUtils.createCircularReveal(binding.revealView, cx, cy, 0, finalRadius);
            binding.revealView.setVisibility(View.VISIBLE);

            anim.addListener(new SimpleAnimatorListener() {
                @Override
                public void onAnimationEnd(Animator animation) {

                    binding.editorPanelContainer.setVisibility(View.VISIBLE);
                }
            });

            anim.setDuration(500);

            final Animator fadeIn = Flubber.with(binding.revealView)
                    .animation(Flubber.AnimationPreset.FADE_OUT)
                    .interpolator(Flubber.Curve.BZR_EASE_OUT)
                    .duration(300)
                    .create();

            animatorSet.play(fadeIn).after(anim);

            animatorSet.start();
        } else {
            binding.editorPanelContainer.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
