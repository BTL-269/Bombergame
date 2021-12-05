package Controller.entities;

import Controller.graphics.Sprite;
import javafx.scene.image.Image;

public class Items extends Entity {
    private long sTime;
    private char symbol;
    private boolean show = false;

    public Items(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        symbol = map[yUnit][xUnit];
        System.out.println("symbol " + symbol + " " + map[yUnit][xUnit]);
    }

    @Override
    public void update() {
        if (isCollide() && !show) {
            if (System.currentTimeMillis() - sTime <= 1000) {
                set_animate(1000);
                broken();
                img = sprite.getFxImage();
            } else {
                switch (symbol) {
                    case 'd': {
                        sprite = Sprite.powerup_detonator;
                        map[yUnit][xUnit] = 'D';
                    }
                    break;
                    case 'b': {
                        sprite = Sprite.powerup_bombs;
                        map[yUnit][xUnit] = 'B';
                    }
                    break;
                    case 'f': {
                        sprite = Sprite.powerup_flames;
                        map[yUnit][xUnit] = 'F';
                    }
                    break;
                    case 's': {
                        sprite = Sprite.powerup_speed;
                        map[yUnit][xUnit] = 'S';
                    }
                    break;
                }
                show = true;
                if (sprite != null) {
                    img = sprite.getFxImage();
                } else {
                    img = null;
                }
            }
        }
        if (map[yUnit][xUnit] == ' ') {
            img = null;
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
