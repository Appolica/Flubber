package com.appolica.sample.ui.editor.pager.settings;

import android.databinding.ObservableField;

public class SeekBarModel<ValueT extends Number> {
    private ObservableField<String> name = new ObservableField<>();
    private ObservableField<ValueT> value = new ObservableField<>();

    private ValueT minValue;
    private ValueT maxValue;

    public static<ValueT extends Number> SeekBarModel create(String name, ValueT value, ValueT minValue, ValueT maxValue) {
        final SeekBarModel<ValueT> model = new SeekBarModel<>();

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

    public ObservableField<ValueT> getValue() {
        return value;
    }

    public void setValue(ObservableField<ValueT> value) {
        this.value = value;
    }

    public ValueT getMinValue() {
        return minValue;
    }

    public void setMinValue(ValueT minValue) {
        this.minValue = minValue;
    }

    public ValueT getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(ValueT maxValue) {
        this.maxValue = maxValue;
    }
}
