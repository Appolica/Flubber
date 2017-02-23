package com.appolica.sample.ui.editor.pager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRadioRVFragment extends BaseFragment<RadioRVAdapter> {

    private static final String BUNDLE_DATA = "data";

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
}