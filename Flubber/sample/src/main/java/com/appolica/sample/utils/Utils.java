package com.appolica.sample.utils;

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
            final RadioElementModel element = new RadioElementModel();
            element.getName().set(name);
            element.getChecked().set(false);

            data.add(element);
        }

        data.get(0).getChecked().set(true);

        return data;
    }
}
