package com.appolica.sample.ui.main;

import android.databinding.ObservableField;

public class AnimationsListItemModel {
    private ObservableField<String> animation = new ObservableField<>();

    private ObservableField<String> interpolator = new ObservableField<>();

    public ObservableField<String> getAnimation() {
        return animation;
    }

    public void setAnimation(ObservableField<String> animation) {
        this.animation = animation;
    }

    public ObservableField<String> getInterpolator() {
        return interpolator;
    }

    public void setInterpolator(ObservableField<String> interpolator) {
        this.interpolator = interpolator;
    }
}
