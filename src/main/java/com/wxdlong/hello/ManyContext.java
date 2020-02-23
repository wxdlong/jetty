package com.wxdlong.hello;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;

public class ManyContext {
    public static Server createServer(int port) {
        Server server = new Server(port);
        ContextHandler rootContext = new ContextHandler("/");
        rootContext.setHandler(new HelloHandle("root"));


        ContextHandler frContext = new ContextHandler("/FR");
        frContext.setHandler(new HelloHandle("FR"));

        ContextHandler bgContext = new ContextHandler("/BG");
        bgContext.setHandler(new HelloHandle("BG"));

        ContextHandler contextV = new ContextHandler("/");
        contextV.setVirtualHosts(new String[]{"192.168.249.193"});
        contextV.setHandler(new HelloHandle("Virtual Hello"));


        ContextHandlerCollection contextHandlerCollection = new ContextHandlerCollection();
        contextHandlerCollection.addHandler(rootContext);
        contextHandlerCollection.addHandler(frContext);
        contextHandlerCollection.addHandler(bgContext);

        server.setHandler(contextHandlerCollection);
        return server;
    }

    public static void main(String[] args) throws Exception {
        Server server = createServer(8080);
        server.start();
        server.join();
    }
}
