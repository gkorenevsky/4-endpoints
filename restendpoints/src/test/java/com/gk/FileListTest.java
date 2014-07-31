package com.gk;

import com.gk.rwsendpoints.dto.FileInfo;
import com.gk.rwsendpoints.dto.FileListResponse;
import org.apache.commons.io.FileUtils;
import org.apache.cxf.jaxrs.client.WebClient;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.MappingJsonFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by greg korenevsky on 7/30/14.
 */
public class FileListTest {

    private static String ENDPOINT_URL_PREFIX;
    private static final String ENDPOINT_URL_SUFFIX = "/rest/v1/fileList";

    @BeforeClass
    public static void beforeClass() {

//        ENDPOINT_URL_PREFIX = System.getProperty("service.url");
        ENDPOINT_URL_PREFIX = "http://localhost:8080/restendpoints";
    }

    @Before
    public void testSetup() {
    }

    @Test
    public void testFileListDirectoryExists() throws IOException {

        List<Object> providers = new ArrayList<Object>() {
            {
                add(new JacksonJsonProvider());
            }
        };

        WebClient client = WebClient.create(ENDPOINT_URL_PREFIX + ENDPOINT_URL_SUFFIX
                , providers);
        Response restResponse = client.accept("application/json")
                .query("directoryPath", "/etc")
                .get();

        assertEquals(Response.Status.OK.getStatusCode(), restResponse.getStatus());
        MappingJsonFactory factory = new MappingJsonFactory();
        JsonParser parser = factory.createJsonParser((InputStream) restResponse.getEntity());
        FileListResponse fileListResponse = parser.readValueAs(FileListResponse.class);

        assertTrue(fileListResponse.isSuccess());
        assertTrue(fileListResponse.getFileCount() > 0);
        assertNotNull(fileListResponse.getDirectory());
        assertNotNull(fileListResponse.getDirectoryContents());
        assertEquals(fileListResponse.getFileCount(), fileListResponse.getDirectoryContents().size());
        assertEquals(fileListResponse.getDirectory().getAbsolutePath(), "/etc");
        assertEquals(fileListResponse.getDirectory().getType(), FileInfo.Type.DIRECTORY.toString());

        System.out.println("Requested directory:");
        displayFileInfo(fileListResponse.getDirectory());
        System.out.println("Directory contents:");
        for (FileInfo fileInfo : fileListResponse.getDirectoryContents()) {
            displayFileInfo(fileInfo);
        }
    }

    @Test
    public void testFileListBadDirectory() throws IOException {

        // Set up: create a temporary file in user's directory

        File tempFile = null;

        Response restResponse;
        try {
            tempFile = File.createTempFile("tmp", null, FileUtils.getUserDirectory());
            String tempFilePath = tempFile.getAbsolutePath();

            List<Object> providers = new ArrayList<Object>() {
                {
                    add(new JacksonJsonProvider());
                }
            };

            WebClient client = WebClient.create(ENDPOINT_URL_PREFIX + ENDPOINT_URL_SUFFIX
                    , providers);
            restResponse = client.accept("application/json")
                    .query("directoryPath", tempFilePath)
                    .get();
        } finally {
            if (tempFile != null) {
                FileUtils.deleteQuietly(tempFile);
            }
        }

        assertEquals(Response.Status.OK.getStatusCode(), restResponse.getStatus());
        MappingJsonFactory factory = new MappingJsonFactory();
        JsonParser parser = factory.createJsonParser((InputStream) restResponse.getEntity());
        FileListResponse fileListResponse = parser.readValueAs(FileListResponse.class);

        assertFalse(fileListResponse.isSuccess());
        assertTrue(fileListResponse.getFileCount() == 0);
        assertNull(fileListResponse.getDirectory());
        assertNull(fileListResponse.getDirectoryContents());
        assertTrue(fileListResponse.getDiagnosticMessage().startsWith("Not a directory"));
    }

    private void displayFileInfo(FileInfo fileInfo) {

        System.out.println("Absolute path="
                + fileInfo.getAbsolutePath()
                + " , type="
                + fileInfo.getType());
    }
}
