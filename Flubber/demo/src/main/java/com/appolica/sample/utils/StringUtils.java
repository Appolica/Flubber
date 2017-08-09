package com.appolica.sample.utils;

import android.support.annotation.NonNull;

import java.util.LinkedHashMap;
import java.util.Map;

public class StringUtils {
    @NonNull
    public static String upperUnderScoreToCamel(String input) {
        final StringBuilder resultBuilder = new StringBuilder();
        final String[] tokens = input.split("_");

        for (int i = 0; i < tokens.length; i++) {
            final char[] chars = tokens[i].toLowerCase().toCharArray();
            chars[0] = Character.toUpperCase(chars[0]);
            resultBuilder.append(chars);
        }

        return resultBuilder.toString();
    }

    public static <T extends Enum> Map<String, T> normalizedNameMapFor(Class<T> clazz) {
        final Map<String, T> resultMap = new LinkedHashMap<>();

        final T[] enumConstants = clazz.getEnumConstants();
        for (int i = 0; i < enumConstants.length; i++) {
            final String name = enumConstants[i].name();
            resultMap.put(upperUnderScoreToCamel(name), enumConstants[i]);
        }

        return resultMap;
    }

    public static <T extends Enum> String getNormalizedEnumName(T enumVar) {
        final String name = enumVar.name();
        final String normalizedName = StringUtils.upperUnderScoreToCamel(name);

        return normalizedName;
    }
}
