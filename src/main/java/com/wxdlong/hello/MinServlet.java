package com.wxdlong.hello;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MinServlet {

    public static Server createServer(int port) {

        Server server = new Server(port);
        ServletHandler servletHandler = new ServletHandler();
        servletHandler.addServletWithMapping(HelloServlet.class, "/*");

        server.setHandler(servletHandler);
        return server;

    }

    public static void main(String[] args) throws Exception {
        Server server = createServer(8080);

        // Start things up!
        server.start();

        // The use of server.join() the will make the current thread join and
        // wait until the server thread is done executing.
        server.join();
    }

    public static class HelloServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("text/html");
            response.setCharacterEncoding("utf-8");
            response.getWriter().println("<h1>Hello from HelloServlet</h1>");
        }
    }
}
