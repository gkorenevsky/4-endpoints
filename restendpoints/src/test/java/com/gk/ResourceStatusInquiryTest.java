package com.gk;

import com.ctc.wstx.util.StringUtil;
import com.gk.rwsendpoints.dto.ResourceStatus;
import org.apache.commons.lang.StringUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by greg korenevsky on 7/30/14.
 */
public class ResourceStatusInquiryTest {

    private static String ENDPOINT_URL_PREFIX;
    private static final String ENDPOINT_URL_SUFFIX = "/rest/v1/resourceStatus";

    @BeforeClass
    public static void beforeClass() {

//        ENDPOINT_URL_PREFIX = System.getProperty("service.url");
        ENDPOINT_URL_PREFIX = "http://localhost:8080/restendpoints";
    }

    @Before
    public void testSetup() {
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testRetrieveResourceStatuses() throws IOException {

        List<Object> providers = new ArrayList<Object>() {
            { add(new JacksonJsonProvider()); }
        };

        WebClient client = WebClient.create(ENDPOINT_URL_PREFIX + ENDPOINT_URL_SUFFIX, providers);
        Response restResponse = client.accept("application/json")
                .get();

        assertEquals(Response.Status.OK.getStatusCode(), restResponse.getStatus());
        MappingJsonFactory factory = new MappingJsonFactory();
        JsonParser parser = factory.createJsonParser((InputStream) restResponse.getEntity());

        List<ResourceStatus> resourceStatuses = new ArrayList<ResourceStatus>();

        while(parser.nextToken() != null) {

                if (parser.getCurrentToken() == JsonToken.START_ARRAY) {
                    while(parser.nextToken() != JsonToken.END_ARRAY) {
                        ResourceStatus resourceStatus = (ResourceStatus) parser.readValueAs(ResourceStatus.class);
                        resourceStatuses.add(resourceStatus);
                    }
                }

        }

        assertTrue(resourceStatuses.size() > 0);

        for (ResourceStatus resourceStatus : resourceStatuses) {

            String displayMsg = "Resource name="
                    + resourceStatus.getResourceName()
                    + ", status="
                    + resourceStatus.getStatus()
                    + ", description="
                    + resourceStatus.getDescription()
                    ;

            if (!StringUtils.isBlank(resourceStatus.getDiagnosticMessage())) {
                displayMsg += ", diagnostic message=" + resourceStatus.getDiagnosticMessage();
            }

            System.out.println( displayMsg );
        }
    }
}
