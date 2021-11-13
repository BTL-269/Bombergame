package entities;
import graphics.Sprite;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Bomber extends Entity {

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

    public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.UP) {
            x += Sprite.SCALED_SIZE;
        }
        if (event.getCode() == KeyCode.DOWN) {
            x += Sprite.SCALED_SIZE;
        }
        if (event.getCode() == KeyCode.LEFT) {
            x += Sprite.SCALED_SIZE;
        }
        if (event.getCode() == KeyCode.RIGHT) {
            x += Sprite.SCALED_SIZE;
        }
    }
}
