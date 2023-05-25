package com.gabrielspassos.poc.memory.utils;

import java.util.Objects;

public class StringUtils {

    public static String capitalize(String stringToCapitalize) {
        if (Objects.isNull(stringToCapitalize) || "".equals(stringToCapitalize)) {
            return stringToCapitalize;
        }

        if (stringToCapitalize.length() == 1) {
            return stringToCapitalize.toUpperCase();
        }

        return stringToCapitalize.substring(0, 1).toUpperCase() + stringToCapitalize.substring(1);
    }

}
