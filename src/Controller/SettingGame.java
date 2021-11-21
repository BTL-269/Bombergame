package Controller;

import Controller.entities.Bomber;
import Controller.entities.Entity;
import Controller.graphics.Sprite;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.net.URL;

public class SettingGame {
    public static int map = 1;
    public static Entity bomberman = new Bomber(1, 1, Sprite.child_right.getFxImage());

    @FXML
    private Pane paneNext;

    @FXML
    void clickMap1(MouseEvent event) {
        map = 1;
    }

    @FXML
    void clickMap2(MouseEvent event) {
        map = 2;
    }

    @FXML
    void clickMap3(MouseEvent event) {
        map = 3;
    }

    @FXML
    void clickMap4(MouseEvent event) {
        map = 4;
    }

    @FXML
    void clickOran(MouseEvent event) {
        bomberman = new Bomber(1, 1, Sprite.child_right.getFxImage());
    }

    @FXML
    void clickMaria(MouseEvent event) {
        bomberman = new Bomber(1, 1, Sprite.girl_right.getFxImage());
    }

    @FXML
    void clickTomy(MouseEvent event) {
        bomberman = new Bomber(1, 1, Sprite.boy_right.getFxImage());
    }

    @FXML
    void clickNick(MouseEvent event) {
        bomberman = new Bomber(1, 1, Sprite.gamer_right.getFxImage());
    }

    @FXML
    void clickWinny(MouseEvent event) {
        bomberman = new Bomber(1, 1, Sprite.play_right.getFxImage());
    }

    @FXML
    void clickPlay(ActionEvent event) {
        try {
            URL url = SettingGame.class.getResource("MainInterface.fxml");
            if (url == null) {
                throw new FileNotFoundException("File is not found!");
            }
            paneNext.getChildren().add(new FXMLLoader().load(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void clickBack(ActionEvent event) {
        try {
            URL url = SettingGame.class.getResource("MenuInterface.fxml");
            if (url == null) {
                throw new FileNotFoundException("File is not found!");
            }
            paneNext.getChildren().add(new FXMLLoader().load(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}