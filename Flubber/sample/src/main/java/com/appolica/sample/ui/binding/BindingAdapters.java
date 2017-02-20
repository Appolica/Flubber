package com.appolica.sample.ui.binding;

import android.databinding.BindingAdapter;

import java.util.Map;

import cn.carbswang.android.numberpickerview.library.NumberPickerView;

public class BindingAdapters {

    @BindingAdapter({"app:values"})
    public static void setDisplayedValues(NumberPickerView numberPicker, Map<String, ? extends Enum> namesMap) {
        final String[] names = new String[namesMap.size()];
        namesMap.keySet().toArray(names);
        numberPicker.setDisplayedValues(names);

        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(namesMap.size() - 1);
    }

}
