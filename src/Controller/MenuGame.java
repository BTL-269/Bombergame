package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuGame implements Initializable {

    @FXML
    private Pane pane;

    @FXML
    private ListView<String> listScore = new ListView<String>();
    private ObservableList<String> list = FXCollections.observableArrayList();
    @FXML
    public void clickStart(ActionEvent event) {
        try {
            URL url = MenuGame.class.getResource("SettingInterface.fxml");
            if (url == null) {
                throw new FileNotFoundException("File is not found!");
            }
            pane.getChildren().add(new FXMLLoader().load(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void showScore(ActionEvent event) {
        listScore.setVisible(true);
    }

    @FXML
    public void clickInstruction(ActionEvent event) {
        try {
            URL url = MenuGame.class.getResource("InstructionInterface.fxml");
            if (url == null) {
                throw new FileNotFoundException("File is not found!");
            }
            pane.getChildren().add(new FXMLLoader().load(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void clickSpeaker(MouseEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listScore.setVisible(false);
        listScore.setItems(list);
    }
}
