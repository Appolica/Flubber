package com.appolica.sample.ui.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.appolica.flubber.AnimationBody;
import com.appolica.flubber.Flubber;
import com.appolica.sample.R;
import com.appolica.sample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements MainActivityClickListener {

    public static final int SECOND = 1000;
    private RVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RVAdapter();
        binding.recyclerView.setAdapter(adapter);

        binding.setClickListener(this);
    }

    @Override
    public void onViewClick(View view) {
        final AnimationBody animationBody = Flubber.with(view)
                .animation(Flubber.AnimationPreset.SLIDE_UP)
                .duration(SECOND)
                .build();

        adapter.add(animationBody);

        animationBody
                .create()
                .start();
    }
}
