package sample;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by cassiano on 29/08/16.
 */
public class GitHubOwnerTest {

    private GitHubOwner owner;


    @org.junit.Before
    public void setUp() throws Exception {
        owner = new GitHubOwner();
    }

    @org.junit.After
    public void tearDown() throws Exception {
        owner = null;
    }

    @Test
    public void githubOwnerShouldHaveID () {

        // Given
        int ownerID = 1;

        // When
        owner.setID(ownerID);

        // Then
        assertEquals(1, owner.getID());
    }

    @Test
    public void githubOwnerShouldHaveAnyID () {

        // Given
        int ownerID = 10;

        // When
        owner.setID(ownerID);

        // Then
        assertEquals(10, owner.getID());
    }

    @Test
    public void githubOwnerIDShouldAcceptLongNumbers () {

        // Given
        long ownerID = Long.MAX_VALUE;

        // When
        owner.setID(ownerID);

        // Then
        assertEquals(Long.MAX_VALUE, owner.getID());
    }

    @Test
    public void githubOwnerShouldHaveLogin() {

        // Given
        String ownerLogin = "aLogin";

        // When
        owner.setLogin(ownerLogin);

        // Then
        assertEquals("aLogin", owner.getLogin());
    }

}