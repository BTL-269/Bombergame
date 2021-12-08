package Controller.entities.enemy;

import Controller.entities.enemy.AI.AILow;
import Controller.entities.enemy.AI.AIMedium;
import javafx.scene.image.Image;

public class Oneal extends AIMedium {

    public Oneal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img, '2');
        mark = 175;
    }

}