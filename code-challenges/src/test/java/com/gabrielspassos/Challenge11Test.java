package com.gabrielspassos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Challenge11Test {

    @Test
    void shouldReplaceOneToken() {
        var expected = "Hello-World-How-Are-You";

        var result = Challenge11.replace("Hello,World,How,Are,You", ",", "-");

        assertEquals(expected, result);
    }

    @Test
    void shouldReplace() {
        var expected = "Hello-How,Are,You";

        var result = Challenge11.replace("Hello,World,How,Are,You", ",World,", "-");

        assertEquals(expected, result);
    }

}