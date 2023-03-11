package com.gabrielspassos.poc.services;

import com.google.common.base.Preconditions;

public class PreconditionsService {

    public void checkIntegerInputBiggerThanZero(Integer input) {
        var errorMessage = "Invalid input: %s. Input must be bigger than zero.";
        Preconditions.checkArgument(input > 0, errorMessage, input);
    }


}
