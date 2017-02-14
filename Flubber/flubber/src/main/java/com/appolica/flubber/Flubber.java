package com.appolica.flubber;

import android.animation.Animator;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.Interpolator;

import com.appolica.flubber.animation.providers.Alpha;
import com.appolica.flubber.animation.providers.FadeIn;
import com.appolica.flubber.animation.providers.FadeInDown;
import com.appolica.flubber.animation.providers.FadeInLeft;
import com.appolica.flubber.animation.providers.FadeInRight;
import com.appolica.flubber.animation.providers.FadeInUp;
import com.appolica.flubber.animation.providers.FadeOut;
import com.appolica.flubber.animation.providers.FadeOutIn;
import com.appolica.flubber.animation.providers.Fall;
import com.appolica.flubber.animation.providers.Flash;
import com.appolica.flubber.animation.providers.FlipX;
import com.appolica.flubber.animation.providers.FlipY;
import com.appolica.flubber.animation.providers.Morph;
import com.appolica.flubber.animation.providers.Pop;
import com.appolica.flubber.animation.providers.Rotation;
import com.appolica.flubber.animation.providers.Shake;
import com.appolica.flubber.animation.providers.SlideDown;
import com.appolica.flubber.animation.providers.SlideLeft;
import com.appolica.flubber.animation.providers.SlideRight;
import com.appolica.flubber.animation.providers.SlideUp;
import com.appolica.flubber.animation.providers.Squeeze;
import com.appolica.flubber.animation.providers.SqueezeDown;
import com.appolica.flubber.animation.providers.SqueezeLeft;
import com.appolica.flubber.animation.providers.SqueezeRight;
import com.appolica.flubber.animation.providers.SqueezeUp;
import com.appolica.flubber.animation.providers.Swing;
import com.appolica.flubber.animation.providers.Wobble;
import com.appolica.flubber.animation.providers.ZoomIn;
import com.appolica.flubber.animation.providers.ZoomOut;
import com.appolica.flubber.interpolator.Spring;
import com.appolica.flubber.interpolator.bezier.EaseIn;
import com.appolica.flubber.interpolator.bezier.EaseInBack;
import com.appolica.flubber.interpolator.bezier.EaseInCirc;
import com.appolica.flubber.interpolator.bezier.EaseInCubic;
import com.appolica.flubber.interpolator.bezier.EaseInExpo;
import com.appolica.flubber.interpolator.bezier.EaseInOut;
import com.appolica.flubber.interpolator.bezier.EaseInOutBack;
import com.appolica.flubber.interpolator.bezier.EaseInOutCirc;
import com.appolica.flubber.interpolator.bezier.EaseInOutCubic;
import com.appolica.flubber.interpolator.bezier.EaseInOutExpo;
import com.appolica.flubber.interpolator.bezier.EaseInOutQuad;
import com.appolica.flubber.interpolator.bezier.EaseInOutQuart;
import com.appolica.flubber.interpolator.bezier.EaseInOutQuint;
import com.appolica.flubber.interpolator.bezier.EaseInOutSine;
import com.appolica.flubber.interpolator.bezier.EaseInQuad;
import com.appolica.flubber.interpolator.bezier.EaseInQuart;
import com.appolica.flubber.interpolator.bezier.EaseInQuint;
import com.appolica.flubber.interpolator.bezier.EaseInSine;
import com.appolica.flubber.interpolator.bezier.EaseOut;
import com.appolica.flubber.interpolator.bezier.EaseOutBack;
import com.appolica.flubber.interpolator.bezier.EaseOutCirc;
import com.appolica.flubber.interpolator.bezier.EaseOutCubic;
import com.appolica.flubber.interpolator.bezier.EaseOutExpo;
import com.appolica.flubber.interpolator.bezier.EaseOutQuad;
import com.appolica.flubber.interpolator.bezier.EaseOutQuart;
import com.appolica.flubber.interpolator.bezier.EaseOutQuint;
import com.appolica.flubber.interpolator.bezier.EaseOutSine;
import com.appolica.flubber.interpolator.bezier.Linear;

