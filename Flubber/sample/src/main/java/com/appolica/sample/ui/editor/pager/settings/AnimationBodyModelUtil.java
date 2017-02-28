package com.appolica.sample.ui.editor.pager.settings;

import com.appolica.flubber.AnimationBody;
import com.appolica.sample.utils.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AnimationBodyModelUtil {

    public enum FieldConfiguration {
        //                   0.1   5.0
        DURATION("duration", 0.1f, 5f, 1000f),
        DELAY("delay", 0f, 5f, 1000f),
        FORCE("force", 1f, 5f, 1f),
        VELOCITY("velocity", 0f, 1f, 1f),
        DAMPING("damping", 0f, 1f, 1f),
        SCALE("scaleX", 0f, 1f, 1f);

        private final String name;
        private float min;
        private float max;
        private float factor;

        FieldConfiguration(String name, float min, float max, float factor) {
            this.name = name;
            this.min = min;
            this.max = max;
            this.factor = factor;
        }

        public String getName() {
            return name;
        }

        public float getMin() {
            return min;
        }

        public float getMax() {
            return max;
        }

        public float getFactor() {
            return factor;
        }

        public FieldConfiguration forFieldName(String name) {
            return FieldConfiguration.valueOf(name.toUpperCase());
        }
    }

    public static List<SeekBarModel> generateFor(AnimationBody body) {
        final List<SeekBarModel> data = new ArrayList<>();

        try {
            final FieldConfiguration[] fieldConfigurations = FieldConfiguration.values();
            for (int i = 0; i < fieldConfigurations.length; i++) {

                final FieldConfiguration config = fieldConfigurations[i];
                final SeekBarModel model = mapFieldToModel(body, config);

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

    public static SeekBarModel mapFieldToModel(AnimationBody body, FieldConfiguration config)
            throws NoSuchFieldException,
            NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException {

        final Field field = AnimationBody.class.getDeclaredField(config.getName());
        final String getterName = "get" + StringUtils.getCapitalizedString(config.getName());

        final Method getter = AnimationBody.class.getDeclaredMethod(getterName);
        final String fieldType = field.getType().getName();

        Float value = null;
        if (fieldType.equals(long.class.getName())) {
            value = (Long) getter.invoke(body) / config.getFactor();
        } else if (fieldType.equals(float.class.getName())) {
            value = (Float) getter.invoke(body) / config.getFactor();
        }

        return SeekBarModel.create(config.getName(), value, config.getMin(), config.getMax());
    }

    public static void initFieldFromModel(SeekBarModel model, AnimationBody animationBody) {

    }
}
