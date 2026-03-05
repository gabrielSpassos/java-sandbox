package com.gabrielspassos.patterns.behavioral.command;

public interface FileSystemReceiver {

    boolean openFile();
    boolean writeFile();
    boolean closeFile();

}
