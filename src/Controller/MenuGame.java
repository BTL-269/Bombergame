package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.net.URL;

public class MenuGame {

    @FXML
    private Pane pane;

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
}
