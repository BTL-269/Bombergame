package Controller;

import Controller.entities.Bomber;
import Controller.entities.Entity;
import Controller.graphics.Board;
import Controller.graphics.Sprite;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<Entity>();
    private Board board = new Board();
    private Entity bomberman = new Bomber(1, 1, Sprite.child_right.getFxImage());

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
        bomberman = new Bomber(1, 1, Sprite.child_right.getFxImage());
    }

    @FXML
    void clickPlayer2(MouseEvent event) {
        bomberman = new Bomber(1, 1, Sprite.girl_right.getFxImage());
    }

    @FXML
    void clickPlayer3(MouseEvent event) {
        bomberman = new Bomber(1, 1, Sprite.boy_right.getFxImage());
    }

    @FXML
    void clickPlayer4(MouseEvent event) {
        bomberman = new Bomber(1, 1, Sprite.play_right.getFxImage());
    }

    @FXML
    void clickPlayer5(MouseEvent event) {
        bomberman = new Bomber(1, 1, Sprite.gamer_right.getFxImage());
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