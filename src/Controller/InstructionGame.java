package Controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.FileNotFoundException;
import java.net.URL;


public class InstructionGame {

    @FXML
    private AnchorPane Inpane;

    @FXML
    void clickBack(ActionEvent event) {
        try {
            URL url = InstructionGame.class.getResource("Menu.fxml");
            if (url == null) {
                throw new FileNotFoundException("File not found!");
            }
            Inpane.getChildren().add(new FXMLLoader().load(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
