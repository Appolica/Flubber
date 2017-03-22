package com.appolica.sample.ui.main.fragment;

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

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.appolica.sample.R;
import com.appolica.sample.databinding.MainPanelBinding;
import com.appolica.sample.ui.animation.CustomAnimationBody;
import com.appolica.sample.utils.Utils;

import java.util.ArrayList;
import java.util.List;


public class MainPanelFragment extends Fragment
        implements MainSwipeDecorator.ItemDismissCallback,
        MainRVAdapter.OnItemClickListener {

    public static final String TAG = "MainPanelFragment";
    public static final String BUNDLE_ANIMATION_BODIES = "BundleAnimationBodies";
    private MainPanelBinding binding;
    private MainRVAdapter adapter;

    private AnimationClickListener clickListener;

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
        adapter.setClickListener(this);
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

        Stream.of(animationBundles)
                .forEach(animationBundle -> addAnimation(Utils.restoreAnimationBody(animationBundle)));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        final ArrayList<Bundle> animationBundles =
                Stream.of(adapter.getData())
                        .map(Utils::createAnimationBodyBundle)
                        .collect(Collectors.toCollection(ArrayList::new));

        outState.putParcelableArrayList(BUNDLE_ANIMATION_BODIES, animationBundles);
    }

    public void addAnimation(CustomAnimationBody animationBody) {
        adapter.add(animationBody);
    }

    public List<CustomAnimationBody> getAnimations() {
        return Stream.of(adapter.getData())
                .map(animationBody -> animationBody)
                .collect(Collectors.toList());
    }

    @Override
    public void onItemDismissed(final int position) {
        final CustomAnimationBody toRemove = adapter.getData().get(position - 1);
        adapter.remove(toRemove);

        Snackbar.make(binding.getRoot(), R.string.snackAnimationRemoved, Snackbar.LENGTH_LONG)
                .setAction(R.string.undo, v -> adapter.add(position - 1, toRemove))
                .show();
    }

    @Override
    public void onItemClicked(CustomAnimationBody animationBody) {
        if (clickListener != null) {
            clickListener.onAnimationClick(animationBody);
        }
    }

    public void setClickListener(AnimationClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface AnimationClickListener {
        void onAnimationClick(CustomAnimationBody animationBody);
    }
}
