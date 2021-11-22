package Controller.entities;

import Controller.graphics.Sprite;
import javafx.scene.image.Image;

public class Explosion extends Entity {

    private int type;
    private Sprite sprite;
    public boolean check = false;
    public long timeStart;
    public int t = 60;

    public Explosion(int x, int y, Image img, int type) {
        super(x, y, img);
        this.type = type;
    }

    @Override
    public void update() {
        if (map[yUnit][xUnit] != '#') {
            if (System.currentTimeMillis() - timeStart < 1000 && check == true) {
                set_animate(1000);
                chooseSprite();
                img = sprite.getFxImage();
                map[yUnit][xUnit] = '-';
            } else {
                map[yUnit][xUnit] = ' ';
                check = false;
                img = null;
            }
        }
    }

    private void chooseSprite() {
        switch (type) {
            case UP:
                sprite = Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1,
                        Sprite.explosion_vertical_top_last2, _animate, t);
                break;
            case RIGHT:
                sprite = Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1,
                        Sprite.explosion_horizontal_right_last2, _animate, t);
                break;
            case DOWN:
                sprite = Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1,
                        Sprite.explosion_vertical_down_last2, _animate, t);
                break;
            case LEFT:
                sprite = Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1,
                        Sprite.explosion_horizontal_left_last2, _animate, t);
                break;
            case CENTER:
                sprite = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, _animate, t);
                break;
        }
    }

    public void setXY(int xUnit, int yUnit) {
        this.xUnit = xUnit;
        this.yUnit = yUnit;
        this.x = xUnit * Sprite.DEFAULT_SIZE;
        this.y = yUnit * Sprite.DEFAULT_SIZE;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}