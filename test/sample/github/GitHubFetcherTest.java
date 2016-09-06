package sample.github;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by cassiano on 05/09/16.
 */
public class GitHubFetcherTest {

    @Mock
    private HTTPConnector httpConnectorMock;

    private GitHubFetcher gitHubFetcher;

    @Before
    public void setUp() throws Exception {
        httpConnectorMock = mock(HTTPConnector.class);
        gitHubFetcher = new GitHubFetcher();
        gitHubFetcher.setHTTPConnector(httpConnectorMock);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void retrieveUserReposShouldFillObjects() {

        // Given
        String repoJSON = "[{\"id\":10},{\"id\":20}]";
        String userName = "testuser";
        when(httpConnectorMock.getContentsFromURLString("https://api.github.com/users/testuser/repos"))
                .thenReturn(repoJSON);

        // When
        List<GitHubRepo> repos = gitHubFetcher.getUserRepos(userName);

        // Then
        assertEquals(2, repos.size());
        assertEquals(10, repos.get(0).getId());
        assertEquals(20, repos.get(1).getId());
    }

    @Test
    public void defaultConstructorShouldCreateDefaultHTTPConnector() {

        // Given
        GitHubFetcher defaultFetcher = new GitHubFetcher();

        // Then
        assertNotNull(defaultFetcher.getHTTPConnector());
    }

}