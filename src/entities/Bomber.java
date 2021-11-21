package entities;
import graphics.Board;
import graphics.Sprite;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Bomber extends Entity {

    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;

    protected Sprite sprite;
    public Bomb bomb = new Bomb(1, 1, null);

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        Sprite.setPlayer();
        Sprite.setMap();
    }

    public boolean alive = true;
    private int tym = 3;
    private long timeDie;

    @Override
    public void update() {
        set_animate(1000);
        isAlive();
        if (!alive) {
            if (System.currentTimeMillis() - bomb.timeStart > 4000) {
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
            y_ -= 4;
            type = 0;
        }
        if (event.getCode() == KeyCode.DOWN) {
            y_ += 4;
            type = 2;
        }
        if (event.getCode() == KeyCode.LEFT) {
            x_ -= 4;
            type = 3;
        }
        if (event.getCode() == KeyCode.RIGHT) {
            x_ += 4;
            type = 1;
        }
        if (event.getCode() == KeyCode.SPACE) {
            bomb.setXY(xUnit, (y + 30) / Sprite.DEFAULT_SIZE); // + kí hiệu khi player đặt bomb
            bomb.timeStart = System.currentTimeMillis();
            bomb.check = true;
            type = 4;
        }
        chooseSprite(type);
        if (canMove(x_, y_, type) && type != 4) {
            map[y / Sprite.DEFAULT_SIZE][x / Sprite.DEFAULT_SIZE] = ' ';
            map[y_ / Sprite.DEFAULT_SIZE][x_ / Sprite.DEFAULT_SIZE] = 'p';
            x = x_;
            y = y_;
            xUnit = x_ / Sprite.DEFAULT_SIZE;
            yUnit = y_ / Sprite.DEFAULT_SIZE;
            if (bomb.check == true && ((bomb.xUnit != xUnit && bomb.xUnit != (x + 24) / Sprite.DEFAULT_SIZE)
                    || (bomb.yUnit != yUnit && bomb.yUnit != (y + 30) / Sprite.DEFAULT_SIZE))) {
                map[bomb.yUnit][bomb.xUnit] = '+';
            }
        }
    }

    public boolean canMove(int x, int y, int type) {
        int x_ = (x + 24) / Sprite.DEFAULT_SIZE;
        int y_ = (y + 30) / Sprite.DEFAULT_SIZE;
        x = x / Sprite.DEFAULT_SIZE;
        y = y / Sprite.DEFAULT_SIZE;
        if (map[y][x] == '-' && map[y][x_] == '-' && map[y_][x] == '-' && map[y_][x_] == '-') {
            return true;
        }
        if ((map[y][x] == 'p' || map[y][x] == ' ')
                && (map[y][x_] == 'p' || map[y][x_] == ' ')
                && (map[y_][x] == 'p' || map[y_][x] == ' ' )
                && (map[y_][x_] == 'p' || map[y_][x_] == ' ')) {
            return true;
        }
        return false;
    }

    /** Chọn hoạt ảnh */
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
        int x_ = (x + 24) / Sprite.DEFAULT_SIZE;
        int y_ = (y + 30) / Sprite.DEFAULT_SIZE;
        int x = xUnit;
        int y = yUnit;
        if (map[y][x] == '-' || map[y][x_] == '-' || map[y_][x] == '-' || map[y_][x_] == '-') {
            timeDie = System.currentTimeMillis();
            alive = false;
            //return false;
        }
        //return true;
    }

    public void die() {
        sprite = Sprite.movingSprite(Sprite.player_down, Sprite.grass,_animate, 60);
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
            map[bomb.yUnit][bomb.xUnit] = ' ';
            bomb.check = false;
            alive = true;
        } else {
            System.out.println("LOSE");
        }
    }
}
