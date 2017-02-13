package com.appolica.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.appolica.flubber.animation.ReboundAnimator;
import com.appolica.flubber.animation.ReboundPropertyValueHolder;
import com.appolica.flubber.utils.DimensionUtils;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringSystem;

public class MainActivity extends AppCompatActivity {

    public static final int SECOND = 1000;
    private SpringSystem springSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View view = findViewById(R.id.text);
        springSystem = SpringSystem.create();

        final Spring spring = springSystem.createSpring();

        spring.getSpringConfig().friction = 5;
        spring.getSpringConfig().tension = 77;

        final ReboundPropertyValueHolder reboundPVH =
                ReboundPropertyValueHolder.ofFloat(View.TRANSLATION_Y, -DimensionUtils.dp2px(300), 0);

        final ReboundAnimator animator = ReboundAnimator.ofPropertyValueHolder(spring, view, reboundPVH);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Flubber.with(view)
//                        .animation(Flubber.AnimationPreset.WOBBLE)
//                        .interpolator(Flubber.Curve.EASE_IN)
//                        .duration(SECOND)
//                        .create()
//                        .start();

                animator.start();

            }
        });

    }
}
