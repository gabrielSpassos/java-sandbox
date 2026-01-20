package com.gabrielspassos;

/*
Revert String

Create a function that can revert a string.
revert("Hello") -> "olleH"
*/
public class Challenge01 {

    public static String revert(String str) {
        StringBuilder reverted = new StringBuilder();
        for (int i = str.length(); i > 0; i--) {
            reverted.append(str.charAt(i-1));
        }
        return reverted.toString();
    }

}
