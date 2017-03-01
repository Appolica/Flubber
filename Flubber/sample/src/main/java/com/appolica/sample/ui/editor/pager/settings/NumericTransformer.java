package com.appolica.sample.ui.editor.pager.settings;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

public class NumericTransformer extends DiscreteSeekBar.NumericTransformer {
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

    public NumericTransformer(long minValue, long maxValue) {
        this.minValue = (int) minValue;
        this.maxValue = (int) maxValue;
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
        return value;
    }

    @Override
    public String transformToString(int percentage) {

        float value = (percentage / 100f) * (maxValue - minValue);
        float displayValue = (float) minValue / factor + value / factor;

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
        return (100 * (value)) / (maxValue / factor);
    }
}
