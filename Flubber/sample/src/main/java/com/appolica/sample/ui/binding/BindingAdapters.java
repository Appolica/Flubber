package com.appolica.sample.ui.binding;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

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

    @BindingAdapter({"app:checked", "app:model"})
    public static<T> void setChecked(RadioButton radioButton, final ObservableField<T> checked, final T model) {

        if (checked == null) {
            return;
        }

        radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!checked.get().equals(model) && isChecked) {
                    checked.set(model);
                }
            }
        });

        final T checkedModel = checked.get();
        final boolean shouldBeChecked = checkedModel.equals(model);

        if (shouldBeChecked != radioButton.isChecked()) {
            radioButton.setChecked(shouldBeChecked);
        }
    }

    @BindingAdapter("app:propagateClick")
    public static void propagateClick(final ViewGroup container, boolean propagate) {
        if (propagate) {
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < container.getChildCount(); i++) {
                        container.getChildAt(i).performClick();
                    }
                }
            });
        }
    }

}
