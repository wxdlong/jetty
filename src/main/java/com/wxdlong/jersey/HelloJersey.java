package com.wxdlong.jersey;

import org.glassfish.jersey.media.multipart.MultiPart;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;

@Path("/hj")
public class HelloJersey {
//    private static final Logger LOGGER = LoggerFactory.getLogger(HelloJersey.class);

    @GET
    @Produces("text/plain")
    public String getJersey() {
        return "Hello Jersey";
    }

    @POST
    @Produces("text/plain")
    public String getName(MyBean myBean) {
        System.out.println("hello bean");
        return myBean.name;
    }


    @GET
    @Produces({"application/json", MediaType.APPLICATION_XML})
    public MyBean getMyBean() {
        return new MyBean("bing", 32);
    }

    @GET()
    @Path("xml")

    @Produces({MediaType.APPLICATION_XML})
    public MyBean getMyBeanXml() {
        return new MyBean("bing", 33);
    }

    @GET()
    @Path("textXml")

    @Produces({MediaType.APPLICATION_XML})
    public Response getMyBeanTextXml() {

        MyBean bing = new MyBean("bing", 33);
        return Response.status(200).entity(bing).build();
    }

    @GET()
    @Path("file")


    public Response getFile() {
        File file = new File(this.getClass().getResource("/jersey/hello.xml").getFile());
        String mimeType = new MimetypesFileTypeMap().getContentType(file);

        return Response
                .ok(file, mimeType)
                .header("Content-Disposition", "attachment; filename=hello.xml")
                .build();
    }

    @GET()
    @Path("part1")
    @Produces(MediaType.APPLICATION_XML)
    public MultiPart getPart1() {
        File file = new File(this.getClass().getResource("/jersey/hello.xml").getFile());
        MultiPart multiPart = new MultiPart()
                .bodyPart(file, MediaType.APPLICATION_XML_TYPE);

        return multiPart;
    }


}
