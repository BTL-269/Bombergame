package entities.emeny;

import graphics.Map;
import graphics.Sprite;
import javafx.scene.image.Image;

/**
 * The type Balloon.
 */
public class Balloon extends Enemy {

    public Balloon(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        map = new Map("res/levels/Level1.txt").getMap();
        direction = 0;
        animate = 0;
        sprite = Sprite.balloom_left1;
    }

    @Override
    public void chooseSprite(int a) {
        switch(a) {
            case 0:
            case 2:
                sprite = Sprite.movingSprite(Sprite.balloom_right1, Sprite.balloom_right2, Sprite.balloom_right3, animate, 60);
                break;
            case 1:
            case 3:
                sprite = Sprite.movingSprite(Sprite.balloom_left1, Sprite.balloom_left2, Sprite.balloom_left3, animate, 60);
                break;
        }
    }
}
