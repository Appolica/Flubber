package com.appolica.sample.ui.editor;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appolica.flubber.AnimationBody;
import com.appolica.flubber.Flubber;
import com.appolica.sample.R;
import com.appolica.sample.databinding.EditorPanelBinding;
import com.appolica.sample.ui.utils.StringUtils;

import java.util.Map;


public class EditorPanelFragment
        extends Fragment
        implements AnimationsValueChangedListener.OnAnimationPickListener,
        InterpolatorsValueChangedListener.OnInterpolatorPickListener,
        EditorPanelClickListener {

    private static final String TAG = "EditorPanelFragment";

    private EditorPanelBinding binding;

    private Map<String, Flubber.AnimationPreset> animationNamesMap = StringUtils.normalizedNameMapFor(Flubber.AnimationPreset.class);
    private Map<String, Flubber.Curve> interpolatorNamesMap = StringUtils.normalizedNameMapFor(Flubber.Curve.class);

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

        binding.setAnimationNames(animationNamesMap);
        binding.setInterpolatorNames(interpolatorNamesMap);

        binding.executePendingBindings();

        animationBody = new AnimationBody();


        binding.animationsPicker.setOnValueChangedListener(new AnimationsValueChangedListener(this));
        binding.interpolatorPicker.setOnValueChangedListener(new InterpolatorsValueChangedListener(this));

        binding.setClickListener(this);
    }


    @Override
    public void onAnimationPicked(String name) {
        Log.d(TAG, "onAnimationPicked: " + name);
        animationBody.setAnimation(animationNamesMap.get(name));
    }

    @Override
    public void onInterpolatorPicked(String name) {
        Log.d(TAG, "onInterpolatorPicked: " + name);
        animationBody.setInterpolator(interpolatorNamesMap.get(name));
    }

    @Override
    public void onCancelClick() {

    }

    @Override
    public void onDoneClick() {

    }
}
