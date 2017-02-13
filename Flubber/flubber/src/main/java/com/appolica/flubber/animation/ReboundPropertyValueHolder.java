package com.appolica.flubber.animation;

import android.util.Property;
import android.view.View;

public class ReboundPropertyValueHolder {

    private Property<View, Float> property;
    private float startValue;
    private float endValue;
    private float dValue;

    private float animatedValue;

    public ReboundPropertyValueHolder(Property<View, Float> property, float startValue, float endValue) {
        this.property = property;
        this.startValue = startValue;
        this.endValue = endValue;

        dValue = endValue - startValue;
        animatedValue = startValue;
    }

    public static ReboundPropertyValueHolder ofFloat(Property<View, Float> property, float startValue, float endValue) {
        return new ReboundPropertyValueHolder(property, startValue, endValue);
    }

    void calculateValue(float reboundValue) {
        animatedValue = startValue + dValue * (reboundValue);
    }

    public Property<View, Float> getProperty() {
        return property;
    }

    public float getStartValue() {
        return startValue;
    }

    public float getEndValue() {
        return endValue;
    }

    public float getAnimatedValue() {
        return animatedValue;
    }
}
