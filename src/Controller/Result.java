package Controller;

import Controller.entities.Bomber;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class Result implements Initializable {

    @FXML
    private Pane pane;

    @FXML
    private Label scoreLabel;

    @FXML
    private Button resultButton;

    @FXML
    private ImageView resultImage;

    @FXML
    void clickBack(ActionEvent event) {
        try {
            URL url = InstructionGame.class.getResource("Menu.fxml");
            if (url == null) {
                throw new FileNotFoundException("File not found!");
            }
            pane.getChildren().add(new FXMLLoader().load(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void clickResult(ActionEvent event) {
        MainGame.restart();
        MainGame.soul = 4;
        try {
            URL url = SettingGame.class.getResource("MainInterface.fxml");
            if (url == null) {
                throw new FileNotFoundException("File is not found!");
            }
            pane.getChildren().add(new FXMLLoader().load(url));
            MainGame.playTime = System.currentTimeMillis();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MainGame.timer.stop();
        String s = "";
        if (Bomber.lose == true) {
            s = "images/gameOver.png";
            resultButton.setText("Again");
        } else {
            s = "images/levelUp.png";
            resultButton.setText("Next");
        }
        Image img = new Image(getClass().getResourceAsStream(s));
        resultImage.setImage(img);
        scoreLabel.setText("Score: " + MainGame.playScore);
    }
}
