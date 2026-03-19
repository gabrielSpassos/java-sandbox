package com.gabrielspassos;

import org.eclipse.jetty.ee10.webapp.WebAppContext;
import org.eclipse.jetty.server.Server;

public class Main {

    static void main() throws Exception {
        IO.println("JSP POC!");
        var server = new Server(8080);

        var context = new WebAppContext();
        context.setContextPath("/");
        context.setBaseResourceAsString("src/main/webapp");

        server.setHandler(context);

        server.start();
        server.join();
    }

}
