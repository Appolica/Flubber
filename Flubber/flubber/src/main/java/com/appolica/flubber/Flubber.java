package com.appolica.flubber;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.support.annotation.NonNull;
import android.support.v4.view.animation.PathInterpolatorCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.appolica.flubber.utils.DimensionUtils;

import static android.animation.PropertyValuesHolder.ofKeyframe;

// TODO: 04.02.17 Set repeat count
public class Flubber {
    private static final String TAG = "Flubber";

    public static final float[] FRACTIONS = new float[]{0f, 0.2f, 0.4f, 0.6f, 0.8f, 1f};
    private static final String SCALE = "scale";

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
        switch (animationBody.getAnimation()) {
            case SLIDE_LEFT:
                break;
            case SLIDE_RIGHT:
                break;
            case SLIDE_DOWN:
                break;
            case SLIDE_UP:
                break;
            case SQUEEZE_LEFT:
                break;
            case SQUEEZE_RIGHT:
                break;
            case SQUEEZE_DOWN:
                break;
            case SQUEEZE_UP:
                return getSqueezeUp(animationBody);
            case FADE_IN:
                return getFadeIn(animationBody);
            case FADE_OUT:
                return getFadeOut(animationBody);
            case FADE_OUT_IN:
                return getFadeOutIn(animationBody);
            case FADE_IN_LEFT:
                return getFadeInLeft(animationBody);
            case FADE_IN_RIGHT:
                return getFadeInRight(animationBody);
            case FADE_IN_DOWN:
                return getFadeInDown(animationBody);
            case FADE_IN_UP:
                return getFadeInUp(animationBody);
            case ZOOM_IN:
                return getZoomIn(animationBody);
            case ZOOM_OUT:
                return getZoomOut(animationBody);
            case FALL:
                return getFall(animationBody);
            case SHAKE:
                return getShake(animationBody);
            case POP:
                return getPop(animationBody);
            case FLIP_X:
                return getFlipX(animationBody);
            case FLIP_Y:
                return getFlipY(animationBody);
            case MORPH:
                return getMorph(animationBody);
            case SQUEEZE:
                return getSqueeze(animationBody);
            case FLASH:
                return getFlash(animationBody);
            case WOBBLE:
                return getWobble(animationBody);
            case SWING:
                return getSwing(animationBody);
        }

