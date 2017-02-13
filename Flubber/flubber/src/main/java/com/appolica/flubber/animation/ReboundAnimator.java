package com.appolica.flubber.animation;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringListener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ReboundAnimator
        extends Animator
        implements PropertyValueUpdateListener, ReboundListener {

    private static final String TAG = "ReboundAnimator";

    private static final int DURATION_UNSPECIFIED = -123;

    private final WeakReference<View> target;

    private Map<String, ReboundPropertyValueHolder> pvHolders;

    private List<AnimatorUpdateListener> updateListeners;

    private final Spring spring;

    private boolean loggingTime = false;
    private long startTime;

    public static ReboundAnimator ofPropertyValueHolder(
            @NonNull final Spring spring,
            @NonNull View target,
            @NonNull ReboundPropertyValueHolder... values) {

        return new ReboundAnimator(spring, target, values);
    }

    private ReboundAnimator(@NonNull final Spring spring, View target, ReboundPropertyValueHolder[] values) {
        this.spring = spring;
        this.target = new WeakReference<>(target);
        this.pvHolders = new HashMap<>();

        for (int i = 0; i < values.length; i++) {
            pvHolders.put(values[i].getProperty().getName(), values[i]);
        }

        spring.addListener(new Engine(new ArrayList<>(pvHolders.values()), this, this));
    }

    @Override
    public boolean isRunning() {
        return spring.isAtRest();
    }

    @Override
    public void start() {
        if (loggingTime) {
            startTime = new Date().getTime();
        }

        spring.setCurrentValue(0);
        spring.setEndValue(1);
    }

    @Override
    public void cancel() {
        spring.setAtRest();
    }

    @Override
    public void setStartDelay(final long startDelay) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                start();
            }
        }, startDelay);
    }

    @Override
    public long getStartDelay() {
        return 0;
    }

    public float getAnimatedValue(String propertyName) {
        return pvHolders.get(propertyName).getAnimatedValue();
    }

    public void addUpdateListener(AnimatorUpdateListener updateListener) {
        if (updateListeners == null) {
            updateListeners = new ArrayList<>();
        }

        updateListeners.add(updateListener);
    }

    @Nullable
    public List<AnimatorUpdateListener> getUpdateListeners() {
        return updateListeners;
    }

    @Override
    public void onPropertyValueUpdate(ReboundPropertyValueHolder pvHolder) {

        final float animatedValue = pvHolder.getAnimatedValue();
        pvHolder.getProperty().set(target.get(), animatedValue);

        if (updateListeners != null) {
            for (AnimatorUpdateListener listener : updateListeners) {
                listener.onAnimationUpdate(this);
            }
        }
    }

    @Override
    public void onSpringAtRest(Spring spring) {
        if (loggingTime) {
            final long endTime = new Date().getTime();

            Log.d(TAG, String.format("Total duration: %dms", endTime - startTime));
        }

        final ArrayList<AnimatorListener> listeners = getListeners();
        if (listeners != null) {
            for (AnimatorListener listener : listeners) {
                listener.onAnimationEnd(this);
            }
        }
    }

    @Override
    public void onSpringActivate(Spring spring) {
        final ArrayList<AnimatorListener> listeners = getListeners();
        if (listeners != null) {
            for (AnimatorListener listener : listeners) {
                listener.onAnimationStart(this);
            }
        }
    }

    @Override
    public void onSpringEndStateChange(Spring spring) {

    }

    private class Engine implements SpringListener {

        private final List<ReboundPropertyValueHolder> pvHolders;
        private final ReboundListener reboundListener;

        private PropertyValueUpdateListener updateListener;

        public Engine(
                List<ReboundPropertyValueHolder> pvHolders,
                ReboundListener reboundListener,
                PropertyValueUpdateListener updateListener) {

            this.pvHolders = pvHolders;
            this.reboundListener = reboundListener;
            this.updateListener = updateListener;
        }

        @Override
        public void onSpringUpdate(Spring spring) {
            final float reboundValue = (float) spring.getCurrentValue();

            for (ReboundPropertyValueHolder pvHolder : pvHolders) {

                pvHolder.calculateValue(reboundValue);
                updateListener.onPropertyValueUpdate(pvHolder);
            }
        }

        @Override
        public void onSpringAtRest(Spring spring) {
            reboundListener.onSpringAtRest(spring);
        }

        @Override
        public void onSpringActivate(Spring spring) {
            reboundListener.onSpringActivate(spring);
        }

        @Override
        public void onSpringEndStateChange(Spring spring) {
            reboundListener.onSpringEndStateChange(spring);
        }
    }

    public static interface AnimatorUpdateListener {

        void onAnimationUpdate(ReboundAnimator animation);
    }

    @Override
    public Animator setDuration(long duration) {
        // Duration is unspecified
        return this;
    }

    @Override
    public long getDuration() {
        return DURATION_UNSPECIFIED;
    }

    @Override
    public void setInterpolator(TimeInterpolator value) {
        // The interpolator is ignored
    }

    public boolean isLoggingTime() {
        return loggingTime;
    }

    public void setLoggingTime(boolean loggingTime) {
        this.loggingTime = loggingTime;
    }
}
