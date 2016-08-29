package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private ListView<String> repoPropertiesListView;

    @FXML
    private TextField userTextfield;

    @FXML
    private ListView<?> reposListView;

    @FXML
    void okButtonClicked(ActionEvent event) {

    }
}
