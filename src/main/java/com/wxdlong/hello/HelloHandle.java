package com.wxdlong.hello;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloHandle extends AbstractHandler {
    private String greeting;
    private String body;

    public HelloHandle() {
        this("Hello World");
    }

    public HelloHandle(String greeting) {
        this(greeting, null);
    }

    public HelloHandle(String greeting, String body) {
        this.greeting = greeting;
        this.body = body;
    }


    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        PrintWriter out = response.getWriter();

        out.println("<h1>" + greeting + "</h1>");
        if (body != null) {
            out.println(body);
        }

        baseRequest.setHandled(true);
    }

    public static void main(String[] args) throws Exception
    {
        Server server = new Server(8080);
        server.setHandler(new HelloHandle("greeting","kaibing"));

        server.start();
        server.join();
    }
}
