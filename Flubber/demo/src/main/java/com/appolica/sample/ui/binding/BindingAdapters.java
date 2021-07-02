package com.appolica.sample.ui.binding;

import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.SeekBar;

import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;

import com.appolica.sample.ui.editor.pager.settings.NumericTransformer;
import com.appolica.sample.ui.editor.pager.settings.SeekBarModel;
import com.appolica.sample.ui.editor.pager.settings.SimpleOnProgressChangeListener;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

public class BindingAdapters {

    @BindingAdapter({"checked", "model"})
    public static <T> void setChecked(RadioButton radioButton, final ObservableField<T> checked, final T model) {

        if (checked == null) {
            return;
        }

        radioButton.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {
                    if ((checked.get() == null || !checked.get().equals(model))
                            && isChecked) {

                        checked.set(model);
                    }
                });

        final T checkedModel = checked.get();
        final boolean shouldBeChecked = checkedModel != null && checkedModel.equals(model);

        if (shouldBeChecked != radioButton.isChecked()) {
            radioButton.setChecked(shouldBeChecked);
        }
    }

    @BindingAdapter("propagateClick")
    public static void propagateClick(final ViewGroup container, boolean propagate) {
        if (propagate) {
            container.setOnClickListener(v -> {
                for (int i = 0; i < container.getChildCount(); i++) {
                    container.getChildAt(i).performClick();
                }
            });
        }
    }

    @BindingAdapter({"value"})
    public static void setLimits(final SeekBar seekBar, final SeekBarModel model) {
//        seekBar.setMax(100);
//
//        final ObservableFloat value = model.get();
//        final float min = model.getMinValue();
//        final float max = model.getMaxValue();
//
//        seekBar.setProgress((int) ((value.get() * 100) / (max - min)));
//
//        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                model.get().set(min + (progress / 100f) * (max - min));
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

    @BindingAdapter("model")
    public static void bindToModel(DiscreteSeekBar seekBar, final SeekBarModel model) {
        DiscreteSeekBar.NumericTransformer transformer = seekBar.getNumericTransformer();

        if (!(transformer instanceof NumericTransformer)) {
            transformer = new NumericTransformer();
            seekBar.setNumericTransformer(transformer);
        }

        final NumericTransformer customTransformer = (NumericTransformer) transformer;
        customTransformer.setValues(model.getMinValue(), model.getMaxValue());

        seekBar.setMin(0);
        seekBar.setMax(100);

        final int percentage = (int) customTransformer.transformToPercentage(model.getValue().get());
        // Bug in the library
        seekBar.setProgress(1);
        seekBar.setProgress(percentage);

        seekBar.setOnProgressChangeListener(
                SimpleOnProgressChangeListener.forStopTrackingTouch(seekBar1 -> {
                    model.getValue().set(customTransformer.transformFromPercentage(seekBar.getProgress()));
                }));
    }

}
