package com.appolica.flubber.utils;

import android.content.res.Resources;

public class DimensionUtils {

    public static float dp2px(int dp) {
        return dp * getDensity();
    }

    public static float px2dp(int px) {
        return px / getDensity();
    }

    private static float getDensity() {
        return Resources.getSystem().getDisplayMetrics().density;
    }
}
