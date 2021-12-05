package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuGame implements Initializable {

    @FXML
    private Pane pane;

    @FXML
    private ImageView imgRadio;

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
    public void clickSpeaker(MouseEvent event) {
        BombermanGame.playAudio++;
        if (BombermanGame.playAudio % 2 == 0) {
            BombermanGame.mediaPlayer.play();
            BombermanGame.mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            Image img = new Image(getClass().getResourceAsStream("images/radio.png"));
            imgRadio.setImage(img);
        } else {
            BombermanGame.mediaPlayer.pause();
            Image img = new Image(getClass().getResourceAsStream("images/radio_off.png"));
            imgRadio.setImage(img);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (BombermanGame.playAudio % 2 == 0) {
            Image img = new Image(getClass().getResourceAsStream("images/radio.png"));
            imgRadio.setImage(img);
        } else {
            Image img = new Image(getClass().getResourceAsStream("images/radio_off.png"));
            imgRadio.setImage(img);
        }
    }
}
