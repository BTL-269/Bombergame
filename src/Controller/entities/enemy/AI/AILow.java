package Controller.entities.enemy.AI;

import Controller.entities.enemy.Enemy;
import javafx.scene.image.Image;

public class AILow extends Enemy {

    public AILow(int xUnit, int yUnit, Image img, char e) {
        super(xUnit, yUnit, img, e);
        direction = rd.nextInt(4);
    }

    @Override
    public int findDirection() {
        return rd.nextInt(4);
    }
}

