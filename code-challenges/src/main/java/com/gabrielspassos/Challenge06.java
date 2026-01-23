package com.gabrielspassos;

import java.util.ArrayList;
import java.util.List;

/*
Create a function that can tokenize a string based on a token.
tokenize("Hello,World,How,Are,You", ",") -> ["Hello", "World", "How", "Are", "You"]
tokenize("Hello World How Are You", " ") -> ["Hello", "World", "How", "Are", "You"]
tokenize("Hello-World-How-Are-You", "-") -> ["Hello", "World", "How", "Are", "You"]
Can you refactor your code and do that without using any prebuild function like split?
*/
public class Challenge06 {

    public static List<String> tokenize(String phrase, String tokenToSplit) {
        var result = new ArrayList<String>();
        var word = new StringBuilder();

        for (int i = 0; i < phrase.length(); i++) {
            var character = String.valueOf(phrase.charAt(i));

            if (tokenToSplit.equals(character)) {
                result.add(word.toString());
                word.delete(0, word.length());
            } else {
                word.append(character);
            }
        }

        result.add(word.toString());

        return result;
    }

}
