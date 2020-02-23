package com.wxdlong.jersey;

import org.apache.log4j.BasicConfigurator;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.junit.jupiter.api.Test;

class HelloJerseyTest {

    @Test
    void getJersey() throws Exception {
        BasicConfigurator.configure();
        Server server = new Server(8080);

        ServletContextHandler ctx =
                new ServletContextHandler(ServletContextHandler.NO_SESSIONS);

        ctx.setContextPath("/");
        server.setHandler(ctx);


        ServletHolder serHol = ctx.addServlet(ServletContainer.class, "/rest/*");
        serHol.setInitOrder(1);
        serHol.setInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, com.wxdlong.app.Application.class.getName());
        //serHol.getServletHandler().addFilter();

        server.start();
        server.join();
    }
}