package com.appolica.sample.utils;

import android.support.annotation.NonNull;

import com.appolica.sample.ui.editor.pager.RadioElementModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Utils {
    public static List<RadioElementModel> convertMapToData(Map<String, ?> animationNamesMap) {
        final Set<String> names = animationNamesMap.keySet();
        final List<RadioElementModel> data = new ArrayList<>();

        for (String name : names) {
            final RadioElementModel element = createElementFor(name);

            data.add(element);
        }

        return data;
    }

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

}
