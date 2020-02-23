package com.wxdlong.app;

import org.apache.log4j.BasicConfigurator;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;

public class JettyS {
    public static void main(String[] args) throws Exception {
        BasicConfigurator.configure();
        Server server = new Server(8080);

        ServletContextHandler ctx =
                new ServletContextHandler(ServletContextHandler.NO_SESSIONS);

        ctx.setContextPath("/");
        server.setHandler(ctx);


        ServletHolder serHol = ctx.addServlet(ServletContainer.class, "/rest/*");
        serHol.setInitOrder(1);
        serHol.setInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, com.wxdlong.app.Application.class.getName());
        serHol.setInitParameter("jersey.config.server.tracing.type", "ON_DEMAND");
        //serHol.getServletHandler().addFilter();

        server.start();
        server.join();
    }
}
