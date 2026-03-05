package com.gabrielspassos.patterns.behavioral.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

    @Test
    void shouldExecuteCommands() {
        FileSystemReceiver fileSystemReceiver = FileSystem.getFileSystemReceiver();

        OpenFileCommand openFileCommand = new OpenFileCommand(fileSystemReceiver);
        FileInvoker fileInvoker = new FileInvoker(openFileCommand);
        assertTrue(fileInvoker.execute());

        WriteFileCommand writeFileCommand = new WriteFileCommand(fileSystemReceiver);
        fileInvoker = new FileInvoker(writeFileCommand);
        assertTrue(fileInvoker.execute());

        CloseFileCommand closeFileCommand = new CloseFileCommand(fileSystemReceiver);
        fileInvoker = new FileInvoker(closeFileCommand);
        assertTrue(fileInvoker.execute());
    }

}