package sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import sample.github.GitHubFetcher;
import sample.github.GitHubRepo;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by cassiano on 05/09/16.
 */
public class PresenterTest {

    @Mock
    private GitHubFetcher gitHubFetcherMock;

    @Mock
    private ViewController viewControllerMock;

    private Presenter presenter;

    @Before
    public void setUp() throws Exception {
        gitHubFetcherMock = mock(GitHubFetcher.class);
        viewControllerMock = mock(ViewController.class);
        presenter = new Presenter(viewControllerMock);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void retrieveUserReposShouldFillReposList() {

        // Given
        String username = "username";
        presenter.setGitHubFetcher(gitHubFetcherMock);
        List<GitHubRepo> repos = Arrays.asList(new GitHubRepo(), new GitHubRepo());
        when(gitHubFetcherMock.getUserRepos("username")).thenReturn(repos);

        // When
        presenter.viewRequestedReposFromUser(username);

        // Then
        verify(viewControllerMock).showReposList(repos);
    }

    @Test
    public void defaultConstructorShouldHaveViewController() {

        // Given
        Presenter newPresenter = new Presenter(viewControllerMock);

        // Then
        assertSame(viewControllerMock, newPresenter.getViewController());
    }

    @Test
    public void defaultConstructorShouldHaveDefaulGitHubFetcher() {

        // Given
        Presenter defaultPresenter = new Presenter(viewControllerMock);

        // Then
        assertNotNull(defaultPresenter.getGitHubFetcher());
    }

}