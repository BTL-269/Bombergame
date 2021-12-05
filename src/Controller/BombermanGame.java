package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;


public class BombermanGame extends Application {
    public static int playAudio = 0;
    public static Media media;
    public static MediaPlayer mediaPlayer;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuInterface.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        media = new Media(new File("radio.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        stage.setTitle("Bomberman Game");
        stage.setScene(scene);
        stage.show();
    }
}
