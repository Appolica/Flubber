package com.appolica.sample.ui.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.appolica.flubber.AnimationBody;
import com.appolica.flubber.Flubber;
import com.appolica.sample.R;
import com.appolica.sample.databinding.ActivityMainBinding;
import com.appolica.sample.ui.editor.EditorPanelFragment;

public class MainActivity extends AppCompatActivity implements MainActivityClickListener {

    public static final int SECOND = 1000;
    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, new MainPanelFragment(), MainPanelFragment.TAG)
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
        final FragmentManager fragmentManager = getSupportFragmentManager();
        final Fragment mainPanel = fragmentManager.findFragmentByTag(MainPanelFragment.TAG);

        fragmentManager.beginTransaction()
                .hide(mainPanel)
                .add(R.id.fragmentContainer, new EditorPanelFragment())
                .commitNow();

        binding.floatingActionButton.hide();
    }
}
