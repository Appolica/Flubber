package com.appolica.sample.utils;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.appolica.flubber.Flubber;
import com.appolica.sample.ui.animation.CustomAnimationBody;
import com.appolica.sample.ui.editor.pager.RadioElementModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Utils {
    public static final String ANIMATION_BODY = "AnimationBody";
    public static final String ANIMATION_PRESET = "AnimationPreset";
    public static final String INTERPOLATOR = "Interpolator";

    public static List<RadioElementModel> convertMapToData(Map<String, ?> animationNamesMap) {
        final Set<String> names = animationNamesMap.keySet();
        final List<RadioElementModel> data = new ArrayList<>();

        for (String name : names) {
            final RadioElementModel element = createElementFor(name);

            data.add(element);
        }

        return data;
    }

    @NonNull
    public static<T extends Enum> RadioElementModel convertEnumToModel(T value) {
        final String name = StringUtils.upperUnderScoreToCamel(value.name());
        return createElementFor(name);
    }

    @NonNull
    public static RadioElementModel createElementFor(String name) {
        final RadioElementModel element = new RadioElementModel();
        element.getName().set(name);
        element.getChecked().set(false);

        return element;
    }

    @NonNull
    public static Bundle createAnimationBodyBundle(CustomAnimationBody animationBody) {
        final Bundle animationBodyBundle = new Bundle();
        animationBodyBundle.putSerializable(ANIMATION_BODY, animationBody);

        final Flubber.AnimationPreset animationPreset =
                Flubber.AnimationPreset.valueFor(((Flubber.AnimationPreset) animationBody.getAnimation()).getProvider());
        final Flubber.Curve interpolator =
                Flubber.Curve.valueFor(((Flubber.Curve) animationBody.getInterpolator()).getProvider());

        animationBodyBundle.putSerializable(ANIMATION_PRESET, animationPreset);
        animationBodyBundle.putSerializable(INTERPOLATOR, interpolator);

        return animationBodyBundle;
    }

    public static CustomAnimationBody restoreAnimationBody(@NonNull Bundle savedInstanceState) {
        final CustomAnimationBody animationBody = (CustomAnimationBody) savedInstanceState.getSerializable(ANIMATION_BODY);
        final Flubber.AnimationPreset animationPreset = (Flubber.AnimationPreset) savedInstanceState.getSerializable(ANIMATION_PRESET);

        animationBody.init();
        animationBody.setAnimation(animationPreset);
        animationBody.setInterpolator((Flubber.Curve) savedInstanceState.getSerializable(INTERPOLATOR));

        return animationBody;
    }
}