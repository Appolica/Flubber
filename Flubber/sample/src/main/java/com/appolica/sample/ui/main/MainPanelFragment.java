package com.appolica.sample.ui.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appolica.flubber.AnimationBody;
import com.appolica.sample.R;
import com.appolica.sample.databinding.MainPanelBinding;
import com.appolica.sample.utils.Utils;

import java.util.ArrayList;
import java.util.List;


public class MainPanelFragment extends Fragment implements MainSwipeDecorator.ItemDismissCallback {

    public static final String TAG = "MainPanelFragment";
    public static final String BUNDLE_ANIMATION_BODIES = "BundleAnimationBodies";
    private MainPanelBinding binding;
    private MainRVAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_panel, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new MainRVAdapter();

        setupRecyclerView();

        if (savedInstanceState != null) {
            restoreAnimationBodies(savedInstanceState);
        }
    }

    private void setupRecyclerView() {
        binding.recyclerViewAnimations.setHasFixedSize(true);
        binding.recyclerViewAnimations.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerViewAnimations.setAdapter(adapter);

        final MainSwipeDecorator itemDecoration =
                new MainSwipeDecorator(this);

        itemDecoration.attachToRecyclerView(binding.recyclerViewAnimations);
    }

    private void restoreAnimationBodies(@NonNull Bundle savedInstanceState) {
        final ArrayList<Bundle> animationBundles =
                savedInstanceState.getParcelableArrayList(BUNDLE_ANIMATION_BODIES);

        for (Bundle animationBundle : animationBundles) {
            addAnimation(Utils.restoreAnimationBody(animationBundle));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        final ArrayList<Bundle> animationBundles = new ArrayList<>();

        for (AnimationBody animationBody : getAnimations()) {
            final Bundle animationBundle = Utils.createAnimationBodyBundle(animationBody);
            animationBundles.add(animationBundle);
        }

        outState.putParcelableArrayList(BUNDLE_ANIMATION_BODIES, animationBundles);
    }

    public void addAnimation(AnimationBody animationBody) {
        adapter.add(animationBody);
    }

    public List<AnimationBody> getAnimations() {
        return adapter.getAnimations();
    }

    @Override
    public void onItemDismissed(final int position) {
        final AnimationBody toRemove = adapter.getAnimations().get(position - 1);
        adapter.remove(toRemove);

        Snackbar.make(binding.getRoot(), R.string.snackAnimationRemoved, Snackbar.LENGTH_LONG)
                .setAction(R.string.undo, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapter.add(position - 1, toRemove);
                    }
                })
                .show();
    }
}
