package sample.github;

import org.junit.*;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by cassiano on 05/09/16.
 */
public class HTTPConnectorTest {

    private HTTPConnector httpConnector;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        String sinatraFolderPath = System.getProperty("user.dir") + "/test";
        Runtime.getRuntime().exec("ruby Server.rb", null, new File(sinatraFolderPath));

        // Wait a little bit to make sure sinatra server is up before running tests
        Thread.sleep(1000);
    }

    @Before
    public void setUpBefore () {
        httpConnector = new HTTPConnector();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        Runtime.getRuntime().exec("killall ruby");
    }

    @Test
    public void getFromURLShouldReturnContents() {

        // Given
        String url = "http://localhost:4567/success";
        String expectedContents = "[{\"id\":12345, \"name\":\"cassiano\"}]";

        // Then
        String retrievedContents = httpConnector.getContentsFromURLString(url);

        // When
        assertEquals(expectedContents, retrievedContents);
    }

    @Test
    public void getFromURLWithErrorShouldReturnEmptyString() {

        // Given
        String url = "http://localhost:4567/error";
        String expectedContents = "";

        // Then
        String retrievedContents = httpConnector.getContentsFromURLString(url);

        // When
        assertEquals(expectedContents, retrievedContents);
    }

}