package sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by cassiano on 29/08/16.
 */
public class GitHubParserTest {

    private String simpleRepoJSON;

    @Before
    public void setUp() throws Exception {

        simpleRepoJSON = "{\n" +
                "    \"id\": 37609525,\n" +
                "    \"name\": \"cockeYoungerKasami\",\n" +
                "    \"full_name\": \"cassianomonteiro/cockeYoungerKasami\",\n" +
                "    \"owner\": {\n" +
                "      \"login\": \"cassianomonteiro\",\n" +
                "      \"id\": 1374714}" + "}";

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void ownerJSONShouldBeParsedToObject() {

        // Given
        String ownerJSON = "{\n" +
                "      \"login\": \"cassianomonteiro\",\n" +
                "      \"id\": 1374714}";

        // When
        GitHubOwner parsedOwner = GitHubParser.parseOwnerFromJSON(ownerJSON);

        // Then
        assertNotNull(parsedOwner);
        assertEquals("cassianomonteiro", parsedOwner.getLogin());
        assertEquals(1374714, parsedOwner.getID());
    }

    @Test
    public void malformedJSONShouldReturnEmptyObject() {

        // Given
        String malformedJSON = "aaaaaaaaaaa";

        // When
        GitHubOwner parsedOwner = GitHubParser.parseOwnerFromJSON(malformedJSON);

        // Then
        assertNotNull(parsedOwner);
        assertNull(parsedOwner.getLogin());
        assertEquals(0, parsedOwner.getID());
    }

    @Test
    public void repoJSONShouldBeParsed () {

        // Given
        // initial state

        // When
        List<GitHubRepo> repos = GitHubParser.parseReposFromJSON(simpleRepoJSON);

        // Then
        assertNotNull(repos);
        assertEquals(1, repos.size());
        GitHubRepo repo = repos.get(0);
        assertEquals(37609525, repo.getId());
        assertEquals("cockeYoungerKasami", repo.getName());
        assertNotNull(repo.getOwner());
        assertEquals("cassianomonteiro", repo.getOwner().getLogin());
        assertEquals(1374714, repo.getOwner().getID());
    }

    @Test
    public void jsonWithArrayShouldParseToList() {

        // Given
        String jsonWithArray = "[" + simpleRepoJSON  + "," + simpleRepoJSON + "]";

        // When
        List<GitHubRepo> repos = GitHubParser.parseReposFromJSON(jsonWithArray);

        // Then
        assertNotNull(repos);
        assertEquals(2, repos.size());
        assertEquals(37609525, repos.get(0).getId());
    }

}