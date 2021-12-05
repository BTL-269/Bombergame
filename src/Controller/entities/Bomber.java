package Controller.entities;

import Controller.MainGame;
import Controller.MenuGame;
import Controller.graphics.Sprite;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Bomber extends Entity {

    public static Bomb bomb1 = new Bomb(2, 2, null);
    public static Bomb bomb2 = new Bomb(2, 2, null);

    private long timeDie;
    private int step = 4;

    public boolean alive = true;
    public boolean bombItem = false; // kiểm tra bomb item được ăn chưa
    public static boolean win = false; // kiểm tra đã thắng chưa, nếu thắng tăng level.
    public static boolean lose = false; // kiểm tra player đã thua chưa.

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        Sprite.setPlayer();
        Sprite.setMap();
    }

    @Override
    public void update() {
        set_animate(1000);
        isWin();
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
        if (event.getCode() == KeyCode.SHIFT) {
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
                    MainGame.soul++;
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
            x = x_;
            y = y_;
            xUnit = x_ / Sprite.DEFAULT_SIZE;
            yUnit = y_ / Sprite.DEFAULT_SIZE;
            if (map[yUnit][xUnit] < '0' || map[yUnit][xUnit] > '5') {
                map[yUnit][xUnit] = ' ';
            }
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
        if ((map[y][x] == ' ' || (map[y][x] >= '1' && map[y][x] <= '5') || map[y][x] == '-'
                || (map[y][x] == 'X' && MainGame.numberEnemies == 0)
                || map[y][x] == 'D' || map[y][x] == 'B' || map[y][x] == 'S' || map[y][x] == 'F')
                && (map[y][x_] == ' ' || (map[y][x_] >= '1' && map[y][x_] <= '5') || map[y][x_] == '-'
                || (map[y][x_] == 'X' && MainGame.numberEnemies == 0)
                || map[y][x_] == 'D' || map[y][x_] == 'B' || map[y][x_] == 'S' || map[y][x_] == 'F')
                && (map[y_][x] == ' ' || (map[y_][x] >= '1' && map[y_][x] <= '5') || map[y_][x] == '-'
                || (map[y_][x] == 'X' && MainGame.numberEnemies == 0)
                || map[y_][x] == 'D' || map[y_][x] == 'B' || map[y_][x] == 'S' || map[y_][x] == 'F')
                && (map[y_][x_] == ' ' || (map[y_][x_] >= '1' && map[y_][x_] <= '5') || map[y_][x_] == '-'
                || (map[y_][x_] == 'X' && MainGame.numberEnemies == 0)
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
                sprite = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, _animate, 20);
                break;
            case RIGHT:
                sprite = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, _animate, 20);
                break;
            case DOWN:
                sprite = Sprite.movingSprite(Sprite.player_down, Sprite.player_down_1, Sprite.player_down_2, _animate, 20);
                break;
            case LEFT:
                sprite = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, _animate, 20);
                break;
        }
    }

    public void isAlive() {
        int x_ = (this.x + 30) / Sprite.DEFAULT_SIZE;
        int y_ = (this.y + 30) / Sprite.DEFAULT_SIZE;
        int x = this.x / Sprite.DEFAULT_SIZE;
        int y = this.y / Sprite.DEFAULT_SIZE;
        /** EDIT */
        if (alive) {
            if (map[y][x] == '-' || map[y][x_] == '-' || map[y_][x] == '-' || map[y_][x_] == '-'
                    || (map[y][x] >= '1' && map[y][x] <= '5')
                    || (map[y_][x] >= '1' && map[y_][x] <= '5')
                    || (map[y][x_] >= '1' && map[y][x_] <= '5')
                    || (map[y_][x_] >= '1' && map[y_][x_] <= '5')) {
                timeDie = System.currentTimeMillis();
                alive = false;
            }
        }
    }

    public void die() {
        sprite = Sprite.movingSprite(Sprite.player_down, Sprite.grass, _animate, 60);
    }

    public void restart() {
        if (MainGame.soul > 1) {
            MainGame.soul--;
            xUnit = 1;
            yUnit = 1;
            x = xUnit * Sprite.DEFAULT_SIZE;
            y = yUnit * Sprite.DEFAULT_SIZE;
            sprite = Sprite.player_right;
            map[bomb1.yUnit][bomb1.xUnit] = ' ';
            bomb1.check = false;
            alive = true;
        } else {
            lose = true;
        }
    }

    public void isWin() {
        int x_ = (x + 12) / Sprite.DEFAULT_SIZE;
        int y_ = (y + 15) / Sprite.DEFAULT_SIZE;
        if (map[y_][x_] == 'X' && MainGame.numberEnemies == 0) {
            win = true;
        }
    }

    public void keyReleased(KeyEvent event) {
        if (event.getCode() == KeyCode.UP) {
            sprite = Sprite.player_up_1;
        }
        if (event.getCode() == KeyCode.RIGHT) {
            sprite = Sprite.player_right_1;
        }
        if (event.getCode() == KeyCode.DOWN) {
            sprite = Sprite.player_down_1;
        }
        if (event.getCode() == KeyCode.LEFT) {
            sprite = Sprite.player_left_1;
        }
    }
}
