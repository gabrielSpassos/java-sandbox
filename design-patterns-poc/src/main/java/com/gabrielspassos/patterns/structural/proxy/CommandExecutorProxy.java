package com.gabrielspassos.patterns.structural.proxy;

public class CommandExecutorProxy implements CommandExecutor {

    private boolean isAdmin;
    private CommandExecutor executor;

    public CommandExecutorProxy(String user, String password) {
        if ("Gabriel".equals(user) && "admin".equals(password)) {
            this.isAdmin = true;
        }

        this.executor = new CommandExecutorImpl();
    }

    @Override
    public boolean runCommand(String cmd) throws Exception {
        if (isAdmin) {
            return executor.runCommand(cmd);
        }

        if (cmd.startsWith("rm")) {
            throw new IllegalArgumentException("Delete not allowed");
        }

        return executor.runCommand(cmd);
    }

}
