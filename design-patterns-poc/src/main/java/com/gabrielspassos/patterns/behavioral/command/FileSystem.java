package com.gabrielspassos.patterns.behavioral.command;

public class FileSystem {

    public static FileSystemReceiver getFileSystemReceiver() {
        String osName = System.getProperty("os.name");
        IO.println("Running on OS: " + osName);
        if (osName.contains("Windows")) {
            return new WindowsFileSystemReceiver();
        } else {
            return new UnixFileSystemReceiver();
        }
    }
}
