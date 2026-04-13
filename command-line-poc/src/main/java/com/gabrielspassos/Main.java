package com.gabrielspassos;

public class Main {
    static void main() {
        IO.println("Command Line POC!");

        CommandExecutor commandExecutor = new CommandExecutor();

        String command = "ls -la";

        CommandResult commandResult = commandExecutor.execute(command);

        IO.println("Exit Code: " + commandResult.exitCode());
        IO.println("STDOUT:\n" + commandResult.stdout());
        IO.println("STDERR:\n" + commandResult.stderr());
    }
}
