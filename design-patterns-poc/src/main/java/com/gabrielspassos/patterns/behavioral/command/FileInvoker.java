package com.gabrielspassos.patterns.behavioral.command;

public class FileInvoker {

    private final Command command;

    public FileInvoker(Command command) {
        this.command = command;
    }

    public boolean execute() {
        return this.command.execute();
    }
}
