package com.appolica.sample.ui.main;

import android.databinding.ObservableField;

public class AnimationsListItemModel {
    private ObservableField<String> name = new ObservableField<>();

    public ObservableField<String> getName() {
        return name;
    }

    public void setName(ObservableField<String> name) {
        this.name = name;
    }
}
