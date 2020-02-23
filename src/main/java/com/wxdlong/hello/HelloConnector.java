package com.wxdlong.hello;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;

public class HelloConnector {
    public static Server createServer(int port) {
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        connector.setName("http");
        connector.setHost("localhost");
        connector.setIdleTimeout(30000);
        server.addConnector(connector);
        server.setHandler(new HelloHandle("greeting"));
        return server;
    }

    public static void main(String[] args) throws Exception {
        Server server = createServer(8080);
        server.start();
        server.join();
    }
}
