package com.gabrielspassos;

/*
Create a function that can replace a given token in a string.

replace("Hello,World,How,Are,You", ",", "-") -> "Hello-World-How-Are-You"

The replace function should receive a string, a token to be replaced and a new token. Refactor the code to not only replace one token but a string that you can recive by parameter.

replace("Hello,World,How,Are,You", ",World,", "-") -> "Hello-How,Are,You"

Now refactor the code and do not use any prebuild function.
*/
public class Challenge11 {

    public static String replace(String input, String tokenToBeReplaced, String replaceToken) {
        var result = new StringBuilder();

        var i = 0;
        while (i < input.length()) {
            if (matches(input, tokenToBeReplaced, i)) {
                result.append(replaceToken);
                i += tokenToBeReplaced.length();
            } else {
                result.append(input.charAt(i));
                i++;
            }
        }
        return result.toString();
    }

    private static boolean matches(String input, String tokenToBeReplaced, int start) {
        if (start + tokenToBeReplaced.length() > input.length()) {
            return false;
        }

        for (int i = 0; i < tokenToBeReplaced.length(); i++) {
            if (input.charAt(start + i) != tokenToBeReplaced.charAt(i)) {
                return false;
            }
        }

        return true;
    }
}
