package com.appolica.flubber;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;

import com.appolica.flubber.annotations.RepeatMode;

import org.jetbrains.annotations.Contract;

import java.io.Serializable;

public class AnimationBody implements Serializable {

    private boolean autoStart;

    private float force = 1;
    private float damping = 0.7f;
    private float velocity = 0.7f;

    private float startX = 0f;
    private float endX = 0f;

    private float startY = 0f;
    private float endY = 0f;

    private float startScaleX = 1L;
    private float endScaleX = 1L;

    private float startScaleY = 1L;
    private float endScaleY = 1L;

    private int repeatCount = 0;
    private int repeatMode = ValueAnimator.RESTART;

    private long delay = 0L;
    private long duration = 700L;

    private boolean animateFrom;

    private Flubber.AnimationProvider animation;
    private Flubber.InterpolatorProvider interpolator;

    public AnimationBody() {
    }

    public Animator createFor(View view) {
        final Animator animation = Flubber.getAnimation(this, view);

        animation.setDuration(duration);
        animation.setStartDelay(delay);

        if (autoStart) {
            animation.start();
        }

        return animation;
    }

    public boolean autoStart() {
        return autoStart;
    }

    public void setAutoStart(boolean autoStart) {
        this.autoStart = autoStart;
    }

    public float getForce() {
        return force;
    }

    public void setForce(float force) {
        this.force = force;
    }

    public float getDamping() {
        return damping;
    }

    public void setDamping(float damping) {
        this.damping = damping;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public int getRepeatCount() {
        return repeatCount;
    }

    public void setRepeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
    }

    public float getStartX() {
        return startX;
    }

    public void setStartX(float startX) {
        this.startX = startX;
    }

    public float getStartY() {
        return startY;
    }

    public void setStartY(float startY) {
        this.startY = startY;
    }

    public float getStartScaleX() {
        return startScaleX;
    }

    public void setStartScaleX(float startScaleX) {
        this.startScaleX = startScaleX;
    }

    public float getStartScaleY() {
        return startScaleY;
    }

    public void setStartScaleY(float startScaleY) {
        this.startScaleY = startScaleY;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public boolean animateFrom() {
        return animateFrom;
    }

    public void setAnimateFrom(boolean animateFrom) {
        this.animateFrom = animateFrom;
    }

    public Flubber.AnimationProvider getAnimation() {
        return animation;
    }

    public void setAnimation(Flubber.AnimationProvider animation) {
        this.animation = animation;
    }

    public Flubber.InterpolatorProvider getInterpolator() {
        return interpolator;
    }

    public void setInterpolator(Flubber.InterpolatorProvider interpolator) {
        this.interpolator = interpolator;
    }

    @RepeatMode
    public int getRepeatMode() {
        return repeatMode;
    }

    public void setRepeatMode(@RepeatMode int repeatMode) {
        this.repeatMode = repeatMode;
    }

    public float getEndScaleX() {
        return endScaleX;
    }

    public void setEndScaleX(float endScaleX) {
        this.endScaleX = endScaleX;
    }

    public float getEndScaleY() {
        return endScaleY;
    }

    public void setEndScaleY(float endScaleY) {
        this.endScaleY = endScaleY;
    }

    public float getEndX() {
        return endX;
    }

    public void setEndX(float endX) {
        this.endX = endX;
    }

    public float getEndY() {
        return endY;
    }

    public void setEndY(float endY) {
        this.endY = endY;
    }

    public static final class Builder {

        private boolean autoStart;

        private float force = 1;
        private float damping = 0.7f;
        private float velocity = 0.7f;

        private float startX = 0f;
        private float endX = 0f;
        private float startY = 0f;
        private float endY = 0f;

        private float startScaleX = 1L;
        private float endScaleX = 1L;
        private float startScaleY = 1L;
        private float endScaleY = 1L;

        private int repeatCount = 0;
        private int repeatMode = ValueAnimator.RESTART;

        private long delay = 0L;
        private long duration = 700L;

        private boolean animateFrom;

        private Flubber.AnimationProvider animation;
        private Flubber.InterpolatorProvider interpolatorProvider;

        private Builder() {

        }

        @Contract(" -> !null")
        public static Builder getBuilder() {
            return new Builder();
        }

        public Builder autoStart(boolean autoStart) {
            this.autoStart = autoStart;
            return this;
        }

        public Builder force(float force) {
            this.force = force;
            return this;
        }

        public Builder damping(float damping) {
            this.damping = damping;
            return this;
        }

        public Builder velocity(float velocity) {
            this.velocity = velocity;
            return this;
        }

        public Builder repeatCount(int repeatCount) {
            this.repeatCount = repeatCount;
            return this;
        }

        public Builder repeatMode(@RepeatMode int repeatMode) {
            this.repeatMode = repeatMode;
            return this;
        }

        public Builder translateX(float endX) {
            this.endX = endX;
            return this;
        }

        public Builder translateX(float startX, float endX) {
            this.startX = startX;
            this.endX = endX;
            return this;
        }

        public Builder translateY(float endY) {
            this.endY = endY;
            return this;
        }

        public Builder translateY(float startY, float endY) {
            this.startY = startY;
            this.endY = endY;
            return this;
        }

        public Builder scaleX(float endScaleX) {
            this.endScaleX = endScaleX;
            return this;
        }

        public Builder scaleX(float startScaleX, float endScaleX) {
            this.startScaleX = startScaleX;
            this.endScaleX = endScaleX;
            return this;
        }

        public Builder scaleY(float endScaleY) {
            this.endScaleY = endScaleY;
            return this;
        }

        public Builder scaleY(float startScaleY, float endScaleY) {
            this.startScaleY = startScaleY;
            this.endScaleY = endScaleY;
            return this;
        }

        public Builder delay(long delay) {
            this.delay = delay;
            return this;
        }

        public Builder duration(long duration) {
            this.duration = duration;
            return this;
        }

        public Builder animateFrom(boolean animateFrom) {
            this.animateFrom = animateFrom;
            return this;
        }

        public Builder animation(Flubber.AnimationProvider animation) {
            this.animation = animation;
            return this;
        }

        public Builder interpolator(Flubber.InterpolatorProvider interpolator) {
            this.interpolatorProvider = interpolator;
            return this;
        }

        public Animator createFor(View view) {
            return build().createFor(view);
        }

        public AnimationBody build() {
            final AnimationBody animationBody = new AnimationBody();
            animationBody.setAutoStart(autoStart);

            animationBody.setForce(force);
            animationBody.setDamping(damping);
            animationBody.setVelocity(velocity);

            animationBody.setRepeatCount(repeatCount);
            animationBody.setRepeatMode(repeatMode);

            animationBody.setStartX(startX);
            animationBody.setEndX(endX);

            animationBody.setStartY(startY);
            animationBody.setEndY(endY);

            animationBody.setStartScaleX(startScaleX);
            animationBody.setEndScaleX(endScaleX);

            animationBody.setStartScaleY(startScaleY);
            animationBody.setEndScaleY(endScaleY);

            animationBody.setDelay(delay);
            animationBody.setDuration(duration);

            animationBody.setAnimateFrom(animateFrom);

            animationBody.setAnimation(animation);
            animationBody.setInterpolator(interpolatorProvider);

            return animationBody;
        }
    }
}
