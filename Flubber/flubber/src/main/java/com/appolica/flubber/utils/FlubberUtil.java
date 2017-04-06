package com.appolica.flubber.utils;

import android.view.View;

public class FlubberUtil {
    public static void clearAnimation(View view) {
        view.setAlpha(1);
        view.setScaleX(1);
        view.setScaleY(1);
        view.setTranslationX(0);
        view.setTranslationY(0);
        view.setRotation(0);
        view.setRotationX(0);
        view.setRotationY(0);
    }
}
