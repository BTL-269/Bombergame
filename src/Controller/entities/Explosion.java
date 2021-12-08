package Controller.entities;

import Controller.graphics.Sprite;
import javafx.scene.image.Image;

public class Explosion extends Entity {

    public static final int UP1 = 4;
    public static final int RIGHT1 = 5;
    public static final int DOWN1 = 6;
    public static final int LEFT1 = 7;

    private int type;
    public boolean check = false;
    public long timeStart;

    private boolean powerUp = false;

    private int t = 60;

    public Explosion(int x, int y, Image img, int type) {
        super(x, y, img);
        this.type = type;
    }

    public void setPowerUp(boolean powerUp) {
        this.powerUp = powerUp;
    }

    @Override
    public void update() {
        if (isExplosion() && check) {
            if (System.currentTimeMillis() - timeStart < 1000) {
                set_animate(1000);
                chooseSprite();
                img = sprite.getFxImage();
                map[yUnit][xUnit] = '-';
            } else {
                if (map[yUnit][xUnit] != 'D' || map[yUnit][xUnit] != 'B' || map[yUnit][xUnit] != 'S'
                        || map[yUnit][xUnit] != 'F' || map[yUnit][xUnit] != 'X') {
                    map[yUnit][xUnit] = ' ';
                }
                check = false;
                img = null;
                _animate = 0;
            }
        }
    }

    private void chooseSprite() {
        switch (type) {
            case UP:
                if (!powerUp || map[yUnit - 1][xUnit] == '#') {
                    sprite = Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1,
                            Sprite.explosion_vertical_top_last2, _animate, t);
                } else {
                    sprite = Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1,
                            Sprite.explosion_vertical2, _animate, t);
                }
                break;
            case RIGHT:
                if (!powerUp || map[yUnit][xUnit + 1] == '#') {
                    sprite = Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1,
                            Sprite.explosion_horizontal_right_last2, _animate, t);
                } else {
                    sprite = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1,
                            Sprite.explosion_horizontal2, _animate, t);
                }
                break;
            case DOWN:
                if (!powerUp || map[yUnit + 1][xUnit] == '#') {
                    sprite = Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1,
                            Sprite.explosion_vertical_down_last2, _animate, t);
                } else {
                    sprite = Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1,
                            Sprite.explosion_vertical2, _animate, t);
                }
                break;
            case LEFT:
                if (!powerUp || map[yUnit][xUnit - 1] == '#') {
                    sprite = Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1,
                            Sprite.explosion_horizontal_left_last2, _animate, t);
                } else {
                    sprite = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1,
                            Sprite.explosion_horizontal2, _animate, t);
                }
                break;
            case UP1:
                sprite = Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1,
                        Sprite.explosion_vertical_top_last2, _animate, t);
                break;
            case RIGHT1:
                sprite = Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1,
                        Sprite.explosion_horizontal_right_last2, _animate, t);

                break;
            case DOWN1:
                sprite = Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1,
                        Sprite.explosion_vertical_down_last2, _animate, t);
                break;
            case LEFT1:
                sprite = Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1,
                        Sprite.explosion_horizontal_left_last2, _animate, t);
                break;
            case CENTER:
                sprite = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1, Sprite.bomb_exploded2, _animate, t);
                break;
        }
    }

    public boolean isExplosion() {
        switch (type) {
            case UP1:
                if (map[yUnit + 1][xUnit] != '#' && map[yUnit][xUnit] != '#' && powerUp == true) {
                    return true;
                }
                break;
            case RIGHT1:
                if (map[yUnit][xUnit - 1] != '#' && map[yUnit][xUnit] != '#' && powerUp == true) {
                    return true;
                }
                break;
            case DOWN1:
                if (map[yUnit - 1][xUnit] != '#' && map[yUnit][xUnit] != '#' && powerUp == true) {
                    return true;
                }
                break;
            case LEFT1:
                if (map[yUnit][xUnit + 1] != '#' && map[yUnit][xUnit] != '#' && powerUp == true) {
                    return true;
                }
                break;
            default:
                if (map[yUnit][xUnit] != '#') {
                    return true;
                }
                break;
        }
        return false;
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