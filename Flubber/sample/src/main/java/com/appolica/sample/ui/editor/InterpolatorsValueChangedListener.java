package com.appolica.sample.ui.editor;

import cn.carbswang.android.numberpickerview.library.NumberPickerView;

public class InterpolatorsValueChangedListener implements NumberPickerView.OnValueChangeListener {

    private OnInterpolatorPickListener listener;

    public InterpolatorsValueChangedListener(OnInterpolatorPickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onValueChange(NumberPickerView picker, int oldVal, int newVal) {
        listener.onInterpolatorPicked(picker.getDisplayedValues()[newVal]);
    }

    public interface OnInterpolatorPickListener {
        void onInterpolatorPicked(String name);
    }

}
