package com.appolica.flubber.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

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

    public static DisplayMetrics getDisplayMetrics(Context context) {
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        final WindowManager wm =
                (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        final Display display = wm.getDefaultDisplay();
        display.getMetrics(displayMetrics);

        return displayMetrics;
    }
}
