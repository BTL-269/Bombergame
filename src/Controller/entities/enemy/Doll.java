package Controller.entities.enemy;

import Controller.entities.enemy.AI.AILow;
import javafx.scene.image.Image;

public class Doll extends AILow {

    public Doll(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img, '3');
    }

}