import java.util.HashMap;
import java.util.Map;

// TODO: 04.02.17 Set repeat count
public class Flubber {
    private static final String TAG = "Flubber";

    public static final float[] FRACTIONS = new float[]{0f, 0.2f, 0.4f, 0.6f, 0.8f, 1f};
    public static final String SCALE = "scale";

    public static AnimationBody.Builder with(View view) {
        return AnimationBody.Builder.getBuilder(view);
    }

    public static Animator create(@NonNull final AnimationBody animationBody) {
        final Animator animation = Flubber.getAnimation(animationBody);

        animation.setDuration(animationBody.getDuration());
        animation.setStartDelay(animation.getStartDelay());

        return animation;
    }

    public static Animator getAnimation(@NonNull final AnimationBody animationBody) {
        return animationBody.getAnimation().createAnimationFor(animationBody);
    }

    public static enum AnimationPreset implements AnimationProvider {
        SLIDE_LEFT,
        SLIDE_RIGHT,
        SLIDE_DOWN,
        SLIDE_UP,
        SQUEEZE_LEFT,
        SQUEEZE_RIGHT,
        SQUEEZE_DOWN,
        SQUEEZE_UP,
        FADE_IN,
        FADE_OUT,
        FADE_OUT_IN,
        FADE_IN_LEFT,
        FADE_IN_RIGHT,
        FADE_IN_DOWN,
        FADE_IN_UP,
        ZOOM_IN,
        ZOOM_OUT,
        FALL,
        SHAKE,
        POP,
        FLIP_X,
        FLIP_Y,
        MORPH,
        SQUEEZE,
        FLASH,
        WOBBLE,
        SWING,
        ALPHA,
        ROTATION;

        private static Map<AnimationPreset, AnimationProvider> providers = new HashMap<>();

        static {
            providers.put(SLIDE_LEFT, new SlideLeft());
            providers.put(SLIDE_RIGHT, new SlideRight());
            providers.put(SLIDE_DOWN, new SlideDown());
            providers.put(SLIDE_UP, new SlideUp());
            providers.put(SQUEEZE_LEFT, new SqueezeLeft());
            providers.put(SQUEEZE_RIGHT, new SqueezeRight());
            providers.put(SQUEEZE_DOWN, new SqueezeDown());
            providers.put(SQUEEZE_UP, new SqueezeUp());
            providers.put(FADE_IN, new FadeIn());
            providers.put(FADE_OUT, new FadeOut());
            providers.put(FADE_OUT_IN, new FadeOutIn());
            providers.put(FADE_IN_LEFT, new FadeInLeft());
            providers.put(FADE_IN_RIGHT, new FadeInRight());
            providers.put(FADE_IN_DOWN, new FadeInDown());
            providers.put(FADE_IN_UP, new FadeInUp());
            providers.put(ZOOM_IN, new ZoomIn());
            providers.put(ZOOM_OUT, new ZoomOut());
            providers.put(FALL, new Fall());
            providers.put(SHAKE, new Shake());
            providers.put(POP, new Pop());
            providers.put(FLIP_X, new FlipX());
            providers.put(FLIP_Y, new FlipY());
            providers.put(MORPH, new Morph());
            providers.put(SQUEEZE, new Squeeze());
            providers.put(FLASH, new Flash());
            providers.put(WOBBLE, new Wobble());
            providers.put(SWING, new Swing());
            providers.put(ALPHA, new Alpha());
            providers.put(ROTATION, new Rotation());
        }


        public Animator createAnimationFor(AnimationBody animationBody) {
            return providers.get(this).createAnimationFor(animationBody);
        }
    }

