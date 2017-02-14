package com.appolica.flubber.animation;

import android.util.Property;
import android.view.View;

import com.facebook.rebound.SpringUtil;

public class ReboundPropertyValueHolder {

    private Property<View, Float> property;
    private float startValue;
    private float endValue;

    private float animatedValue;

    public ReboundPropertyValueHolder(Property<View, Float> property, float startValue, float endValue) {
        this.property = property;
        this.startValue = startValue;
        this.endValue = endValue;

        animatedValue = startValue;
    }

    public static ReboundPropertyValueHolder ofFloat(Property<View, Float> property, float startValue, float endValue) {
        return new ReboundPropertyValueHolder(property, startValue, endValue);
    }

    void calculateValue(float reboundValue) {
        animatedValue = (float) SpringUtil.mapValueFromRangeToRange(reboundValue, 0, 1, startValue, endValue);
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
