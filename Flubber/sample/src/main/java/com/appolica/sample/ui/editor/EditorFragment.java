package com.appolica.sample.ui.editor;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appolica.flubber.AnimationBody;
import com.appolica.flubber.Flubber;
import com.appolica.sample.R;
import com.appolica.sample.databinding.FragmentEditorPanelBinding;
import com.appolica.sample.ui.editor.pager.ListenerProvider;
import com.appolica.sample.ui.editor.pager.animations.OnAnimationSelectedListener;
import com.appolica.sample.ui.editor.pager.interpolators.OnInterpolatorSelectedListener;


public class EditorFragment extends Fragment
        implements ListenerProvider,
        OnAnimationSelectedListener,
        OnInterpolatorSelectedListener {

    public static final String TAG = "EditorFragment";

    private FragmentEditorPanelBinding binding;

    private AnimationBody animationBody;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_editor_panel, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        animationBody = new AnimationBody();

        final EditViewPagerAdapter adapter =
                new EditViewPagerAdapter(getChildFragmentManager(), getContext());

        adapter.setListenerProvider(this);

        binding.tabLayoutEdit.setupWithViewPager(binding.viewPagerEdit);

        binding.viewPagerEdit.setOffscreenPageLimit(4);
        binding.viewPagerEdit.setAdapter(adapter);
    }

    @Override
    public OnAnimationSelectedListener getAnimationSelectedListener() {
        return this;
    }

    @Override
    public OnInterpolatorSelectedListener getInterpolatorSelectedListener() {
        return this;
    }

    @Override
    public void onAnimationSelected(Flubber.AnimationProvider animationProvider) {
        animationBody.setAnimation(animationProvider);
    }

    @Override
    public void onInterpolatorSelected(Flubber.Curve interpolator) {
        animationBody.setInterpolator(interpolator);
    }
}