package Controller.entities;

import Controller.graphics.Sprite;
import javafx.scene.image.Image;

public class Brick extends Entity {

    public long sTime;

    public Brick(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        if (isCollide()) {
            if (System.currentTimeMillis() - sTime < 1000) {
                set_animate(1000);
                broken();
                img = sprite.getFxImage();
            } else {
                map[yUnit][xUnit] = ' ';
                img = null;
            }
        }

    }

    public boolean isCollide() {
        for (int i = 0; i < 5; i++) {
            if ((xUnit == Bomber.bomb1.explosions.get(i).xUnit && yUnit == Bomber.bomb1.explosions.get(i).yUnit)
                    && Bomber.bomb1.explosions.get(i).check == true) {
                sTime = Bomber.bomb1.explosions.get(i).timeStart;
                return true;
            }
        }
        for (int i = 5; i < 9; i++) {
            if (xUnit == Bomber.bomb1.explosions.get(i).xUnit && yUnit == Bomber.bomb1.explosions.get(i).yUnit
                    && Bomber.bomb1.explosions.get(i).isExplosion() && Bomber.bomb1.explosions.get(i).check == true) {
                sTime = Bomber.bomb1.explosions.get(i).timeStart;
                return true;
            }
        }
        for (int i = 0; i < 5; i++) {
            if (xUnit == Bomber.bomb2.explosions.get(i).xUnit && yUnit == Bomber.bomb2.explosions.get(i).yUnit
                    && Bomber.bomb2.explosions.get(i).check == true) {
                sTime = Bomber.bomb2.explosions.get(i).timeStart;
                return true;
            }
        }
        for (int i = 5; i < 9; i++) {
            if (xUnit == Bomber.bomb2.explosions.get(i).xUnit && yUnit == Bomber.bomb2.explosions.get(i).yUnit
                    && Bomber.bomb2.explosions.get(i).isExplosion() && Bomber.bomb2.explosions.get(i).check == true) {
                sTime = Bomber.bomb2.explosions.get(i).timeStart;
                return true;
            }
        }
        return false;
    }

    public void broken() {
        sprite = Sprite.movingSprite(Sprite.brick_break, Sprite.brick_break1, Sprite.brick_break2, _animate, 80);
    }

}