    public static enum Curve implements InterpolatorProvider {
        BZR_EASE_IN,
        BZR_EASE_OUT,
        BZR_EASE_IN_OUT,
        BZR_LINEAR,
        BZR_SPRING,
        BZR_EASE_IN_SINE,
        BZR_EASE_OUT_SINE,
        BZR_EASE_IN_OUT_SINE,
        BZR_EASE_IN_QUAD,
        BZR_EASE_OUT_QUAD,
        BZR_EASE_IN_OUT_QUAD,
        BZR_EASE_IN_CUBIC,
        BZR_EASE_OUT_CUBIC,
        BZR_EASE_IN_OUT_CUBIC,
        BZR_EASE_IN_QUART,
        BZR_EASE_OUT_QUART,
        BZR_EASE_IN_OUT_QUART,
        BZR_EASE_IN_QUINT,
        BZR_EASE_OUT_QUINT,
        BZR_EASE_IN_OUT_QUINT,
        BZR_EASE_IN_EXPO,
        BZR_EASE_OUT_EXPO,
        BZR_EASE_IN_OUT_EXPO,
        BZR_EASE_IN_CIRC,
        BZR_EASE_OUT_CIRC,
        BZR_EASE_IN_OUT_CIRC,
        BZR_EASE_IN_BACK,
        BZR_EASE_OUT_BACK,
        BZR_EASE_IN_OUT_BACK,
        SPRING;

        private static Map<Curve, InterpolatorProvider> providers = new HashMap<>();

        static {
            providers.put(BZR_EASE_IN, new EaseIn());
            providers.put(BZR_EASE_OUT, new EaseOut());
            providers.put(BZR_EASE_IN_OUT, new EaseInOut());
            providers.put(BZR_LINEAR, new Linear());
            providers.put(BZR_SPRING, new Spring());
            providers.put(BZR_EASE_IN_SINE, new EaseInSine());
            providers.put(BZR_EASE_OUT_SINE, new EaseOutSine());
            providers.put(BZR_EASE_IN_OUT_SINE, new EaseInOutSine());
            providers.put(BZR_EASE_IN_QUAD, new EaseInQuad());
            providers.put(BZR_EASE_OUT_QUAD, new EaseOutQuad());
            providers.put(BZR_EASE_IN_OUT_QUAD, new EaseInOutQuad());
            providers.put(BZR_EASE_IN_CUBIC, new EaseInCubic());
            providers.put(BZR_EASE_OUT_CUBIC, new EaseOutCubic());
            providers.put(BZR_EASE_IN_OUT_CUBIC, new EaseInOutCubic());
            providers.put(BZR_EASE_IN_QUART, new EaseInQuart());
            providers.put(BZR_EASE_OUT_QUART, new EaseOutQuart());
            providers.put(BZR_EASE_IN_OUT_QUART, new EaseInOutQuart());
            providers.put(BZR_EASE_IN_QUINT, new EaseInQuint());
            providers.put(BZR_EASE_OUT_QUINT, new EaseOutQuint());
            providers.put(BZR_EASE_IN_OUT_QUINT, new EaseInOutQuint());
            providers.put(BZR_EASE_IN_EXPO, new EaseInExpo());
            providers.put(BZR_EASE_OUT_EXPO, new EaseOutExpo());
            providers.put(BZR_EASE_IN_OUT_EXPO, new EaseInOutExpo());
            providers.put(BZR_EASE_IN_CIRC, new EaseInCirc());
            providers.put(BZR_EASE_OUT_CIRC, new EaseOutCirc());
            providers.put(BZR_EASE_IN_OUT_CIRC, new EaseInOutCirc());
            providers.put(BZR_EASE_IN_BACK, new EaseInBack());
            providers.put(BZR_EASE_OUT_BACK, new EaseOutBack());
            providers.put(BZR_EASE_IN_OUT_BACK, new EaseInOutBack());
            providers.put(SPRING, new Spring());
        }

        @Override
        public Interpolator createInterpolatorFor(AnimationBody animationBody) {
            return providers.get(this).createInterpolatorFor(animationBody);
        }
    }

    public static interface AnimationProvider {
        public Animator createAnimationFor(final AnimationBody animationBody);
    }

    public static interface InterpolatorProvider {
        public Interpolator createInterpolatorFor(final AnimationBody animationBody);
    }

}
