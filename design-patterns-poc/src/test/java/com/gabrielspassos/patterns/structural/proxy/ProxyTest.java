package com.gabrielspassos.patterns.structural.proxy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProxyTest {

    @Test
    void shouldExecuteCommand() throws Exception {
        var commandExecutor = new CommandExecutorProxy("user", "pass");

        var result = commandExecutor.runCommand("ls");

        assertTrue(result);
    }

    @Test
    void shouldNotExecuteRmCommand() {
        var commandExecutor = new CommandExecutorProxy("user", "pass");

        assertThrows(IllegalArgumentException.class, () -> commandExecutor.runCommand("rm /temp/foo.txt"));
    }

    @Test
    void shouldExecuteRmCommandWithAdmin() throws Exception {
        var commandExecutor = new CommandExecutorProxy("Gabriel", "admin");

        var result1 = commandExecutor.runCommand("touch foo.txt");
        var result2 = commandExecutor.runCommand("rm foo.txt");

        assertTrue(result1);
        assertTrue(result2);
    }
}
