package com.gabrielspassos;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Challenge02Test {

    @Test
    void revertList() {
        var list = List.of(1,2,3,4,5);

        var reverted = Challenge02.revert(list);

        assertEquals(List.of(5, 4, 3, 2, 1), reverted);
    }

}