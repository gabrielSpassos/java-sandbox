package com.gabrielspassos;

import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.server.Server;

import java.io.File;

public class Main {

    public static void main() throws Exception {
        System.out.println("JSP POC!");
        var server = new Server(8080);

        var context = new WebAppContext();
        context.setContextPath("/");
        context.setBaseResourceAsString("src/main/webapp");

        context.setAttribute(
                "jakarta.servlet.context.tempdir",
                new File("target/tmp")
        );

        context.setConfigurationDiscovered(true);

        // enable annotations (@WebServlet)
        context.setAttribute(
                "org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",
                ".*jetty.*\\.jar$|.*jsp.*\\.jar$|.*taglibs.*\\.jar$"
        );

        server.setHandler(context);

        server.start();
        server.join();
    }

}
