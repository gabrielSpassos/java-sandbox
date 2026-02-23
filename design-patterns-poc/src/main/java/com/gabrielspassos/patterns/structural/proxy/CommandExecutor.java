package com.gabrielspassos.patterns.structural.proxy;

public interface CommandExecutor {

    boolean runCommand(String cmd) throws Exception;
}
