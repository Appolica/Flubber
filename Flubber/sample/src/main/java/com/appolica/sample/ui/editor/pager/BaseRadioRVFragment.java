package com.appolica.sample.ui.editor.pager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.appolica.flubber.AnimationBody;
import com.appolica.sample.ui.editor.AnimationBodyHolder;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRadioRVFragment
        extends BaseFragment<RadioRVAdapter>
        implements AnimationBodyHolder {

    private static final String BUNDLE_DATA = "data";
    private AnimationBody animationBody;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final List<RadioElementModel> data;
        if (savedInstanceState == null) {
            data = getData();
        } else {
            data = (List<RadioElementModel>) savedInstanceState.getSerializable(BUNDLE_DATA);
        }

        getAdapter().setData(data);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(BUNDLE_DATA, new ArrayList<>(getAdapter().getData()));
    }

    public abstract List<RadioElementModel> getData();

    @Override
    protected RadioRVAdapter getAdapterInstance() {
        return new RadioRVAdapter(getContext());
    }

    @Override
    public void setAnimationBody(AnimationBody animationBody) {
        this.animationBody = animationBody;
    }

    public AnimationBody getAnimationBody() {
        return animationBody;
    }
}
