package Controller.entities.enemy;

import Controller.entities.enemy.AI.AIMedium;
import javafx.scene.image.Image;

public class Minvo extends AIMedium {
    public Minvo(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img, '4');
        mark = 175;
    }
}
