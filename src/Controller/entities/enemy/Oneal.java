package Controller.entities.enemy;

import Controller.entities.enemy.AI.AILow;
import javafx.scene.image.Image;

public class Oneal extends AILow {

    public Oneal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img, '2');
        mark = 125;
    }

}