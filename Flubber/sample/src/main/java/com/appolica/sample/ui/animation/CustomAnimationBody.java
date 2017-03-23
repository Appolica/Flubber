package com.appolica.sample.ui.animation;

import android.support.annotation.Nullable;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.appolica.flubber.AnimationBody;
import com.appolica.sample.R;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * This class extends the library's {@link AnimationBody} class in order to adapt it to the sample.
 * It does NOT in any way extend the library's functionality.
 */
public class CustomAnimationBody extends AnimationBody implements Serializable {

    private transient Map<FieldName, PropertyMethodsHolder<Long>> longProperties = new HashMap<>();
    private transient Map<FieldName, PropertyMethodsHolder<Float>> floatProperties = new HashMap<>();
    private transient Map<Class<?>, Map<FieldName, ? extends PropertyMethodsHolder<?>>> typesMap = new HashMap<>();

    public CustomAnimationBody() {
        longProperties.put(FieldName.DURATION, PropertyMethodsHolder.fields(this::getDuration, this::setDuration));
        longProperties.put(FieldName.DELAY, PropertyMethodsHolder.fields(this::getDelay, this::setDelay));

        floatProperties.put(FieldName.FORCE, PropertyMethodsHolder.fields(this::getForce, this::setForce));
        floatProperties.put(FieldName.VELOCITY, PropertyMethodsHolder.fields(this::getVelocity, this::setVelocity));
        floatProperties.put(FieldName.DAMPING, PropertyMethodsHolder.fields(this::getDamping, this::setDamping));
        floatProperties.put(FieldName.SCALE, PropertyMethodsHolder.fields(
                this::getEndScaleX,
                (scale) -> {
                    setEndScaleX(scale);
                    setEndScaleY(scale);
                }));

        typesMap.put(Long.class, longProperties);
        typesMap.put(Float.class, floatProperties);
    }

    public void setProperty(FieldName name, long value) {
        final PropertyMethodsHolder<Long> methodsHolder = longProperties.get(name);
        final PropertyMethodsHolder.Setter<Long> setter = methodsHolder.setter();

        setter.set(value);
    }

    public void setProperty(FieldName name, float value) {
        final PropertyMethodsHolder<Float> methodsHolder = floatProperties.get(name);
        final PropertyMethodsHolder.Setter<Float> setter = methodsHolder.setter();

        setter.set(value);
    }

    public <T extends Number> T getPropertyValue(FieldName name, Class<?> numberClass) {
        if (!typesMap.containsKey(numberClass)) {
            throw new IllegalArgumentException("There is no property of type: " + numberClass);
        }

        final Map<FieldName, ? extends PropertyMethodsHolder<?>> propertiesMap = typesMap.get(numberClass);
        final PropertyMethodsHolder<?> holder = propertiesMap.get(name);
        final PropertyMethodsHolder.Getter<?> getter = holder.getter();

        return (T) getter.get();
    }

    public Map<FieldName, PropertyMethodsHolder<Long>> getLongProperties() {
        return longProperties;
    }

    public Map<FieldName, PropertyMethodsHolder<Float>> getFloatProperties() {
        return floatProperties;
    }

    public enum FieldName {
        DURATION(R.string.duration),
        DELAY(R.string.delay),
        FORCE(R.string.force),
        VELOCITY(R.string.velocity),
        DAMPING(R.string.damping),
        SCALE(R.string.scale);

        private static final Map<Integer, FieldName> idNamesMap;

        static {
            idNamesMap =
                    Stream.of(FieldName.values())
                            .collect(Collectors.toMap((name -> name.stringId), (name -> name)));
        }

        private final int stringId;

        FieldName(int stringId) {
            this.stringId = stringId;
        }

        @Nullable
        public static FieldName forStringId(int stringId) {
            return idNamesMap.get(stringId);
        }

        public int getStringId() {
            return stringId;
        }
    }
}
