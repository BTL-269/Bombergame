package Controller;

import entities.Bomber;
import entities.Entity;
import graphics.Board;
import graphics.Sprite;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SettingGame {
    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;
    public static int level = 1;

    public static int player = 1;
    public static int numberEnemies = 0;

    private GraphicsContext gc;
    private Canvas canvas;
    public static List<Entity> entities = new ArrayList<Entity>();
    private Board board = new Board();
    private Bomber bomberman = new Bomber(1, 1, Sprite.child_right.getFxImage());

    public void update() {
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        board.getStillObjects().forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }

    @FXML
    private Pane paneNext;

    @FXML
    void clickLevel1(MouseEvent event) {
        level = 1;
    }

    @FXML
    void clickLevel2(MouseEvent event) {
        level = 2;
    }

    @FXML
    void clickLevel3(MouseEvent event) {
        level = 3;
    }

    @FXML
    void clickLevel4(MouseEvent event) {
        level = 4;
    }

    @FXML
    void clickPlayer1(MouseEvent event) {
        player = 1;
        bomberman = new Bomber(1, 1, Sprite.child_right.getFxImage());
    }

    @FXML
    void clickPlayer2(MouseEvent event) {
        player = 2;
        bomberman = new Bomber(1, 1, Sprite.girl_right.getFxImage());
    }

    @FXML
    void clickPlayer3(MouseEvent event) {
        player = 3;
        bomberman = new Bomber(1, 1, Sprite.boy_right.getFxImage());
    }

    @FXML
    void clickPlayer4(MouseEvent event) {
        player = 4;
        bomberman = new Bomber(1, 1, Sprite.gamer_right.getFxImage());
    }

    @FXML
    void clickPlayer5(MouseEvent event) {
        player = 5;
        bomberman = new Bomber(1, 1, Sprite.play_right.getFxImage());
    }

    @FXML
    void clickPlay(ActionEvent event) {
        // Tao Canvas
        canvas = new Canvas(Sprite.DEFAULT_SIZE * WIDTH, Sprite.DEFAULT_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root, 780, 480);
        BombermanGame.stages.setScene(scene);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();
        board.createMap("levels/Level" + Integer.toString(level) + ".txt");
        entities.add(bomberman);
        entities.add(bomberman.bomb1);
        entities.add(bomberman.bomb2);
        for (int i = 0; i < 9; i++) {
            entities.add(bomberman.bomb1.explosions.get(i));
            entities.add(bomberman.bomb2.explosions.get(i));
        }
        entities.add(bomberman);
        if (BombermanGame.stages.getScene() != null) {
            Platform.runLater(() -> {
                BombermanGame.stages.getScene().addEventFilter(KeyEvent.KEY_PRESSED, event1 -> {
                    bomberman.handle(event1);
                });
            });
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