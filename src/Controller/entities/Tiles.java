package Controller.entities;

import Controller.graphics.Sprite;
import javafx.scene.image.Image;

public abstract class Tiles extends Entity {
    protected long sTime;
    protected boolean show = false;

    public Tiles(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public abstract void update();

    public boolean isCollide() {
        for (int i = 0; i < 5; i++) {
            if ((xUnit == Bomber.bomb1.explosions.get(i).xUnit && yUnit == Bomber.bomb1.explosions.get(i).yUnit)
                    && Bomber.bomb1.explosions.get(i).check) {
                sTime = Bomber.bomb1.explosions.get(i).timeStart;
                return true;
            }
        }
        for (int i = 5; i < 9; i++) {
            if (xUnit == Bomber.bomb1.explosions.get(i).xUnit && yUnit == Bomber.bomb1.explosions.get(i).yUnit
                    && Bomber.bomb1.explosions.get(i).isExplosion() && Bomber.bomb1.explosions.get(i).check) {
                sTime = Bomber.bomb1.explosions.get(i).timeStart;
                return true;
            }
        }
        for (int i = 0; i < 5; i++) {
            if (xUnit == Bomber.bomb2.explosions.get(i).xUnit && yUnit == Bomber.bomb2.explosions.get(i).yUnit
                    && Bomber.bomb2.explosions.get(i).check) {
                sTime = Bomber.bomb2.explosions.get(i).timeStart;
                return true;
            }
        }
        for (int i = 5; i < 9; i++) {
            if (xUnit == Bomber.bomb2.explosions.get(i).xUnit && yUnit == Bomber.bomb2.explosions.get(i).yUnit
                    && Bomber.bomb2.explosions.get(i).isExplosion() && Bomber.bomb2.explosions.get(i).check) {
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
