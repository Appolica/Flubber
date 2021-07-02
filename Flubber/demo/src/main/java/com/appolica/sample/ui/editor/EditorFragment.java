package com.appolica.sample.ui.editor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.annimon.stream.Stream;
import com.appolica.flubber.Flubber;
import com.appolica.sample.R;
import com.appolica.sample.databinding.FragmentEditorPanelBinding;
import com.appolica.sample.ui.animation.CustomAnimationBody;
import com.appolica.sample.ui.editor.pager.AnimationBodyProvider;
import com.appolica.sample.ui.editor.pager.ListenerProvider;
import com.appolica.sample.ui.editor.pager.animations.OnAnimationSelectedListener;
import com.appolica.sample.ui.editor.pager.interpolators.OnInterpolatorSelectedListener;
import com.appolica.sample.ui.editor.pager.settings.AnimationBodyModelUtil;
import com.appolica.sample.ui.editor.pager.settings.OnFieldChangedListener;
import com.appolica.sample.ui.editor.pager.settings.SeekBarModel;
import com.appolica.sample.ui.main.FlubberClickListener;
import com.appolica.sample.utils.Utils;

import java.util.ArrayList;


public class EditorFragment extends Fragment
        implements ListenerProvider,
        OnAnimationSelectedListener,
        OnInterpolatorSelectedListener,
        OnFieldChangedListener,
        AnimationBodyProvider,
        FlubberClickListener,
        AnimationBodyHolder {

    public static final String TAG = "EditorFragment";

    public static final String PAGER_FRAGMENT_TAGS = "PagerFragmentTags";
    private static final String ANIMATION_BODY_BUNDLE = "AnimationBodyBundle";

    private FragmentEditorPanelBinding binding;

    private CustomAnimationBody animationBody;
    private EditViewPagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_editor_panel, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new EditViewPagerAdapter(getChildFragmentManager(), getContext());
        adapter.setListenerProvider(this);
        adapter.setAnimationBodyProvider(this);

        if (savedInstanceState != null) {
            animationBody = Utils.restoreAnimationBody(savedInstanceState.getBundle(ANIMATION_BODY_BUNDLE));
            propagateRestoredAnimationBody(savedInstanceState);
        }

        binding.viewPagerEdit.setAdapter(adapter);
        binding.tabLayoutEdit.setupWithViewPager(binding.viewPagerEdit);
        binding.viewPagerEdit.setOffscreenPageLimit(2);
    }

    private void propagateRestoredAnimationBody(@NonNull Bundle savedInstanceState) {
        final ArrayList<String> pagerFragmentTags =
                (ArrayList<String>) savedInstanceState.getSerializable(PAGER_FRAGMENT_TAGS);

        if (pagerFragmentTags != null) {
            Stream.of(pagerFragmentTags)
                    .forEach(tag -> {
                        final AnimationBodyHolder bodyHolder =
                                (AnimationBodyHolder) getChildFragmentManager().findFragmentByTag(tag);
                        bodyHolder.setAnimationBody(animationBody);
                    });
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        final Bundle animationBodyBundle = Utils.createAnimationBodyBundle(animationBody);

        outState.putBundle(ANIMATION_BODY_BUNDLE, animationBodyBundle);
        outState.putSerializable(PAGER_FRAGMENT_TAGS, adapter.getRegisteredFragmentTags());
    }

    @Override
    public void onFlubberClick(View view) {
        animationBody.createFor(view).start();
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
    public OnFieldChangedListener getFieldChangedListener() {
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

    @Override
    public void onPropertyChanged(SeekBarModel model) {
        AnimationBodyModelUtil.initFieldFromModel(animationBody, model);
    }

    @Override
    public CustomAnimationBody getAnimationBody() {
        return animationBody;
    }

    @Override
    public void setAnimationBody(CustomAnimationBody animationBody) {
        this.animationBody = animationBody;
    }
}
