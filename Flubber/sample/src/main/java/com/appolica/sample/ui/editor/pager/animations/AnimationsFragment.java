package com.appolica.sample.ui.editor.pager.animations;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.appolica.flubber.Flubber;
import com.appolica.sample.ui.editor.pager.BaseRadioRVFragment;
import com.appolica.sample.ui.editor.pager.RadioElementModel;
import com.appolica.sample.ui.editor.pager.RadioRVAdapter;
import com.appolica.sample.utils.StringUtils;
import com.appolica.sample.utils.Utils;

import java.util.List;
import java.util.Map;

public class AnimationsFragment
        extends BaseRadioRVFragment
        implements RadioRVAdapter.OnElementSelectedListener {

    public static final String TAG = "AnimationsFragment";

    private Map<String, Flubber.AnimationPreset> animationNamesMap =
            StringUtils.normalizedNameMapFor(Flubber.AnimationPreset.class);

    private OnAnimationSelectedListener selectedListener;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getAdapter().setSelectedListener(this);
    }

    @Override
    public List<RadioElementModel> getData() {
        return Utils.convertMapToData(animationNamesMap);
    }


    @Override
    public void onElementSelected(RadioElementModel model) {
        if (selectedListener != null) {
            selectedListener.onAnimationSelected(animationNamesMap.get(model.getName().get()));
        }
    }

    public void setSelectedListener(OnAnimationSelectedListener selectedListener) {
        this.selectedListener = selectedListener;
    }
}
