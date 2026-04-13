package com.gabrielspassos;

public record CommandResult (
        int exitCode,
        String stdout,
        String stderr
){}
