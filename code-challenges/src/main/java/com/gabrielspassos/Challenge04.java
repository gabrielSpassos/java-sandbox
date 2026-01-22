package com.gabrielspassos;

/*
Given the following countries and languages:
Usa -> English
Brazil -> Portuguese
Spain -> Spanish
Italy -> Italian
France -> French
Germany -> German
Create a function that can return the language for a given country. You cannot use a hashmap or dictionary.
pattern_matcher("Usa") -> "English"
Refactor the code, can you do that without using IF statements?
pattern_matcher("Usa") -> "English"
*/
public class Challenge04 {

    public static String patternMatcher(String country) {
        return switch (country) {
            case "Usa" -> "English";
            case "Brazil" -> "Portuguese";
            case "Spain" -> "Spanish";
            case "Italy" -> "Italian";
            case "France" -> "French";
            case "Germany" -> "German";
            default -> throw new IllegalArgumentException("invalid country");
        };
    }

}
