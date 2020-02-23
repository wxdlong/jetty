package com.wxdlong.hello;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ListenerHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.EnumSet;

import static javax.servlet.DispatcherType.ASYNC;
import static javax.servlet.DispatcherType.REQUEST;


public class OneServletContext {
    public static Server createServer(int port) {
        Server server = new Server(port);

        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContextHandler.setContextPath("/");
        server.setHandler(servletContextHandler);

        servletContextHandler.addServlet(MinServlet.HelloServlet.class, "/hello/*");

        ServletHolder debugHolder = new ServletHolder("debug", DumpServlet.class);
        servletContextHandler.addServlet(debugHolder, "/dump/*");
        servletContextHandler.addServlet(debugHolder, "*.dump");

        servletContextHandler.addServlet(DefaultServlet.class, "/");

        servletContextHandler.addFilter(TestFilter.class, "/test/*", EnumSet.of(REQUEST));
        servletContextHandler.addFilter(TestFilter.class, "*.test", EnumSet.of(REQUEST, ASYNC));


        servletContextHandler.getServletHandler().addListener(new ListenerHolder(InitListener.class));
        servletContextHandler.getServletHandler().addListener(new ListenerHolder(RequestListener.class));
        return server;
    }

    public static void main(String[] args) throws Exception {
        Server server = createServer(8080);
        server.start();
        server.join();
    }

    public static class DumpServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest request,
                             HttpServletResponse response) throws IOException {
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("text/html");
            response.setCharacterEncoding("utf-8");
            response.getWriter().println("<h1>Hello from DumpServlet</h1>");
        }
    }

    public static class TestFilter implements Filter {

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
            System.out.println("TestFilter init");
        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            System.out.println("TestFilter doFilter");
            if (response instanceof HttpServletResponse) {
                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                httpServletResponse.setHeader("X-TestFilter", "true");
            }
            chain.doFilter(request, response);
        }

        @Override
        public void destroy() {
            System.out.println("TestFilter destroy");
        }
    }

    public static class RequestListener implements ServletRequestListener {


        @Override
        public void requestDestroyed(ServletRequestEvent sre) {
            System.out.println("requestDestroyed");
        }

        @Override
        public void requestInitialized(ServletRequestEvent sre) {
            System.out.println("requestInitialized");
        }
    }

    public static class InitListener implements ServletContextListener {

        @Override
        public void contextInitialized(ServletContextEvent sce) {
            System.out.println("contextInitialized");
        }

        @Override
        public void contextDestroyed(ServletContextEvent sce) {
            System.out.println("contextDestroyed");
        }
    }


}
