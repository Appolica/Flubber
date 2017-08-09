package com.appolica.sample.ui.editor.pager.settings;

import android.util.Log;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

public class NumericTransformer extends DiscreteSeekBar.NumericTransformer {
    private static final String TAG = "NumericTransformer";

    private int factor = 1;
    private long minValue;
    private long maxValue;

    public NumericTransformer() {

    }

    public NumericTransformer(float minValue, float maxValue) {
        calculateFactor(minValue, maxValue);
        this.minValue = (long) (minValue * factor);
        this.maxValue = (long) (maxValue * factor);
    }

    private void calculateFactor(float minValue, float maxValue) {
        this.factor = getFactor(minValue) * getFactor(maxValue);
    }

    private int getFactor(float value) {
        int factor = 1;
        float result = value;
        for (int i = 1; result % 2 != 0; i++) {
            factor = (int) Math.pow(10, i);
            result = value * factor;
        }

        return factor;
    }

    @Override
    public int transform(int value) {
        Log.d(TAG, "transform: " + value);
        return value;
    }

    @Override
    public String transformToString(int percentage) {

        float displayValue = transformFromPercentage(percentage);
        Log.d(TAG, "transformToString: " + displayValue);
        if (factor == 1) {
            return String.format("%d", (int) displayValue);
        } else {
            return String.format("%.2f", displayValue);
        }
    }

    @Override
    public boolean useStringTransform() {
        return true;
    }

    public long getMinValue() {
        return minValue;
    }

    public void setValues(float minValue, float maxValue) {
        calculateFactor(minValue, maxValue);
        this.minValue = (long) (minValue * factor);
        this.maxValue = (long) (maxValue * factor);
    }

    public long getMaxValue() {
        return maxValue;
    }

    public int getFactor() {
        return factor;
    }

    public float transformToPercentage(float value) {
        final float result = (100 * (value - (minValue / factor))) / ((maxValue - minValue) / factor);
        return result;
    }

    public float transformFromPercentage(int percentage) {
        float value = (percentage / 100f) * ((maxValue - minValue) / factor);
        float result = (float) minValue / factor + value;

        return result;
    }
}
