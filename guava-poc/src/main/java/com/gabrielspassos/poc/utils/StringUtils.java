package com.gabrielspassos.poc.utils;

import com.google.common.base.CharMatcher;

public class StringUtils {

    public String removeSpecialChars(String input) {
        CharMatcher matcher = CharMatcher.javaLetterOrDigit();
        return matcher.retainFrom(input);
    }
}
