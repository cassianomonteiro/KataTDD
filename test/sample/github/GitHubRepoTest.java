package sample.github;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sample.github.GitHubOwner;
import sample.github.GitHubRepo;

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
        repo.setLanguage("Java");
        repo.setOwner(owner);

        // Then
        assertEquals(10, repo.getId());
        assertEquals("repoName", repo.getName());
        assertEquals("Java", repo.getLanguage());
        assertEquals(owner, repo.getOwner());
    }

    @Test
    public void toStringShouldShowNameAndLanguage() {

        // Given
        GitHubRepo repo = new GitHubRepo();
        repo.setName("repoName");
        repo.setLanguage("Java");

        // Then
        assertEquals("repoName (Java)", repo.toString());
    }

}