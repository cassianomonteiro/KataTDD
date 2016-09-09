package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;
import sample.github.GitHubFetcher;
import sample.github.GitHubRepo;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by cassiano on 05/09/16.
 */

// To run JavaFX tests, add Maven dependency org.testfx:testfx-junit:4.0.4-alpha
// @see http://torgen-engineering.blogspot.com.br/2015/11/testing-javafx-applications-with-testfx.html
public class ControllerTest extends ApplicationTest {

    private Controller controller;

    @Mock
    private Presenter presenterMock;

    // JavaFX stuff
    private Parent mainNode;
    private TextField userTextfield;
    private Button okButton;
    private ListView<GitHubRepo> reposListView;
    private ListView<String> repoPropertiesListView;

    @Override
    // Method to initialize JavaFX stuff
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        mainNode = loader.load(Main.class.getResource("sample.fxml").openStream());
        controller = loader.getController();
        stage.setScene(new Scene(mainNode));
        stage.show();

        /* Do not forget to put the GUI in front of windows. Otherwise, the robots may interact with another
        window, the one in front of all the windows... */
        stage.toFront();
    }

    @Before
    public void setUp() throws Exception {
        userTextfield = lookup("#userTextfield").query();
        okButton = lookup("#okButton").query();
        reposListView = lookup("#reposListView").query();
        repoPropertiesListView = lookup("#repoPropertiesListView").query();
        presenterMock = mock(Presenter.class);
        controller.setPresenter(presenterMock);
    }

    @After
    public void tearDown() throws Exception {
        /* Close the window. It will be re-opened at the next test. */
        FxToolkit.hideStage();
        release(new KeyCode[] {});
        release(new MouseButton[] {});
    }

    @Test
    public void constructorShouldCreateDefaultPresenter() {

        // Given
        Controller controller = new Controller();

        // When
        assertNotNull(controller.getPresenter());
        assertSame(controller, controller.getPresenter().getViewController());
    }

    @Test
    public void okButtonShouldRetrieveRepos() {

        // Given
        List<GitHubRepo> repos = Arrays.asList(new GitHubRepo(), new GitHubRepo());
        doAnswer(invocationOnMock -> {
            controller.showReposList(repos);
            return null;
        }).when(presenterMock).viewRequestedReposFromUser("user");

        // When
        clickOn(userTextfield).type(KeyCode.U).type(KeyCode.S).type(KeyCode.E).type(KeyCode.R);
        clickOn(okButton);
        WaitForAsyncUtils.waitForFxEvents();

        // Then
        verify(presenterMock).viewRequestedReposFromUser("user");
        assertEquals(2, reposListView.getItems().size());
    }

    @Test
    public void reposListClickShouldShowRepoProperties() {

        // Given
        GitHubRepo repo1 = new GitHubRepo();
        repo1.setName("Repo1");
        repo1.setLanguage("Objetive-C");
        GitHubRepo repo2 = new GitHubRepo();
        repo2.setName("Repo2");
        repo2.setLanguage("Java");

        List<GitHubRepo> repos = Arrays.asList(repo1, repo2);
        List<String> repoProperties = Arrays.asList("Property1","Property2","Property3","Property4");

        doAnswer(invocationOnMock -> {
            controller.showRepoProperties(repoProperties);
            return null;
        }).when(presenterMock).viewSelectedRepo(repo2);

        // When
        controller.showReposList(repos);
        WaitForAsyncUtils.waitForFxEvents();
        clickOn("Repo2 (Java)");
        WaitForAsyncUtils.waitForFxEvents();

        // Then
        verify(presenterMock).viewSelectedRepo(repo2);
        assertEquals(4, repoPropertiesListView.getItems().size());
    }

}