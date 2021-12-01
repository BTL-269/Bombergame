package entities;

import entities.Bomb.Bomb;
import graphics.Sprite;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Bomber extends Entity {

    public static Bomb bomb1 = new Bomb(2, 2, null);
    public static Bomb bomb2 = new Bomb(2, 2, null);

    private int step = 4;
    private boolean bombItem = false; // kiểm tra bomb item được ăn chưa
    private int tym = 3; // số mạng ban đầu của player

    public boolean alive = true;
    private long timeDie;

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        Sprite.setPlayer();
        Sprite.setMap();
    }

    @Override
    public void update() {
        set_animate();
        isAlive();
        if (!alive) {
            if (System.currentTimeMillis() - bomb1.timeStart > 4000) {
                if (System.currentTimeMillis() - timeDie < 1000) {
                    die();
                } else {
                    restart();
                }
            }
        }
        if (sprite != null) {
            img = sprite.getFxImage();
        }
    }

    public void handle(KeyEvent event) {
        int x_ = x;
        int y_ = y;
        int type = -1;
        if (event.getCode() == KeyCode.UP) {
            y_ -= step;
            type = UP;
        }
        if (event.getCode() == KeyCode.DOWN) {
            y_ += step;
            type = DOWN;
        }
        if (event.getCode() == KeyCode.LEFT) {
            x_ -= step;
            type = LEFT;
        }
        if (event.getCode() == KeyCode.RIGHT) {
            x_ += step;
            type = RIGHT;
        }
        if (event.getCode() == KeyCode.SPACE) {
            if (System.currentTimeMillis() - bomb1.timeStart >= 4000) {
                bomb1.setXY((x + 12) / Sprite.DEFAULT_SIZE, (y + 15) / Sprite.DEFAULT_SIZE); // + kí hiệu khi player đặt bomb
                bomb1.timeStart = System.currentTimeMillis();
                bomb1.check = true;
                type = 4;
            } else {
                if (bombItem && System.currentTimeMillis() - bomb2.timeStart > 4000) {
                    bomb2.setXY((x + 12) / Sprite.DEFAULT_SIZE, (y + 15) / Sprite.DEFAULT_SIZE); // + kí hiệu khi player đặt bomb
                    bomb2.timeStart = System.currentTimeMillis();
                    bomb2.check = true;
                    type = 4;
                }
            }
        }
        chooseSprite(type);
        if (canMove(x_, y_) && type != 4) {
            switch (map[y_ / Sprite.DEFAULT_SIZE][x_ / Sprite.DEFAULT_SIZE]) {
                case 'D': // alive item
                    tym++;
                    break;
                case 'B': // bomb item
                    bombItem = true;
                    break;
                case 'S': // speed item
                    step += 4;
                    break;
                case 'F': // flame item
                    bomb1.setPowerUp(true);
                    bomb2.setPowerUp(true);
                    break;
            }
            map[y_ / Sprite.DEFAULT_SIZE][x_ / Sprite.DEFAULT_SIZE] = ' ';
            x = x_;
            y = y_;
            xUnit = x_ / Sprite.DEFAULT_SIZE;
            yUnit = y_ / Sprite.DEFAULT_SIZE;
            if (bomb1.check && ((bomb1.xUnit != xUnit && bomb1.xUnit != (x + 20) / Sprite.DEFAULT_SIZE)
                    || (bomb1.yUnit != yUnit && bomb1.yUnit != (y + 25) / Sprite.DEFAULT_SIZE))) {
                map[bomb1.yUnit][bomb1.xUnit] = '+';
            }
            /** nếu bomb item được ăn thì bomb2 được cài đặt. */
            if (bombItem && bomb2.check && ((bomb2.xUnit != xUnit && bomb2.xUnit != (x + 20) / Sprite.DEFAULT_SIZE)
                    || (bomb2.yUnit != yUnit && bomb2.yUnit != (y + 25) / Sprite.DEFAULT_SIZE))) {
                map[bomb2.yUnit][bomb2.xUnit] = '+';
            }
        }
    }

    public boolean canMove(int x, int y) {
        int x_ = (x + 20) / Sprite.DEFAULT_SIZE;
        int y_ = (y + 25) / Sprite.DEFAULT_SIZE;
        x = x / Sprite.DEFAULT_SIZE;
        y = (y + 3) / Sprite.DEFAULT_SIZE;
        if (map[y][x] == '-' && map[y][x_] == '-' && map[y_][x] == '-' && map[y_][x_] == '-') {
            return true;
        }
        if ((map[y][x] == ' '
                || map[y][x] == 'D' || map[y][x] == 'B' || map[y][x] == 'S' || map[y][x] == 'F')
                && (map[y][x_] == ' '
                || map[y][x_] == 'D' || map[y][x_] == 'B' || map[y][x_] == 'S' || map[y][x_] == 'F')
                && (map[y_][x] == ' '
                || map[y_][x] == 'D' || map[y_][x] == 'B' || map[y_][x] == 'S' || map[y_][x] == 'F')
                && (map[y_][x_] == ' '
                || map[y_][x_] == 'D' || map[y_][x_] == 'B' || map[y_][x_] == 'S' || map[y_][x_] == 'F')) {
            return true;
        }
        return false;
    }

    /**
     * Chọn hoạt ảnh
     */
    public void chooseSprite(int type) {
        switch (type) {
            case UP:
                sprite = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, animate, 20);
                break;
            case RIGHT:
                sprite = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, animate, 20);
                break;
            case DOWN:
                sprite = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, animate, 20);
                break;
            case LEFT:
                sprite = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, animate, 20);
                break;
        }
    }

    public void isAlive() {
        int x_ = (x + 24) / Sprite.DEFAULT_SIZE;
        int y_ = (y + 30) / Sprite.DEFAULT_SIZE;
        int x = xUnit;
        int y = yUnit;
        if (map[y][x] == '-' || map[y][x_] == '-' || map[y_][x] == '-' || map[y_][x_] == '-') {
            timeDie = System.currentTimeMillis();
            alive = false;
        }
    }

    public void die() {
        sprite = Sprite.movingSprite(Sprite.player_down, Sprite.grass, animate, 60);
    }

    public void restart() {
        if (tym > 0) {
            tym--;
            System.out.println("tym = " + tym);
            xUnit = 1;
            yUnit = 1;
            x = xUnit * Sprite.DEFAULT_SIZE;
            y = yUnit * Sprite.DEFAULT_SIZE;
            sprite = Sprite.player_right;
            map[bomb1.yUnit][bomb1.xUnit] = ' ';
            bomb1.check = false;
            alive = true;
        } else {
            System.out.println("LOSE");
        }
    }
}
