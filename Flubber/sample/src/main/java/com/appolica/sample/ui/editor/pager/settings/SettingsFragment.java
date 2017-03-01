package com.appolica.sample.ui.editor.pager.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.appolica.flubber.AnimationBody;
import com.appolica.sample.ui.editor.pager.BaseFragment;

public class SettingsFragment
        extends BaseFragment<SettingsRVAdapter>
        implements SettingsRVAdapter.OnModelChangedCallback {

    public static final String TAG = "SettingsFragment";
    public static final String BUNDLE_ANIM_BODY = "SettingsArguments";
    private AnimationBody animationBody;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        animationBody = (AnimationBody) getArguments().getSerializable(SettingsFragment.BUNDLE_ANIM_BODY);

        getAdapter().setAnimationBody(animationBody);
        getAdapter().setModelChangedCallback(this);
    }

    @Override
    protected SettingsRVAdapter getAdapterInstance() {
        return new SettingsRVAdapter(getContext());
    }

    @Override
    public void onModelChanged(SeekBarModel model) {
        AnimationBodyModelUtil.initFieldFromModel(model, animationBody);
        Log.d(TAG, "onModelChanged: " + model);
        Log.d(TAG, "AnimationBody: duration: " + animationBody.getDuration());
        Log.d(TAG, "AnimationBody: force: " + animationBody.getForce());
    }
}
