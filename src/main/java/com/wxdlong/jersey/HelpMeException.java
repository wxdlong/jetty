package com.wxdlong.jersey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class HelpMeException implements ExceptionMapper<Exception> {
    private final static Logger LOGGER = LoggerFactory.getLogger(HelpMeException.class);

    @Override
    public Response toResponse(Exception exception) {
        LOGGER.error("HelpMe", exception);

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .type(MediaType.APPLICATION_JSON)
                .entity(exception.getCause())
                .build();
    }
}
