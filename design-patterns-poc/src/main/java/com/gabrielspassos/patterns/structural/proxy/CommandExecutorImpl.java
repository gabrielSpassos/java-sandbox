package com.gabrielspassos.patterns.structural.proxy;

class CommandExecutorImpl implements CommandExecutor {

    @Override
    public boolean runCommand(String cmd) throws Exception {
        var command = cmd.split(" ");
        Process process = new ProcessBuilder(command).start();
        process.waitFor();
        return process.exitValue() == 0;
    }

}
