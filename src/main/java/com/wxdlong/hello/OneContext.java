package com.wxdlong.hello;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;

public class OneContext {
    public static Server createServer(int port) {
        Server server = new Server(port);
        ContextHandler contextHandler = new ContextHandler("/context");
        contextHandler.setHandler(new HelloHandle());

        server.setHandler(contextHandler);
        return server;
    }

    public static void main(String[] args) throws Exception {
        Server server = createServer(8080);
        server.start();
        server.join();
    }
}
