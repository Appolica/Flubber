package com.appolica.sample.ui.binding;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.SeekBar;

import com.appolica.sample.ui.editor.pager.settings.NumericTransformer;
import com.appolica.sample.ui.editor.pager.settings.SeekBarModel;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

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
    public static <T> void setChecked(RadioButton radioButton, final ObservableField<T> checked, final T model) {

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

    @BindingAdapter({"app:value"})
    public static void setLimits(final SeekBar seekBar, final SeekBarModel model) {
//        seekBar.setMax(100);
//
//        final ObservableFloat value = model.getValue();
//        final float min = model.getMinValue();
//        final float max = model.getMaxValue();
//
//        seekBar.setProgress((int) ((value.get() * 100) / (max - min)));
//
//        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                model.getValue().set(min + (progress / 100f) * (max - min));
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });
    }

    @BindingAdapter("app:model")
    public static <T extends Number> void setNumericTransformer(DiscreteSeekBar seekBar, SeekBarModel<T> model) {

        final Class<? extends Number> valueClass = model.getValue().get().getClass();

        NumericTransformer transformer = null;
        if (valueClass.isAssignableFrom(Float.class)) {
            transformer = new NumericTransformer((Float) model.getMinValue(), (Float) model.getMaxValue());
        } else if (valueClass.isAssignableFrom(Long.class)) {
            transformer = new NumericTransformer((Long) model.getMinValue(), (Long) model.getMaxValue());
        }

        if (transformer != null) {
            seekBar.setNumericTransformer(transformer);
            seekBar.setMin(0);
            seekBar.setMax(100);
        }
    }

}
