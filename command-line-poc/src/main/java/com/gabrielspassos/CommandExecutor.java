package com.gabrielspassos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandExecutor {

    public CommandResult execute(String command) {
        ProcessBuilder processBuilder = new ProcessBuilder();

        // Use bash to allow full shell features
        processBuilder.command("/bin/bash", "-c", command);

        Process process;
        try {
            process = processBuilder.start();
        } catch (IOException e) {
            throw new RuntimeException("Failed to start process", e);
        }

        String stdout;
        String stderr;

        try (
                BufferedReader outReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                BufferedReader errReader = new BufferedReader(new InputStreamReader(process.getErrorStream()))
        ) {
            stdout = outReader.lines().reduce("", (a, b) -> a + b + "\n");
            stderr = errReader.lines().reduce("", (a, b) -> a + b + "\n");
        } catch (IOException e) {
            throw new RuntimeException("Error reading process output", e);
        }

        int exitCode;
        try {
            exitCode = process.waitFor();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Process interrupted", e);
        }

        return new CommandResult(exitCode, stdout, stderr);
    }
}