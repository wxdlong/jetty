package com.wxdlong.app;

import com.wxdlong.jersey.HelloFilter;
import com.wxdlong.jersey.MyBean;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class Application extends ResourceConfig {
    public Application() {
        packages("com.wxdlong.jersey").register(MyBean.class).register(HelloFilter.class)
                .register(JacksonFeature.class)
                .register(MultiPartFeature.class)
                .register(LoggingFeature.class);
    }
}
