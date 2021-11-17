package entities.emeny;

import graphics.Map;
import graphics.Sprite;
import javafx.scene.image.Image;

public class Doll extends Enemy{

    public Doll(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        map = new Map("res/levels/Level1.txt").getMap();
        direction = 0;
        animate = 0;
        sprite = Sprite.doll_left1;
    }

    @Override
    public void chooseSprite(int a) {
        switch(a) {
            case 0:
            case 2:
                sprite = Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, animate, 60);
                break;
            case 1:
            case 3:
                sprite = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, animate, 60);
                break;
        }
    }
}
