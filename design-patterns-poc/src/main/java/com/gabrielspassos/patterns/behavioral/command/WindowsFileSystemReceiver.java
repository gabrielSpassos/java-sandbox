package com.gabrielspassos.patterns.behavioral.command;

public class WindowsFileSystemReceiver implements FileSystemReceiver {

    @Override
    public boolean openFile() {
        IO.println("Open file on Windows OS");
        return true;
    }

    @Override
    public boolean writeFile() {
        IO.println("Write file on Windows OS");
        return true;
    }

    @Override
    public boolean closeFile() {
        IO.println("Close file on Windows OS");
        return true;
    }

}
