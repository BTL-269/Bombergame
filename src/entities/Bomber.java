package entities;
import graphics.Sprite;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Bomber extends Entity {

    private boolean die, up, down;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        up = false;
        down = false;
        die = false;
    }

    @Override
    public void update() {

    }

    public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.UP) {
            up = true;
            y += Sprite.SCALED_SIZE;
        }
        if (event.getCode() == KeyCode.DOWN) {
            down = true;
            y += Sprite.SCALED_SIZE;
        }
        if (event.getCode() == KeyCode.LEFT) {
            x += Sprite.SCALED_SIZE;
        }
        if (event.getCode() == KeyCode.RIGHT) {
            x += Sprite.SCALED_SIZE;
        }
    }

    public boolean checkCollision() {

        return true;
    }
}
