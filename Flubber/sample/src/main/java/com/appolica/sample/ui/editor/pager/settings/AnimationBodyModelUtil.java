package com.appolica.sample.ui.editor.pager.settings;

import com.appolica.flubber.AnimationBody;
import com.appolica.sample.utils.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AnimationBodyModelUtil {

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
        final Method getter = getGetter(config);
        final String fieldType = field.getType().getName();

        Float value = null;
        if (fieldType.equals(long.class.getName())) {
            value = (Long) getter.invoke(body) / config.getFactor();
        } else if (fieldType.equals(float.class.getName())) {
            value = (Float) getter.invoke(body) / config.getFactor();
        }

        return SeekBarModel.create(config.getName(), value, config.getMin(), config.getMax());
    }

    private static Method getGetter(FieldConfiguration config) throws NoSuchMethodException {
        final String getterName = "get" + StringUtils.getCapitalizedString(config.getName());

        return AnimationBody.class.getDeclaredMethod(getterName);
    }

    private static Method getSetter(FieldConfiguration config, Class type) throws NoSuchMethodException {
        final String getterName = "set"  + StringUtils.getCapitalizedString(config.getName());

        return AnimationBody.class.getDeclaredMethod(getterName, type);
    }

    public static void initFieldFromModel(SeekBarModel model, AnimationBody animationBody) {
        final String fieldName = model.getName().get();
        final FieldConfiguration configuration = FieldConfiguration.forFieldName(fieldName.replace("endScaleX", "scale"));

        try {
            final Field field = AnimationBody.class.getDeclaredField(fieldName);
            final String fieldType = field.getType().getName();

            final Method setter;
            if (fieldType.equals(long.class.getName())) {
                 setter = getSetter(configuration, long.class);
                setter.invoke(animationBody, (long) (model.getValue().get() * configuration.getFactor()));
            } else if (fieldType.equals(float.class.getName())) {
                setter = getSetter(configuration, float.class);
                setter.invoke(animationBody, model.getValue().get() * configuration.getFactor());
            }

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public enum FieldConfiguration {
        //                   0.1   5.0
        DURATION("duration", 0.1f, 5f, 1000f),
        DELAY("delay", 0f, 5f, 1000f),
        FORCE("force", 1f, 5f, 1f),
        VELOCITY("velocity", 0f, 1f, 1f),
        DAMPING("damping", 0f, 1f, 1f),
        SCALE("endScaleX", 0f, 5f, 1f);

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

        public static FieldConfiguration forFieldName(String name) {
            return FieldConfiguration.valueOf(name.toUpperCase());
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
    }
}
