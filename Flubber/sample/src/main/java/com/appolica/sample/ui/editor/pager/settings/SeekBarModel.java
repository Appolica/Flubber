package com.appolica.sample.ui.editor.pager.settings;

import android.databinding.ObservableField;
import android.databinding.ObservableFloat;

public class SeekBarModel {
    private ObservableField<String> name = new ObservableField<>();
    private ObservableFloat value = new ObservableFloat();

    private float minValue;
    private float maxValue;

    public static SeekBarModel create(String name, float value, float minValue, float maxValue) {
        final SeekBarModel model = new SeekBarModel();

        model.getName().set(name);
        model.getValue().set(value);
        model.setMinValue(minValue);
        model.setMaxValue(maxValue);

        return model;
    }

    public ObservableField<String> getName() {
        return name;
    }

    public void setName(ObservableField<String> name) {
        this.name = name;
    }

    public ObservableFloat getValue() {
        return value;
    }

    public void setValue(ObservableFloat value) {
        this.value = value;
    }

    public float getMinValue() {
        return minValue;
    }

    public void setMinValue(float minValue) {
        this.minValue = minValue;
    }

    public float getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    @Override
    public String toString() {
        return String.format("%s: Min = %f | Max = %f | Value = %f", name.get(), minValue, maxValue, value.get());
    }
}
