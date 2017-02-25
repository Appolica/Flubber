package com.appolica.sample.ui.editor.pager.settings;

import com.appolica.flubber.AnimationBody;
import com.appolica.sample.utils.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AnimationBodyDataGenerator {

    private static final List<FieldConfiguration> fieldsConfig;

    static {
        fieldsConfig = new ArrayList<>();

        fieldsConfig.add(FieldConfiguration.create("duration", 100L, 5000L));
        fieldsConfig.add(FieldConfiguration.create("delay", 0L, 5000L));
        fieldsConfig.add(FieldConfiguration.create("force", 1f, 5f));
        fieldsConfig.add(FieldConfiguration.create("velocity", 0f, 1f));
        fieldsConfig.add(FieldConfiguration.create("damping", 0f, 1f));
        fieldsConfig.add(FieldConfiguration.create("scale", 0f, 1f));
    }

    private static class FieldConfiguration<ValueT extends Number> {
        private String name;
        private ValueT minValue;
        private ValueT maxValue;

        public static<ValueT extends Number> FieldConfiguration<ValueT> create(String name, ValueT minValue, ValueT maxValue) {
            return new FieldConfiguration<>(name, minValue, maxValue);
        }

        public FieldConfiguration(String name, ValueT minValue, ValueT maxValue) {
            this.name = name;
            this.minValue = minValue;
            this.maxValue = maxValue;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ValueT getMinValue() {
            return minValue;
        }

        public void setMinValue(ValueT minValue) {
            this.minValue = minValue;
        }

        public ValueT getMaxValue() {
            return maxValue;
        }

        public void setMaxValue(ValueT maxValue) {
            this.maxValue = maxValue;
        }
    }

    public static List<SeekBarModel> generateFor(AnimationBody body) {
        final List<SeekBarModel> data = new ArrayList<>();

        for (FieldConfiguration config : fieldsConfig) {

            try {
                String fieldName = config.getName();
                if (fieldName.equals("scale")) {
                    fieldName = fieldName.concat("X");
                }

                final Field field = AnimationBody.class.getDeclaredField(fieldName);
                final String getterName = "get" + StringUtils.getCapitalizedString(fieldName);

                final Method getter = AnimationBody.class.getDeclaredMethod(getterName);
                final String fieldType = field.getType().getName();

                Number value = null;
                if (fieldType.equals(long.class.getName())) {
                    value = (Long) getter.invoke(body);

                } else if (fieldType.equals(float.class.getName())) {
                    value = (Float) getter.invoke(body);
                }

                final SeekBarModel model =
                        SeekBarModel.create(config.getName(), value, config.minValue, config.maxValue);

                data.add(model);

            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }

        return data;
    }
}
