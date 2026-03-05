package com.gabrielspassos.patterns.behavioral.command;

public class UnixFileSystemReceiver  implements FileSystemReceiver {

    @Override
    public boolean openFile() {
        IO.println("Open file on Unix OS");
        return true;
    }

    @Override
    public boolean writeFile() {
        IO.println("Write file on Unix OS");
        return true;
    }

    @Override
    public boolean closeFile() {
        IO.println("Close file on Unix OS");
        return true;
    }

}
