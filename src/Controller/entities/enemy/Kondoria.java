package Controller.entities.enemy;

import Controller.entities.enemy.AI.AILow;
import javafx.scene.image.Image;

public class Kondoria extends AILow {

    public Kondoria(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img, '5');
        mark = 125;
    }

}