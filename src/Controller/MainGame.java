package Controller;

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
    private int isPause = 1;
    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<Entity>();
    private Board board = new Board();

    public void update() {
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        board.getStillObjects().forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }

    @FXML
    private Label levelLabel;

    @FXML
    private TextField score;

    @FXML
    private TextField iWallpass;

    @FXML
    private TextField highScore;

    @FXML
    private TextField time;

    @FXML
    private TextField iBombs;

    @FXML
    private ImageView imgPause;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private TextField iSpeed;

    @FXML
    private TextField heart;

    @FXML
    private TextField iHeart;

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
        String s = "";
        if (isPause % 2 == 0) {
            s = "images/pause.png";
        } else {
            s = "images/continue.png";
        }
        Image img = new Image(s);
        imgPause.setImage(img);
    }

    @FXML
    void clickRadio(MouseEvent event) {

    }

    @FXML
    void clickBomb(MouseEvent event) {

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
        entities.add(bomberman.bomb);
        for (int i = 0; i < 5; i++) {
            entities.add(bomberman.bomb.explosions.get(i));
        }
        entities.add(bomberman);
        if (mainPane != null) {
            Platform.runLater(() -> {
                mainPane.addEventFilter(KeyEvent.KEY_PRESSED, event1 -> {
                    bomberman.handle(event1);
                });
            });
        }
    }
}

