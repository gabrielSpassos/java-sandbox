package com.gabrielspassos.poc.utils;

import com.google.common.base.Preconditions;

public class PreconditionsUtils {

    public void checkIntegerInputBiggerThanZero(Integer input) {
        var errorMessage = "Invalid input: %s. Input must be bigger than zero.";
        Preconditions.checkArgument(input > 0, errorMessage, input);
    }


}
