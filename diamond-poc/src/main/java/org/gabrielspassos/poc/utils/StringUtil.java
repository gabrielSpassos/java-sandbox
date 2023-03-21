package org.gabrielspassos.poc.utils;

public class StringUtil {

    public String includeBeforeAndAfter(String stringToUpdate, String stringToIncludeBefore, String stringToIncludeAfter) {
        stringToUpdate = stringToIncludeBefore + stringToUpdate;
        stringToUpdate = stringToUpdate + stringToIncludeAfter;
        return stringToUpdate;
    }

    public String repeat(String valueToRepeat, Integer repeatCount) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < repeatCount; i++) {
            stringBuilder.append(valueToRepeat);
        }
        return stringBuilder.toString();
    }
}
