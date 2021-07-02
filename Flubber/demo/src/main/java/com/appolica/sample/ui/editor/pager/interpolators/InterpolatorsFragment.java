package com.appolica.sample.ui.editor.pager.interpolators;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;

import com.appolica.flubber.AnimationBody;
import com.appolica.flubber.Flubber;
import com.appolica.sample.ui.editor.pager.BaseRadioRVFragment;
import com.appolica.sample.ui.editor.pager.RadioElementModel;
import com.appolica.sample.ui.editor.pager.RadioRVAdapter;
import com.appolica.sample.utils.StringUtils;
import com.appolica.sample.utils.Utils;

import java.util.List;
import java.util.Map;

public class InterpolatorsFragment
        extends BaseRadioRVFragment
        implements RadioRVAdapter.OnElementSelectedListener {

    public static final String TAG = "InterpolatorsFragment";

    private Map<String, Flubber.Curve> interpolatorNamesMap =
            StringUtils.normalizedNameMapFor(Flubber.Curve.class);

    private OnInterpolatorSelectedListener selectedListener;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final RadioRVAdapter adapter = getAdapter();

        getAdapter().setSelectedListener(this);

        final AnimationBody animationBody = getAnimationBody();
        if (animationBody.getInterpolator() == null) {
            getAdapter().setSelected(0);
        } else {
            final Flubber.InterpolatorProvider interpolatorProvider = ((Flubber.Curve) animationBody.getInterpolator()).getProvider();
            final Flubber.Curve interpolator = Flubber.Curve.valueFor(interpolatorProvider);
            final RadioElementModel radioElementModel = Utils.convertEnumToModel(interpolator);

            final int index = adapter.getData().indexOf(radioElementModel);

            adapter.setSelected(index);
        }
    }

    @Override
    public List<RadioElementModel> getData() {
        return Utils.convertMapToData(interpolatorNamesMap);
    }

    @Override
    public void onElementSelected(RadioElementModel model) {
        if (selectedListener != null) {
            selectedListener.onInterpolatorSelected(interpolatorNamesMap.get(model.getName().get()));
        }
    }

    public void setSelectedListener(OnInterpolatorSelectedListener selectedListener) {
        this.selectedListener = selectedListener;
    }

}
