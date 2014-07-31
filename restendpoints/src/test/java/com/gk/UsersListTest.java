package com.gk;

import com.gk.rwsendpoints.dto.UserInfo;
import com.gk.rwsendpoints.dto.UsersListResponse;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by greg korenevsky on 8/1/14.
 */
public class UsersListTest {

    private static String ENDPOINT_URL_PREFIX;
    private static final String ENDPOINT_URL_SUFFIX = "/rest/v1/getUsers";

    @BeforeClass
    public static void beforeClass() {

//        ENDPOINT_URL_PREFIX = System.getProperty("service.url");
        ENDPOINT_URL_PREFIX = "http://localhost:8080/restendpoints";
    }

    @Before
    public void testSetup() {
    }

    @Test
    public void testRetrieveAllUsers() throws IOException {

        List<Object> providers = new ArrayList<Object>() {
            {
                add(new JacksonJsonProvider());
            }
        };

        WebClient client = WebClient.create(ENDPOINT_URL_PREFIX + ENDPOINT_URL_SUFFIX, providers);
        Response restResponse = client.accept("application/json")
                .get();

        assertEquals(Response.Status.OK.getStatusCode(), restResponse.getStatus());
        MappingJsonFactory factory = new MappingJsonFactory();
        JsonParser parser = factory.createJsonParser((InputStream) restResponse.getEntity());
        UsersListResponse usersListResponse = parser.readValueAs(UsersListResponse.class);
        assertTrue(usersListResponse.getUserCount() > 0);
        assertNotNull(usersListResponse.getUsersList());
        assertEquals(usersListResponse.getUserCount(), usersListResponse.getUsersList().size());

        for (UserInfo userInfo : usersListResponse.getUsersList()) {
            System.out.println("User name="
                    + userInfo.getFirstName()
                    + ' '
                    + userInfo.getLastName());
        }
    }

    @Test
    public void testRetrieveSpecificUser() throws IOException {

        List<Object> providers = new ArrayList<Object>() {
            {
                add(new JacksonJsonProvider());
            }
        };

        WebClient client = WebClient.create(ENDPOINT_URL_PREFIX + ENDPOINT_URL_SUFFIX, providers);
        Response restResponse = client.accept("application/json")
                .query("attributeName", "userId")
                .query("attributeValue", "rharwood")
                .get();

        assertEquals(Response.Status.OK.getStatusCode(), restResponse.getStatus());
        MappingJsonFactory factory = new MappingJsonFactory();
        JsonParser parser = factory.createJsonParser((InputStream) restResponse.getEntity());
        UsersListResponse usersListResponse = parser.readValueAs(UsersListResponse.class);
        assertEquals(usersListResponse.getUserCount(), 1);      // exactly one user
        assertNotNull(usersListResponse.getUsersList());
        assertEquals(usersListResponse.getUserCount(), usersListResponse.getUsersList().size());

        for (UserInfo userInfo : usersListResponse.getUsersList()) {
            System.out.println("User name="
                    + userInfo.getFirstName()
                    + ' '
                    + userInfo.getLastName());
        }
    }

    @Test
    public void testRetrieveMultipleUsers() throws IOException {

        List<Object> providers = new ArrayList<Object>() {
            {
                add(new JacksonJsonProvider());
            }
        };

        WebClient client = WebClient.create(ENDPOINT_URL_PREFIX + ENDPOINT_URL_SUFFIX, providers);
        Response restResponse = client.accept("application/json")
                .query("attributeName", "gender")
                .query("attributeValue", "F")
                .get();

        assertEquals(Response.Status.OK.getStatusCode(), restResponse.getStatus());
        MappingJsonFactory factory = new MappingJsonFactory();
        JsonParser parser = factory.createJsonParser((InputStream) restResponse.getEntity());
        UsersListResponse usersListResponse = parser.readValueAs(UsersListResponse.class);
        assertEquals(usersListResponse.getUserCount(), 2);
        assertNotNull(usersListResponse.getUsersList());
        assertEquals(usersListResponse.getUserCount(), usersListResponse.getUsersList().size());

        for (UserInfo userInfo : usersListResponse.getUsersList()) {
            System.out.println("User name="
                            + userInfo.getFirstName()
                            + ' '
                            + userInfo.getLastName()
                            + ", gender="
                            + userInfo.getGender()
            )
            ;
        }
    }
}
