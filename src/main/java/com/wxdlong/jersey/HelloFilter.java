package com.wxdlong.jersey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;

public class HelloFilter implements ContainerResponseFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloFilter.class);

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        LOGGER.info("Hello Filter execute");
    }
}
