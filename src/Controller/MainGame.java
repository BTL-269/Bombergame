package Controller;

import Controller.entities.Bomber;
import Controller.entities.Entity;
import Controller.graphics.Board;
import Controller.graphics.Sprite;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static Controller.SettingGame.bomberman;

public class MainGame implements Initializable {
    public static final int WIDTH = 25;
    public static final int HEIGHT = 18;
    public static int level = 1;
    public static int soul = 3;
    public static int numberEnemies = 0;
    public static long playTime;
    public static int playScore = 0;
    public static int bestMark = 500;
    private int isPause = 0;
    private GraphicsContext gc;
    private Canvas canvas;
    public static List<Entity> entities = new ArrayList<Entity>();
    private Board board = new Board();
    public static AnimationTimer timer;

    @FXML
    private Label levelLabel;

    @FXML
    private TextField score;

    @FXML
    private TextField highScore;

    @FXML
    private TextField time;

    @FXML
    private ImageView imgPause;

    @FXML
    private ImageView imgRadio;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private TextField heart;

    public void update() {
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        board.getStillObjects().forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }

    public void setDetail() {
        levelLabel.setText("  Level  " + String.valueOf(level));
        heart.setText(String.valueOf(soul));
        time.setText(String.valueOf(300 - (System.currentTimeMillis() - playTime) / 1000));
        score.setText(String.valueOf(playScore));
        highScore.setText(String.valueOf(bestMark));
    }

    public static void restart() {
        if (playScore > bestMark) {
            bestMark = playScore;
        }
        if (Bomber.lose == true) {
            playScore = 0;
        } else if (Bomber.win == true) {
            level++;
        }
        Bomber.lose = false;
        Bomber.win = false;
        MainGame.soul = 3;
    }

    @FXML
    void clickBack(MouseEvent event) {
        try {
            URL url = SettingGame.class.getResource("MenuInterface.fxml");
            if (url == null) {
                throw new FileNotFoundException("File is not found!");
            }
            mainPane.getChildren().add(new FXMLLoader().load(url));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void clickPause(MouseEvent event) {
        isPause ++;
        if (isPause % 2 == 1) {
            MainGame.timer.stop();
            Image img = new Image(getClass().getResourceAsStream("images/pause.png"));
            imgPause.setImage(img);
        } else {
            Image img = new Image(getClass().getResourceAsStream("images/continue.png"));
            imgPause.setImage(img);
            MainGame.timer.start();
        }
    }

    @FXML
    void clickRadio(MouseEvent event) {
        BombermanGame.playAudio++;
        if (BombermanGame.playAudio % 2 == 0) {
            BombermanGame.mediaPlayer.play();
            BombermanGame.mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            Image img = new Image(getClass().getResourceAsStream("images/speaker.png"));
            imgRadio.setImage(img);
        } else {
            BombermanGame.mediaPlayer.pause();
            Image img = new Image(getClass().getResourceAsStream("images/speaker_pause.png"));
            imgRadio.setImage(img);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Create Canvas
        canvas = new Canvas(Sprite.DEFAULT_SIZE * WIDTH, Sprite.DEFAULT_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Create root container
        Group root = new Group();
        root.getChildren().add(canvas);
        root.setLayoutX(0);
        root.setLayoutY(100);

        // Add to main pain
        mainPane.getChildren().add(root);
        // Set image for radio
        if (BombermanGame.playAudio % 2 == 0) {
            Image img = new Image(getClass().getResourceAsStream("images/speaker.png"));
            imgRadio.setImage(img);
        } else {
            Image img = new Image(getClass().getResourceAsStream("images/speaker_pause.png"));
            imgRadio.setImage(img);
        }

        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
                setDetail();
                if ((Bomber.win == true || Bomber.lose == true) || (300 - (System.currentTimeMillis() - playTime) / 1000) < 0) {
                    if ((300 - (System.currentTimeMillis() - playTime) / 1000) < 0) {
                        Bomber.lose = true;
                    }
                    try {
                        URL url = InstructionGame.class.getResource("ResultInterface.fxml");
                        if (url == null) {
                            throw new FileNotFoundException("File not found!");
                        }
                        mainPane.getChildren().add(new FXMLLoader().load(url));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        timer.start();
        board.createMap("levels/Level" + Integer.toString(level) + ".txt");
        entities.add(bomberman.bomb1);
        entities.add(bomberman.bomb2);
        for (int i = 0; i < 9; i++) {
            entities.add(bomberman.bomb1.explosions.get(i));
            entities.add(bomberman.bomb2.explosions.get(i));
        }
        entities.add(bomberman);
        if (mainPane != null) {
            Platform.runLater(() -> {
                mainPane.addEventFilter(KeyEvent.KEY_PRESSED, event1 -> {
                    bomberman.handle(event1);
                });
                mainPane.addEventFilter(KeyEvent.KEY_RELEASED, event1 -> {
                    bomberman.keyReleased(event1);
                });
            });
        }
    }
}
