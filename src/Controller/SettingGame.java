package Controller;

import Controller.entities.Bomber;
import Controller.graphics.Sprite;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.net.URL;

public class SettingGame {
    public static int typeMap = 1;
    public static int player = 1;
    public static Bomber bomberman = new Bomber(1, 1, Sprite.child_right.getFxImage());

    @FXML
    private Pane paneNext;

    @FXML
    void clickMap1(MouseEvent event) {
        typeMap = 1;
    }

    @FXML
    void clickMap2(MouseEvent event) {
        typeMap = 2;
    }

    @FXML
    void clickMap3(MouseEvent event) {
        typeMap = 3;
    }

    @FXML
    void clickMap4(MouseEvent event) {
        typeMap = 4;
    }

    @FXML
    void clickOran(MouseEvent event) {
        player = 1;
        bomberman = new Bomber(1, 1, Sprite.child_right.getFxImage());
    }

    @FXML
    void clickMaria(MouseEvent event) {
        player = 2;
        bomberman = new Bomber(1, 1, Sprite.girl_right.getFxImage());
    }

    @FXML
    void clickTomy(MouseEvent event) {
        player = 3;
        bomberman = new Bomber(1, 1, Sprite.boy_right.getFxImage());
    }

    @FXML
    void clickNick(MouseEvent event) {
        player = 4;
        bomberman = new Bomber(1, 1, Sprite.gamer_right.getFxImage());
    }

    @FXML
    void clickWinny(MouseEvent event) {
        player = 5;
        bomberman = new Bomber(1, 1, Sprite.play_right.getFxImage());
    }

    @FXML
    void clickPlay(ActionEvent event) {
        MainGame.restart();
        try {
            URL url = SettingGame.class.getResource("MainInterface.fxml");
            if (url == null) {
                throw new FileNotFoundException("File is not found!");
            }
            paneNext.getChildren().add(new FXMLLoader().load(url));
            MainGame.playTime = System.currentTimeMillis();
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