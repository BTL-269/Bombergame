import entities.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import graphics.Sprite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    
    private GraphicsContext gc;
    private Canvas canvas;
    private final List<Entity> entities = new ArrayList<>();
    private final List<Entity> stillObjects = new ArrayList<>();

    private Bomber bomberman;

    //private static char[][] board = new char[HEIGHT][WIDTH];

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        createMap();

        bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);
        entities.add(bomberman.bomb);

        if (stage.getScene() != null) {
            Platform.runLater(() -> {
                stage.getScene().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
                    bomberman.handle(event);
                    /*if (event.getCode() == KeyCode.SPACE) {
                        bomberman.bomb.setXY(6, 1);
                    }
                    System.out.println(bomberman.map[1][1] + " " + stillObjects.get(1).map[6][1]);*/
                });
            });
        }

    }

    public void createMap() {
        Entity entity = new Grass(1, 1, Sprite.grass.getFxImage());
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                Entity obj;
                if (entity.map[i][j] == '#') {
                    obj = new Wall(j, i, Sprite.wall.getFxImage());
                } else {
                    obj = new Grass(j, i, Sprite.grass.getFxImage());
                }
                stillObjects.add(obj);
                if (entity.map[i][j] == '*') {
                    entities.add(new Brick(j, i, Sprite.brick.getFxImage()));
                } else if (entity.map[i][j] == 'x') {
                    entities.add(new Portal(j, i, Sprite.portal.getFxImage()));
                }
                System.out.print(entity.map[i][j]);
            }
            System.out.println();
        }
    }

    public void update() {
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
