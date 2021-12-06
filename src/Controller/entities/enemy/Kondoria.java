package Controller.entities.enemy;

import Controller.entities.enemy.AI.AIMedium;
import javafx.scene.image.Image;

public class Kondoria extends AIMedium {

    public Kondoria(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img, '5');
        mark = 200;
    }

}
