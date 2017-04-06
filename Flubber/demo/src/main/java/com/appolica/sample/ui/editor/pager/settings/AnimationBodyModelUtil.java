package com.appolica.sample.ui.editor.pager.settings;

import android.support.annotation.Nullable;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.appolica.sample.ui.animation.CustomAnimationBody;

import java.util.List;
import java.util.Map;

public class AnimationBodyModelUtil {

    public static List<SeekBarModel> generateFor(CustomAnimationBody body) {
        final FieldConfiguration[] fieldConfigurations = FieldConfiguration.values();

        return Stream.of(fieldConfigurations)
                .map(config -> mapFieldToModel(body, config))
                .collect(Collectors.toList());
    }

    public static SeekBarModel mapFieldToModel(CustomAnimationBody body, FieldConfiguration config) {

        final Float value = AnimationBodyModelUtil.getValue(config, body).floatValue() / config.getFactor();
        final int stringId = config.getName().getStringId();

        return SeekBarModel.create(stringId, value, config.getMin(), config.getMax());
    }

    public static <T extends Number> T getValue(FieldConfiguration fieldConfiguration, CustomAnimationBody animationBody) {
        return animationBody.getPropertyValue(fieldConfiguration.getName(), fieldConfiguration.getFieldClass());
    }

    public static void initFieldFromModel(CustomAnimationBody animationBody, SeekBarModel model) {
        final CustomAnimationBody.FieldName fieldName = CustomAnimationBody.FieldName.forStringId(model.getName().get());
        final FieldConfiguration config = FieldConfiguration.forFieldName(fieldName);

        final float floatValue = model.getValue().get() * config.getFactor();

        if (config.fieldClass == Long.class) {
            animationBody.setProperty(fieldName, (long) floatValue);
        } else if (config.fieldClass == Float.class) {
            animationBody.setProperty(fieldName, floatValue);
        }
    }

    public enum FieldConfiguration {
        DURATION(CustomAnimationBody.FieldName.DURATION, 0.1f, 5f, 1000f, Long.class),
        DELAY(CustomAnimationBody.FieldName.DELAY, 0f, 5f, 1000f, Long.class),
        FORCE(CustomAnimationBody.FieldName.FORCE, 1f, 5f, 1f, Float.class),
        VELOCITY(CustomAnimationBody.FieldName.VELOCITY, 0f, 1f, 1f, Float.class),
        DAMPING(CustomAnimationBody.FieldName.DAMPING, 0f, 1f, 1f, Float.class),
        SCALE(CustomAnimationBody.FieldName.SCALE, 0f, 5f, 1f, Float.class);

        private static final Map<CustomAnimationBody.FieldName, FieldConfiguration> nameConfigMap;

        static {
            nameConfigMap =
                    Stream.of(FieldConfiguration.values())
                    .collect(Collectors.toMap(FieldConfiguration::getName, config -> config));
        }

        private final CustomAnimationBody.FieldName name;
        private final float min;
        private final float max;
        private final float factor;
        private final Class<? extends Number> fieldClass;

        FieldConfiguration(CustomAnimationBody.FieldName name,
                           float min,
                           float max,
                           float factor,
                           Class<? extends Number> fieldClass) {

            this.name = name;
            this.min = min;
            this.max = max;
            this.factor = factor;
            this.fieldClass = fieldClass;
        }

        @Nullable
        public static FieldConfiguration forFieldName(CustomAnimationBody.FieldName name) {
            return nameConfigMap.get(name);
        }

        public CustomAnimationBody.FieldName getName() {
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

        public Class<? extends Number> getFieldClass() {
            return fieldClass;
        }

    }
}
