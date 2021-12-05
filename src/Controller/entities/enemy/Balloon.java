package Controller.entities.enemy;

import Controller.entities.enemy.AI.AILow;
import javafx.scene.image.Image;

/**
 * The type Balloon.
 */
public class Balloon extends AILow {

    public Balloon(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img, '1');
    }

}

