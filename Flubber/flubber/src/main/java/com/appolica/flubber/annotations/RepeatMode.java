package com.appolica.flubber.annotations;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static android.animation.ValueAnimator.RESTART;
import static android.animation.ValueAnimator.REVERSE;

@IntDef({RESTART, REVERSE})
@Retention(RetentionPolicy.SOURCE)
public @interface RepeatMode {
}
