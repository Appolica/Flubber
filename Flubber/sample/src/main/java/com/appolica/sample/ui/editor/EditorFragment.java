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
import com.appolica.sample.ui.editor.pager.AnimationBodyProvider;
import com.appolica.sample.ui.editor.pager.ListenerProvider;
import com.appolica.sample.ui.editor.pager.animations.OnAnimationSelectedListener;
import com.appolica.sample.ui.editor.pager.interpolators.OnInterpolatorSelectedListener;
import com.appolica.sample.ui.editor.pager.settings.AnimationBodyModelUtil;
import com.appolica.sample.ui.editor.pager.settings.OnFieldChangedListener;
import com.appolica.sample.ui.editor.pager.settings.SeekBarModel;
import com.appolica.sample.ui.main.FlubberClickListener;


public class EditorFragment extends Fragment
        implements ListenerProvider,
        OnAnimationSelectedListener,
        OnInterpolatorSelectedListener,
        OnFieldChangedListener,
        AnimationBodyProvider,
        FlubberClickListener {

    public static final String TAG = "EditorFragment";
    public static final String BUNDLE_ANIM_BODY = "SettingsArguments";

    private FragmentEditorPanelBinding binding;

    private AnimationBody animationBody;

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

        final EditViewPagerAdapter adapter =
                new EditViewPagerAdapter(getChildFragmentManager(), getContext());

        adapter.setListenerProvider(this);
        adapter.setAnimationBodyProvider(this);

        animationBody = createAnimationBody(getArguments());

        binding.tabLayoutEdit.setupWithViewPager(binding.viewPagerEdit);

        binding.viewPagerEdit.setOffscreenPageLimit(2);
        binding.viewPagerEdit.setAdapter(adapter);
    }

    private AnimationBody createAnimationBody(Bundle arguments) {
        if (arguments != null && arguments.getSerializable(BUNDLE_ANIM_BODY) != null) {
            return (AnimationBody) arguments.getSerializable(BUNDLE_ANIM_BODY);
        } else {
            throw new IllegalStateException("AnimationBody must be provided as an argument with key " + BUNDLE_ANIM_BODY);
        }
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
    public void onFieldChanged(SeekBarModel model) {
        AnimationBodyModelUtil.initFieldFromModel(model, animationBody);
    }

    @Override
    public AnimationBody getAnimationBody() {
        return animationBody;
    }
}