        return null;
    }

    @NonNull
    public static ObjectAnimator getSqueezeUp(@NonNull final AnimationBody animationBody) {

        final View view = animationBody.getView();

        final float startY = DimensionUtils.dp2px(300);
        final float endY = 0f;

        final PropertyValuesHolder translationPVH =
                PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, startY, endY);

        final float startScaleY = 3 * animationBody.getForce();
        final float endScaleY = 1f;
        final PropertyValuesHolder scalePVH =
                PropertyValuesHolder.ofFloat(View.SCALE_Y, startScaleY, endScaleY);

        final ObjectAnimator animation =
                ObjectAnimator.ofPropertyValuesHolder(view, translationPVH, scalePVH);

        animation.setInterpolator(animationBody.getInterpolator());

        return animation;
    }

    @NonNull
    public static ObjectAnimator getFadeIn(@NonNull final AnimationBody animationBody) {
        final View view = animationBody.getView();

        final ObjectAnimator animation = ObjectAnimator.ofFloat(view, View.ALPHA, 0f, 1f);

        animation.setInterpolator(animationBody.getInterpolator());

        return animation;
    }

    @NonNull
    public static ObjectAnimator getFadeOut(@NonNull final AnimationBody animationBody) {
        final View view = animationBody.getView();

        final ObjectAnimator animation = ObjectAnimator.ofFloat(view, View.ALPHA, 1f, 0f);

        animation.setInterpolator(animationBody.getInterpolator());

        return animation;
    }

    @NonNull
    public static ObjectAnimator getFadeOutIn(@NonNull final AnimationBody animationBody) {
        final View view = animationBody.getView();

        final ObjectAnimator animation = ObjectAnimator.ofFloat(view, View.ALPHA, 1f, 0f);

        animation.setRepeatCount(animationBody.getRepeatCount() * 2 + 1);
        animation.setRepeatMode(ValueAnimator.REVERSE);

        animation.setInterpolator(animationBody.getInterpolator());

        return animation;
    }

    @NonNull
    public static AnimatorSet getFadeInLeft(@NonNull final AnimationBody animationBody) {
        final View view = animationBody.getView();

        final float startY = DimensionUtils.dp2px(300) * animationBody.getForce();
        final float endY = 0;

        final AnimatorSet animatorSet = new AnimatorSet();
        final ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(view, View.ALPHA, 0f, 1f);
        alphaAnimation.setInterpolator(new LinearInterpolator());


        final ObjectAnimator translateAnimation = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, startY, endY);

        translateAnimation.setInterpolator(animationBody.getInterpolator());

        animatorSet.play(translateAnimation)
                .with(alphaAnimation);

        return animatorSet;
    }

    @NonNull
    public static AnimatorSet getFadeInRight(@NonNull final AnimationBody animationBody) {
        final View view = animationBody.getView();

        final float startY = -DimensionUtils.dp2px(300) * animationBody.getForce();
        final float endY = 0;

        final AnimatorSet animatorSet = new AnimatorSet();
        final ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(view, View.ALPHA, 0f, 1f);
        alphaAnimation.setInterpolator(new LinearInterpolator());

        final ObjectAnimator translateAnimation = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, startY, endY);
        translateAnimation.setInterpolator(animationBody.getInterpolator());

        animatorSet.play(translateAnimation)
                .with(alphaAnimation);

        return animatorSet;
    }

    @NonNull
    public static AnimatorSet getFadeInDown(@NonNull final AnimationBody animationBody) {
        // TODO: 06.02.17 fix interpolation
        final View view = animationBody.getView();

        final float startY = -DimensionUtils.dp2px(300) * animationBody.getForce();
        final float endY = 0;

        final AnimatorSet animatorSet = new AnimatorSet();
        final ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(view, View.ALPHA, 0f, 1f);
        alphaAnimation.setInterpolator(new LinearInterpolator());


        final ObjectAnimator translateAnimation = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, startY, endY);

        translateAnimation.setInterpolator(animationBody.getInterpolator());

        animatorSet.play(translateAnimation)
                .with(alphaAnimation);

        return animatorSet;
    }

    @NonNull
    public static AnimatorSet getFadeInUp(@NonNull final AnimationBody animationBody) {
        // TODO: 06.02.17 fix interpolation
        final View view = animationBody.getView();

        final float startY = DimensionUtils.dp2px(300) * animationBody.getForce();
        final float endY = 0f;

        final AnimatorSet animatorSet = new AnimatorSet();
        final ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(view, View.ALPHA, 0f, 1f);
        alphaAnimation.setInterpolator(new LinearInterpolator());


        final ObjectAnimator translateAnimation = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, startY, endY);

        translateAnimation.setInterpolator(animationBody.getInterpolator());

        animatorSet.play(translateAnimation)
                .with(alphaAnimation);

        return animatorSet;
    }

    @NonNull
    public static ObjectAnimator getZoomIn(@NonNull final AnimationBody animationBody) {
        final View view = animationBody.getView();

        final float scale = 2 * animationBody.getForce();

        final PropertyValuesHolder alphaPVH = PropertyValuesHolder.ofFloat(View.ALPHA, 0f, 1f);
        final PropertyValuesHolder scaleXPVH = PropertyValuesHolder.ofFloat(View.SCALE_X, scale, 1f);
        final PropertyValuesHolder scaleYPVH = PropertyValuesHolder.ofFloat(View.SCALE_Y, scale, 1f);

        final ObjectAnimator animation =
                ObjectAnimator.ofPropertyValuesHolder(view, alphaPVH, scaleXPVH, scaleYPVH);

        animation.setInterpolator(animationBody.getInterpolator());

        return animation;
    }

    @NonNull
    public static ObjectAnimator getZoomOut(@NonNull final AnimationBody animationBody) {

        final View view = animationBody.getView();

        final float scale = 2 * animationBody.getForce();

        final PropertyValuesHolder alphaPVH = PropertyValuesHolder.ofFloat(View.ALPHA, 1f, 0f);
        final PropertyValuesHolder scaleXPVH = PropertyValuesHolder.ofFloat(View.SCALE_X, 1f, scale);
        final PropertyValuesHolder scaleYPVH = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f, scale);

        final ObjectAnimator animation =
                ObjectAnimator.ofPropertyValuesHolder(view, alphaPVH, scaleXPVH, scaleYPVH);

        animation.setInterpolator(animationBody.getInterpolator());

        return animation;
    }

    @NonNull
    public static AnimatorSet getFall(@NonNull final AnimationBody animationBody) {
        final AnimatorSet animatorSet = new AnimatorSet();

        final View view = animationBody.getView();
        final DisplayMetrics displayMetrics = DimensionUtils.getDisplayMetrics(view.getContext());

        final int startY = 0;
        final float endY = (displayMetrics.heightPixels - view.getY()) * animationBody.getForce();

        final ObjectAnimator translateAnimation =
                ObjectAnimator.ofFloat(view, View.TRANSLATION_Y.getName(), startY, endY);

        translateAnimation.setInterpolator(animationBody.getInterpolator());


        final float startRotation = view.getRotation();
        final float endRotation = (float) Math.toDegrees(15 * (Math.PI / 180));

        final ObjectAnimator rotateAnimation =
                ObjectAnimator.ofFloat(view, View.ROTATION, startRotation, endRotation);

        animatorSet
                .play(translateAnimation)
                .with(rotateAnimation);

        return animatorSet;
    }

    @NonNull
    private static ObjectAnimator getShake(@NonNull AnimationBody animationBody) {
        final float dX = DimensionUtils.dp2px(30);
        final float force = animationBody.getForce();

        float[] translationValues = {0f, dX * force, -dX * force, dX * force, 0f, 0f};
        final PropertyValuesHolder translationPVH =
                PropertyValuesHolder.ofKeyframe(View.TRANSLATION_X, getKeyFrames(FRACTIONS, translationValues));

        final ObjectAnimator animation =
                ObjectAnimator.ofPropertyValuesHolder(animationBody.getView(), translationPVH);

        animation.setInterpolator(animationBody.getInterpolator());

        return animation;
    }

    @NonNull
    public static ValueAnimator getPop(@NonNull final AnimationBody animationBody) {
        final float force = animationBody.getForce();
        final float[] values = {0f, 0.2f * force, -0.2f * force, 0.2f * force, 0f, 0f};

        final PropertyValuesHolder scaleXPVH =
                PropertyValuesHolder.ofKeyframe(SCALE, getKeyFrames(FRACTIONS, values));

        final ValueAnimator animation = ObjectAnimator.ofPropertyValuesHolder(scaleXPVH);

        animation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            private int initialWidth = -1;

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                final View view = animationBody.getView();

                if (initialWidth == -1) {
                    initialWidth = view.getWidth();
                }

                final float animScaleX = (float) animation.getAnimatedValue(SCALE);

                final float scale = (view.getWidth() + view.getWidth() * animScaleX) / initialWidth;

                view.setScaleX(scale);
                view.setScaleY(scale);
            }
        });

        animation.setInterpolator(animation.getInterpolator());

        return animation;
    }

    @NonNull
    public static Animator getFlipX(@NonNull AnimationBody animationBody) {
        final View view = animationBody.getView();

        final float startRotation = view.getRotationX();
        final float endRotation = startRotation + 180f;

        final PropertyValuesHolder rotationPVH =
                PropertyValuesHolder.ofFloat(View.ROTATION_X, startRotation, endRotation);

        final ObjectAnimator animation =
                ObjectAnimator.ofPropertyValuesHolder(view, rotationPVH);

        animation.setInterpolator(animationBody.getInterpolator());

        return animation;
    }

    @NonNull
    public static Animator getFlipY(@NonNull AnimationBody animationBody) {
        final View view = animationBody.getView();

        final float startRotation = view.getRotationY();
        final float endRotation = startRotation + 180f;

        final PropertyValuesHolder rotationPVH =
                PropertyValuesHolder.ofFloat(View.ROTATION_Y, startRotation, endRotation);

        final ObjectAnimator animation =
                ObjectAnimator.ofPropertyValuesHolder(view, rotationPVH);

        animation.setInterpolator(animationBody.getInterpolator());

        return animation;
    }

    public static ObjectAnimator getMorph(@NonNull final AnimationBody animationBody) {
        final float force = animationBody.getForce();

        float[] valuesX = {1f, 1.3f * force, 0.7f, 1.3f * force, 1f, 1f};
        float[] valuesY = {1f, 0.7f, 1.3f * force, 0.7f, 1f, 1f};

        final PropertyValuesHolder scaleXPVH =
                ofKeyframe(View.SCALE_X, getKeyFrames(FRACTIONS, valuesX));

        final PropertyValuesHolder scaleYPVH =
                PropertyValuesHolder.ofKeyframe(View.SCALE_Y, getKeyFrames(FRACTIONS, valuesY));

        final ObjectAnimator animation =
                ObjectAnimator.ofPropertyValuesHolder(animationBody.getView(), scaleXPVH, scaleYPVH);

        animation.setInterpolator(animationBody.getInterpolator());

        return animation;
    }

    @NonNull
    public static ObjectAnimator getSqueeze(@NonNull final AnimationBody animationBody) {
        final float force = animationBody.getForce();

        float[] valuesX = {1f, 1.5f * force, 0.5f, 1.5f * force, 1f, 1f};
        float[] valuesY = {1f, 0.5f, 1f, 0.5f, 1f, 1f};

        final PropertyValuesHolder scaleXPVH =
                PropertyValuesHolder.ofKeyframe(View.SCALE_X, getKeyFrames(FRACTIONS, valuesX));

        final PropertyValuesHolder scaleYPVH =
                PropertyValuesHolder.ofKeyframe(View.SCALE_Y, getKeyFrames(FRACTIONS, valuesY));

        final ObjectAnimator animation =
                ObjectAnimator.ofPropertyValuesHolder(animationBody.getView(), scaleXPVH, scaleYPVH);

        animation.setInterpolator(animationBody.getInterpolator());

        return animation;
    }

    @NonNull
    public static ValueAnimator getFlash(@NonNull final AnimationBody animationBody) {
        final PropertyValuesHolder alphaPVH = PropertyValuesHolder.ofFloat(View.ALPHA, 1f, 0f);
        final ValueAnimator animation =
                ObjectAnimator.ofPropertyValuesHolder(animationBody.getView(), alphaPVH);

        animation.setRepeatCount(3);
        animation.setRepeatMode(ValueAnimator.REVERSE);

        animation.setInterpolator(animationBody.getInterpolator());

        return animation;
    }

    @NonNull
    public static ValueAnimator getSwing(@NonNull final AnimationBody animationBody) {
        final float force = animationBody.getForce();

        float[] values = {
                (float) Math.toDegrees(0),
                (float) Math.toDegrees(0.3f * force),
                (float) Math.toDegrees(-0.3f * force),
                (float) Math.toDegrees(0.3f * force),
                (float) Math.toDegrees(0f),
                (float) Math.toDegrees(0f)
        };

        final PropertyValuesHolder pvhRotation =
                PropertyValuesHolder.ofKeyframe(View.ROTATION, getKeyFrames(FRACTIONS, values));

        final ObjectAnimator animation =
                ObjectAnimator.ofPropertyValuesHolder(animationBody.getView(), pvhRotation);

        animation.setInterpolator(animationBody.getInterpolator());

        return animation;
    }

    @NonNull
    public static AnimatorSet getWobble(@NonNull final AnimationBody animationBody) {
        final AnimatorSet animatorSet = new AnimatorSet();

        final ValueAnimator rotationAnimator = getRotation(animationBody);
        final Animator translationAnimator = getShake(animationBody);

        rotationAnimator.setInterpolator(new LinearInterpolator());

        animatorSet.play(translationAnimator)
                .with(rotationAnimator);

        return animatorSet;
    }

    @NonNull
    public static ValueAnimator getRotation(@NonNull AnimationBody animationBody) {
        final float force = animationBody.getForce();

        float[] rotationValues = {
                (float) Math.toDegrees(0),
                (float) Math.toDegrees(0.3f * force),
                (float) Math.toDegrees(-0.3f * force),
                (float) Math.toDegrees(0.3f * force),
                (float) Math.toDegrees(0f),
                (float) Math.toDegrees(0f)
        };

        final PropertyValuesHolder pvhRotation =
                PropertyValuesHolder.ofKeyframe(View.ROTATION, getKeyFrames(FRACTIONS, rotationValues));

        final ObjectAnimator animation =
                ObjectAnimator.ofPropertyValuesHolder(animationBody.getView(), pvhRotation);

        return animation;
    }

    private static Keyframe[] getKeyFrames(float[] fractions, float[] values) {
        final Keyframe[] keyframes = new Keyframe[fractions.length];
        for (int i = 0; i < fractions.length; i++) {
            keyframes[i] = Keyframe.ofFloat(fractions[i], values[i]);
        }

        return keyframes;
    }

    public static Interpolator getInterpolator(Curve curve) {
        return getInterpolator(curve, 1f);
    }

    public static Interpolator getInterpolator(Curve curve, float force) {

        switch (curve) {
            case EASE_IN:
                return PathInterpolatorCompat.create(0.42f, 0.0f, 1.0f, 1.0f);
            case EASE_OUT:
                return PathInterpolatorCompat.create(0.0f, 0.0f, 0.58f, 1.0f);
            case EASE_IN_OUT:
                return PathInterpolatorCompat.create(0.42f, 0.0f, 0.58f, 1.0f);
            case LINEAR:
                return PathInterpolatorCompat.create(0.0f, 0.0f, 1.0f, 1.0f);
            case SPRING:
                return PathInterpolatorCompat.create(0.5f, 1.1f + force / 3, 1f, 1f);
            case EASE_IN_SINE:
                return PathInterpolatorCompat.create(0.47f, 0f, 0.745f, 0.715f);
            case EASE_OUT_SINE:
                return PathInterpolatorCompat.create(0.39f, 0.575f, 0.565f, 1f);
            case EASE_IN_OUT_SINE:
                return PathInterpolatorCompat.create(0.445f, 0.05f, 0.55f, 0.95f);
            case EASE_IN_QUAD:
                return PathInterpolatorCompat.create(0.55f, 0.085f, 0.68f, 0.53f);
            case EASE_OUT_QUAD:
                return PathInterpolatorCompat.create(0.25f, 0.46f, 0.45f, 0.94f);
            case EASE_IN_OUT_QUAD:
                return PathInterpolatorCompat.create(0.455f, 0.03f, 0.515f, 0.955f);
            case EASE_IN_CUBIC:
                return PathInterpolatorCompat.create(0.55f, 0.055f, 0.675f, 0.19f);
            case EASE_OUT_CUBIC:
                return PathInterpolatorCompat.create(0.215f, 0.61f, 0.355f, 1f);
            case EASE_IN_OUT_CUBIC:
                return PathInterpolatorCompat.create(0.645f, 0.045f, 0.355f, 1f);
            case EASE_IN_QUART:
                return PathInterpolatorCompat.create(0.895f, 0.03f, 0.685f, 0.22f);
            case EASE_OUT_QUART:
                return PathInterpolatorCompat.create(0.165f, 0.84f, 0.44f, 1f);
            case EASE_IN_OUT_QUART:
                return PathInterpolatorCompat.create(0.77f, 0f, 0.175f, 1f);
            case EASE_IN_QUINT:
                return PathInterpolatorCompat.create(0.755f, 0.05f, 0.855f, 0.06f);
            case EASE_OUT_QUINT:
                return PathInterpolatorCompat.create(0.23f, 1f, 0.32f, 1f);
            case EASE_IN_OUT_QUINT:
                return PathInterpolatorCompat.create(0.86f, 0f, 0.07f, 1f);
            case EASE_IN_EXPO:
                return PathInterpolatorCompat.create(0.95f, 0.05f, 0.795f, 0.035f);
            case EASE_OUT_EXPO:
                return PathInterpolatorCompat.create(0.19f, 1f, 0.22f, 1f);
            case EASE_IN_OUT_EXPO:
                return PathInterpolatorCompat.create(1f, 0f, 0f, 1f);
            case EASE_IN_CIRC:
                return PathInterpolatorCompat.create(0.6f, 0.04f, 0.98f, 0.335f);
            case EASE_OUT_CIRC:
                return PathInterpolatorCompat.create(0.075f, 0.82f, 0.165f, 1f);
            case EASE_IN_OUT_CIRC:
                return PathInterpolatorCompat.create(0.785f, 0.135f, 0.15f, 0.86f);
            case EASE_IN_BACK:
                return PathInterpolatorCompat.create(0.6f, -0.28f, 0.735f, 0.045f);
            case EASE_OUT_BACK:
                return PathInterpolatorCompat.create(0.175f, 0.885f, 0.32f, 1.275f);
            case EASE_IN_OUT_BACK:
                return PathInterpolatorCompat.create(0.68f, -0.55f, 0.265f, 1.55f);
            default:
                return PathInterpolatorCompat.create(0.25f, 0.1f, 0.25f, 1.0f);
        }
    }

    public static enum AnimationPreset {
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
        SWING
    }

    public static enum Curve {
        EASE_IN,
        EASE_OUT,
        EASE_IN_OUT,
        LINEAR,
        SPRING,
        EASE_IN_SINE,
        EASE_OUT_SINE,
        EASE_IN_OUT_SINE,
        EASE_IN_QUAD,
        EASE_OUT_QUAD,
        EASE_IN_OUT_QUAD,
        EASE_IN_CUBIC,
        EASE_OUT_CUBIC,
        EASE_IN_OUT_CUBIC,
        EASE_IN_QUART,
        EASE_OUT_QUART,
        EASE_IN_OUT_QUART,
        EASE_IN_QUINT,
        EASE_OUT_QUINT,
        EASE_IN_OUT_QUINT,
        EASE_IN_EXPO,
        EASE_OUT_EXPO,
        EASE_IN_OUT_EXPO,
        EASE_IN_CIRC,
        EASE_OUT_CIRC,
        EASE_IN_OUT_CIRC,
        EASE_IN_BACK,
        EASE_OUT_BACK,
        EASE_IN_OUT_BACK
    }

}
