package com.appolica.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.appolica.flubber.Flubber;
import com.facebook.rebound.SpringSystem;

public class MainActivity extends AppCompatActivity {

    public static final int SECOND = 1000;
    private SpringSystem springSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View view = findViewById(R.id.text);
//        springSystem = SpringSystem.create();

//        final Spring spring = springSystem.createSpring();
//        spring.setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(50, 4));
////        spring.setRestDisplacementThreshold(1.2);

//        final ReboundPropertyValueHolder reboundPVH =
//                ReboundPropertyValueHolder.ofFloat(View.TRANSLATION_Y, -DimensionUtils.dp2px(300), 0);
//
//        final ReboundAnimator animator = ReboundAnimator.ofPropertyValueHolder(spring, view, reboundPVH);
//        animator.setLoggingTime(true);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Flubber.with(view)
                        .animation(Flubber.AnimationPreset.SLIDE_LEFT)
                        .duration(500)
                        .damping(0.55f)
//                        .velocity(1)
                        .create()
                        .start();

//                animator.start();

            }
        });

    }
}
