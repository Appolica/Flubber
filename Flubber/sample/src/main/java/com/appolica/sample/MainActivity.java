package com.appolica.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.appolica.flubber.Flubber;

public class MainActivity extends AppCompatActivity {

    public static final int SECOND = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View view = findViewById(R.id.text);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Flubber.with(view)
                        .animation(Flubber.AnimationPreset.ZOOM_IN)
                        .interpolator(Flubber.Curve.SPRING)
                        .duration(SECOND)
                        .create()
                        .start();
            }
        });

    }
}
