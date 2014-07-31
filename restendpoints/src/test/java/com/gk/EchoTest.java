package com.gk;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.junit.BeforeClass;
import org.junit.Test;

public class EchoTest {
    private static String endpointUrl;

    @BeforeClass
    public static void beforeClass() {

//        endpointUrl = System.getProperty("service.url");
        endpointUrl = "http://localhost:8080/restendpoints";
    }

    @Test
    public void testEcho() throws Exception {
        WebClient client = WebClient.create(endpointUrl + "/rest/v1/echo/SierraTangoNevada");
        Response r = client.accept("text/plain").get();
        assertEquals(Response.Status.OK.getStatusCode(), r.getStatus());
        String value = IOUtils.toString((InputStream)r.getEntity());
        assertEquals("SierraTangoNevada", value);
    }
}
