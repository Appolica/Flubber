package com.appolica.sample.ui.editor.pager.settings;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

public class NumericTransformer extends DiscreteSeekBar.NumericTransformer {

    private int factor = 1;
    private int minValue;
    private int maxValue;

    public NumericTransformer(float minValue, float maxValue) {
        this.minValue = toInt(minValue);
        this.maxValue = toInt(maxValue);
    }

    public NumericTransformer(long minValue, long maxValue) {
        this.minValue = (int) minValue;
        this.maxValue = (int) maxValue;
    }

    private int toInt(float value) {
        float result = value;
        for (int i = 1; result % 2 != 0; i++) {
            factor = (int) Math.pow(10, i);
            result = value * factor;
        }

        return (int) result;
    }

    @Override
    public int transform(int value) {
        return value;
    }

    @Override
    public String transformToString(int percentage) {

        float value = minValue + (percentage / 100f) * (maxValue - minValue);
        float displayValue = value / factor;

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

    public int getFactor() {
        return factor;
    }

    public int getMinValue() {
        return minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }
}
