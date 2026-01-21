package com.gabrielspassos;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/*
Create a function that perform a lookup in a map for a given key you should have id, name.
lookup(1) -> "John"
Refactor the code so you can lookup for email as well and get the name and vice versa.
lookup("John") -> "john@john.jhon.com"
lookup("john@john.jhon.com") -> "John"
*/
public class Challenge03 {

    private static final HashMap<String, String> KEY_NAME_MAP = new HashMap<>();
    private static final HashMap<String, String> EMAIL_NAME_MAP = new HashMap<>();

    static {
        KEY_NAME_MAP.put("1", "John");
        KEY_NAME_MAP.put("2", "Gabriel");

        EMAIL_NAME_MAP.put("john@john.jhon.com", "John");
        EMAIL_NAME_MAP.put("gabriel@gabriel.gabriel.com", "Gabriel");
    }

    public static Optional<String> lookupById(String key) {
        return Optional.ofNullable(KEY_NAME_MAP.get(key));
    }

    public static Optional<String> lookupByEmailOrName(String emailOrName) {
        return Optional.ofNullable(EMAIL_NAME_MAP.get(emailOrName))
                .or(() -> lookupByName(emailOrName));
    }

    private static Optional<String> lookupByName(String name) {
        return EMAIL_NAME_MAP.entrySet().stream()
                .filter(entry -> entry.getValue().equals(name))
                .map(Map.Entry::getKey)
                .findFirst();
    }
}
