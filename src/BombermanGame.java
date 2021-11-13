import entities.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
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

        Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);

    }

    public void createMap() {
        /*for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Entity object;
                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                }
                else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
        }*/
        try {
            FileReader fr = new FileReader("res/levels/Level1.txt");
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
            /*String[] arr = s.split(String.valueOf(' '));
            int row = Integer.parseInt(arr[1]);
            int col = Integer.parseInt(arr[2]);*/
            String s;
            int j = 0;
            while ((s = br.readLine()) != null && j < HEIGHT) {
                System.out.println(s);
                for (int i = 0; i < s.length(); i++) {
                    Entity obj;
                    if (s.charAt(i) == '#') {
                        obj = new Wall(i, j, Sprite.wall.getFxImage());
                    } else if (s.charAt(i) == '*') {
                        obj = new Brick(i, j, Sprite.brick.getFxImage());
                    } else if (s.charAt(i) == 'x') {
                        obj = new Portal(i, j, Sprite.portal.getFxImage());
                    } else {
                        obj = new Grass(i, j, Sprite.grass.getFxImage());
                    }
                    stillObjects.add(obj);
                }
                j++;
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
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
