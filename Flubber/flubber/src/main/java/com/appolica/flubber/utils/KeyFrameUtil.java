package com.appolica.flubber.utils;

import android.animation.Keyframe;

public class KeyFrameUtil {
    public static Keyframe[] getKeyFrames(float[] fractions, float[] values) {
        final Keyframe[] keyframes = new Keyframe[fractions.length];
        for (int i = 0; i < fractions.length; i++) {
            keyframes[i] = Keyframe.ofFloat(fractions[i], values[i]);
        }

        return keyframes;
    }
}
