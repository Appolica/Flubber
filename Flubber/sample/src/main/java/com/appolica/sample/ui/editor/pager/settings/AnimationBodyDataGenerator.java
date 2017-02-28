package com.appolica.sample.ui.editor.pager.settings;

import com.appolica.flubber.AnimationBody;
import com.appolica.sample.utils.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AnimationBodyDataGenerator {

    public enum FieldConfiguration {
        DURATION("duration", 100L, 5000L),
        DELAY("delay", 0L, 5000L),
        FORCE("force", 1f, 5f),
        VELOCITY("velocity", 0f, 1f),
        DAMPING("damping", 0f, 1f),
        SCALE("scaleX", 0f, 1f);

        private final String name;
        private Number min;
        private Number max;

        FieldConfiguration(String name, Number min, Number max) {
            this.name = name;
            this.min = min;
            this.max = max;
        }
    }

    public static List<SeekBarModel> generateFor(AnimationBody body) {
        final List<SeekBarModel> data = new ArrayList<>();

        try {

            final FieldConfiguration[] fieldConfigurations = FieldConfiguration.values();
            for (int i = 0; i < fieldConfigurations.length; i++) {
                FieldConfiguration config = fieldConfigurations[i];

                final Field field = AnimationBody.class.getDeclaredField(config.name);
                final String getterName = "get" + StringUtils.getCapitalizedString(config.name);

                final Method getter = AnimationBody.class.getDeclaredMethod(getterName);
                final String fieldType = field.getType().getName();

                Number value = null;
                if (fieldType.equals(long.class.getName())) {
                    value = (Long) getter.invoke(body);

                } else if (fieldType.equals(float.class.getName())) {
                    value = (Float) getter.invoke(body);
                }

                final SeekBarModel model =
                        SeekBarModel.create(config.name, value, config.min, config.max);

                data.add(model);
            }


        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return data;
    }
}
