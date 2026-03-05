package com.gabrielspassos.patterns.behavioral.command;

public class CloseFileCommand implements Command {

    private final FileSystemReceiver fileSystemReceiver;

    public CloseFileCommand(FileSystemReceiver fileSystemReceiver) {
        this.fileSystemReceiver = fileSystemReceiver;
    }

    @Override
    public boolean execute() {
        return this.fileSystemReceiver.closeFile();
    }

}
