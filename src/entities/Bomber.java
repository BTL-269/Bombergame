package entities;
import graphics.Sprite;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Bomber extends Entity {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    protected int _animate = 0;
    protected final int MAX_ANIMATE = 750;

    protected Sprite sprite = Sprite.player_right;
    public Bomb bomb = new Bomb(-1, -1, Sprite.bomb_exploded1.getFxImage());

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        //handle();
        if (_animate < MAX_ANIMATE) {
            _animate++;
        } else {
            _animate = 0;
        }
        img = sprite.getFxImage();
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
            if (bomb.xUnit != -1) {
                map[bomb.yUnit][bomb.xUnit] = ' ';
            }
            bomb.setXY(xUnit, yUnit); // + kí hiệu khi player đặt bomb
            type = 4;
        }
        chooseSprite(type);
        if (canMove(x_, y_, type) && type != 4) {
            map[y / Sprite.SCALED_SIZE][x / Sprite.SCALED_SIZE] = ' ';
            map[y_ / Sprite.SCALED_SIZE][x_ / Sprite.SCALED_SIZE] = 'p';
            x = x_;
            y = y_;
            xUnit = x_ / Sprite.SCALED_SIZE;
            yUnit = y_ / Sprite.SCALED_SIZE;
            if (bomb.xUnit != -1 && ((bomb.xUnit != xUnit && bomb.xUnit != (x + 24) / Sprite.SCALED_SIZE)
                    || (bomb.yUnit != yUnit && bomb.yUnit != (y + 30) / Sprite.SCALED_SIZE))) {
                map[bomb.yUnit][bomb.xUnit] = '+';
            }
        }
    }

    public boolean canMove(int x, int y, int type) {
        int x_ = (x + 24) / Sprite.SCALED_SIZE;
        int y_ = (y + 30) / Sprite.SCALED_SIZE;
        x = x / Sprite.SCALED_SIZE;
        y = y / Sprite.SCALED_SIZE;
        if ((map[y][x] == 'p' || map[y][x] == ' ')
                && (map[y][x_] == 'p' || map[y][x_] == ' ')
                && (map[y_][x] == 'p' || map[y_][x] == ' ' )
                && (map[y_][x_] == 'p' || map[y_][x_] == ' ')) {
            return true;
        }
        return false;
    }

    public void chooseSprite(int type) {
        switch (type) {
            case 0:
                sprite = Sprite.movingSprite(Sprite.player_up, Sprite.player_up_1, Sprite.player_up_2, _animate, 20);
                break;
            case 1:
                sprite = Sprite.movingSprite(Sprite.player_right, Sprite.player_right_1, Sprite.player_right_2, _animate, 20);
                break;
            case 2:
                sprite = Sprite.movingSprite(Sprite.player_down, Sprite.player_down, Sprite.player_down_2, _animate, 20);
                break;
            case 3:
                sprite = Sprite.movingSprite(Sprite.player_left, Sprite.player_left_1, Sprite.player_left_2, _animate, 20);
                break;
        }
    }
}
