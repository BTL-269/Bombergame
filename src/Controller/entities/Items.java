package Controller.entities;

import Controller.BombermanGame;
import Controller.SettingGame;
import Controller.graphics.Sprite;
import javafx.scene.image.Image;

public class Items extends Tiles {
    private char symbol;
    private boolean isEat = false;

    public Items(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        symbol = map[yUnit][xUnit];
    }

    @Override
    public void update() {
        if (isCollide() && !show) {
            if (System.currentTimeMillis() - sTime <= 1000) {
                set_animate(1000);
                broken();
                img = sprite.getFxImage();
            } else {
                show = true;
            }
        }
        if (show && !isEat) {
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
            img = sprite.getFxImage();
        }
        if (xUnit == SettingGame.bomberman.xUnit && yUnit == SettingGame.bomberman.yUnit) {
            if (!isEat) {
                map[yUnit][xUnit] = ' ';
                if (BombermanGame.playAudio % 2 == 0) {
                    BombermanGame.soundEatItem.play();
                }
                isEat = true;
            }
            img = null;
        }
    }
}
