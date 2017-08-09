package com.appolica.sample.ui.editor.pager.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.appolica.sample.ui.animation.CustomAnimationBody;
import com.appolica.sample.ui.editor.AnimationBodyHolder;
import com.appolica.sample.ui.editor.pager.BaseFragment;

public class SettingsFragment
        extends BaseFragment<SettingsRVAdapter>
        implements SettingsRVAdapter.OnModelChangedCallback,
        AnimationBodyHolder {

    public static final String TAG = "SettingsFragment";

    private OnFieldChangedListener fieldChangedListener;
    private CustomAnimationBody animationBody;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getAdapter().setAnimationBody(animationBody);
        getAdapter().setModelChangedCallback(this);
    }

    @Override
    protected SettingsRVAdapter getAdapterInstance() {
        return new SettingsRVAdapter(getContext());
    }

    @Override
    public void onModelChanged(SeekBarModel model) {
        if (fieldChangedListener != null) {
            fieldChangedListener.onPropertyChanged(model);
        }
    }

    public void setFieldChangedListener(OnFieldChangedListener fieldChangedListener) {
        this.fieldChangedListener = fieldChangedListener;
    }

    @Override
    public void setAnimationBody(CustomAnimationBody animationBody) {
        this.animationBody = animationBody;
    }
}
