package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import sample.github.GitHubRepo;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements ViewController, Initializable {

    @FXML
    private ListView<String> repoPropertiesListView;

    @FXML
    private TextField userTextfield;

    @FXML
    private ListView<GitHubRepo> reposListView;

    private Presenter presenter;

    public Controller() {
        this.presenter = new Presenter(this);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Set action for selecting list item
        reposListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> presenter.viewSelectedRepo(newValue));
    }

    @FXML
    void okButtonClicked(ActionEvent event) {
        presenter.viewRequestedReposFromUser(userTextfield.getText());
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

    @Override
    public void showRepoProperties(List<String> expectedRepoProperties) {
        ObservableList<String> items = FXCollections.observableArrayList(expectedRepoProperties);
        repoPropertiesListView.setItems(items);
    }
}
