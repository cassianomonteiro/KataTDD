package sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by cassiano on 29/08/16.
 */
public class GitHubRepoTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void gitHubRepoShouldHaveAllProperties () {

        // Given
        GitHubRepo repo = new GitHubRepo();
        GitHubOwner owner = new GitHubOwner();

        // When
        repo.setId(10);
        repo.setName("repoName");
        repo.setOwner(owner);

        // Then
        assertEquals(10, repo.getId());
        assertEquals("repoName", repo.getName());
        assertEquals(owner, repo.getOwner());
    }


}