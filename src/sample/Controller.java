package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import sample.github.GitHubFetcher;
import sample.github.GitHubRepo;

import java.util.List;

public class Controller implements ViewController {

    @FXML
    private ListView<String> repoPropertiesListView;

    @FXML
    private TextField userTextfield;

    @FXML
    private ListView<GitHubRepo> reposListView;
    private GitHubFetcher gitHubFetcher;
    private Presenter presenter;

    public Controller() {
        this.presenter = new Presenter(this);
    }

    @FXML
    void okButtonClicked(ActionEvent event) {
        presenter.viewRequestedReposFromUser(userTextfield.getText());
    }

    public TextField getUserTextfield() {
        return userTextfield;
    }

    public void setUserTextfield(TextField userTextfield) {
        this.userTextfield = userTextfield;
    }

    public void setGitHubFetcher(GitHubFetcher gitHubFetcher) {
        this.gitHubFetcher = gitHubFetcher;
    }

    public GitHubFetcher getGitHubFetcher() {
        return gitHubFetcher;
    }

    public ListView<GitHubRepo> getReposListView() {
        return reposListView;
    }

    public void setReposListView(ListView<GitHubRepo> reposListView) {
        this.reposListView = reposListView;
    }

    public Presenter getPresenter() {
        return presenter;
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showReposList(List<GitHubRepo> repos) {
        ObservableList<GitHubRepo> items = FXCollections.observableArrayList(repos);
        reposListView.setItems(items);
    }
}
