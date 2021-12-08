package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;


public class BombermanGame extends Application {
    public static int playAudio = 0;
    public static Media media;
    public static MediaPlayer mediaPlayer;

    /* Sound */
    public static AudioClip soundExplosion;
    public static AudioClip soundEatItem;
    public static AudioClip soundDead;
    public static AudioClip soundWin;
    public static AudioClip soundMonsterDead;

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        media = new Media(new File("radio.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        soundExplosion = new AudioClip(new File("res/sound/explosion.mp3").toURI().toString());
        soundEatItem = new AudioClip(new File("res/sound/eatItem.mp3").toURI().toString());
        soundDead = new AudioClip(new File("res/sound/dead.mp3").toURI().toString());
        soundWin = new AudioClip(new File("res/sound/win.mp3").toURI().toString());
        soundMonsterDead = new AudioClip(new File("res/sound/monster_die.wav").toURI().toString());

        stage.setTitle("Bomberman Game");
        stage.setScene(scene);
        stage.show();
    }
}
