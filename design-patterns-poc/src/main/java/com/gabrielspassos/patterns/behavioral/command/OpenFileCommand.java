package com.gabrielspassos.patterns.behavioral.command;

public class OpenFileCommand implements Command {

    private final FileSystemReceiver fileSystemReceiver;

    public OpenFileCommand(FileSystemReceiver fileSystemReceiver) {
        this.fileSystemReceiver = fileSystemReceiver;
    }

    @Override
    public boolean execute() {
        return this.fileSystemReceiver.openFile();
    }

}
