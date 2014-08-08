package com.gk;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import com.gk.rwsendpoints.dto.AuthenticationRequest;
import com.gk.rwsendpoints.dto.AuthenticationResponse;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by greg korenevsky on 7/28/14.
 */
public class UserAuthenticationTest {

    private static String ENDPOINT_URL_PREFIX;
    private static final String ENDPOINT_URL_SUFFIX = "/rest/v1/authentication";

    @BeforeClass
    public static void beforeClass() {

//        ENDPOINT_URL_PREFIX = System.getProperty("service.url");
        ENDPOINT_URL_PREFIX = "http://localhost:8080/restendpoints";
    }

    @Before
    public void testSetup() {
    }

    @Test
    public void testUserAuthenticationFailure() throws Exception {

        List<Object> providers = new ArrayList<Object>() {
            {
                add(new JacksonJsonProvider());
            }
        };

        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setUserId("john-doe");
        authenticationRequest.setPassword("somemadeuppassword");

        WebClient client = WebClient.create(ENDPOINT_URL_PREFIX + ENDPOINT_URL_SUFFIX, providers);
        Response restResponse = client.accept("application/json")
                .type("application/json")
                .post(authenticationRequest);

        assertEquals(Response.Status.OK.getStatusCode(), restResponse.getStatus());
        MappingJsonFactory factory = new MappingJsonFactory();
        JsonParser parser = factory.createJsonParser((InputStream) restResponse.getEntity());
        AuthenticationResponse authenticationResponse = parser.readValueAs(AuthenticationResponse.class);
        assertFalse(authenticationResponse.isUserAuthenticated());
    }

    @Test
    public void testUserAuthenticationSuccessful() throws Exception {

        List<Object> providers = new ArrayList<Object>() {
            {
                add(new JacksonJsonProvider());
            }
        };

        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setUserId("jholden");
        authenticationRequest.setPassword("123abc");

        WebClient client = WebClient.create(ENDPOINT_URL_PREFIX + ENDPOINT_URL_SUFFIX, providers);
        Response restResponse = client.accept("application/json")
                .type("application/json")
                .post(authenticationRequest);

        assertEquals(Response.Status.OK.getStatusCode(), restResponse.getStatus());
        MappingJsonFactory factory = new MappingJsonFactory();
        JsonParser parser = factory.createJsonParser((InputStream) restResponse.getEntity());
        AuthenticationResponse authenticationResponse = parser.readValueAs(AuthenticationResponse.class);
        assertTrue(authenticationResponse.isUserAuthenticated());
    }
}
