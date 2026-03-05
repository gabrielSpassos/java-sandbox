package com.gabrielspassos.patterns.behavioral.command;

public class WriteFileCommand implements Command {

    private final FileSystemReceiver fileSystemReceiver;

    public WriteFileCommand(FileSystemReceiver fileSystemReceiver) {
        this.fileSystemReceiver = fileSystemReceiver;
    }

    @Override
    public boolean execute() {
        return this.fileSystemReceiver.writeFile();
    }

}
