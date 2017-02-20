package com.appolica.sample.ui.editor;


import cn.carbswang.android.numberpickerview.library.NumberPickerView;

public class AnimationsValueChangedListener implements NumberPickerView.OnValueChangeListener {

    private OnAnimationPickListener listener;

    public AnimationsValueChangedListener(OnAnimationPickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onValueChange(NumberPickerView picker, int oldVal, int newVal) {
        listener.onAnimationPicked(picker.getDisplayedValues()[newVal]);
    }


    public interface OnAnimationPickListener {
        void onAnimationPicked(String name);
    }
}
