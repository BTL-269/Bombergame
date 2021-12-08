package Controller.entities.enemy;

import Controller.BombermanGame;
import Controller.MainGame;
import Controller.entities.Entity;
import Controller.entities.Text;
import Controller.graphics.Sprite;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.Random;

public abstract class Enemy extends Entity {

    protected static final int SPEED = 1;
    protected final Random rd = new Random();
    protected boolean die = false;
    protected int finalAnimation = 90;
    protected int mark = 100;
    protected int direction;
    protected char enemy;
    protected Sprite[] sprites;

    public Enemy(int xUnit, int yUnit, Image img, char e) {
        super(xUnit, yUnit, img);
        MainGame.numberEnemies++;
        sprites = Sprite.setEnemy(e);
        sprite = sprites[0];
        enemy = e;
    }

    public abstract void moveEnemy();

    public boolean move() {
        boolean ans = false;
        int _x = x;
        int _y = y;

        if (!die) {
            if (direction == 0) _x += SPEED;  // right
            if (direction == 1) _x -= SPEED;  // left
            if (direction == 2) _y += SPEED;  // down
            if (direction == 3) _y -= SPEED;  // up
            chooseSprite(direction);

            if (canMove(_x, _y)) {
                ans = true;
                if (enemy == '4' || enemy == '5') {
                    if (map[y / Sprite.DEFAULT_SIZE][x / Sprite.DEFAULT_SIZE] == enemy) {
                        map[y / Sprite.DEFAULT_SIZE][x / Sprite.DEFAULT_SIZE] = ' ';
                    }
                    if (map[_y / Sprite.DEFAULT_SIZE][_x / Sprite.DEFAULT_SIZE] == ' ') {
                        map[_y / Sprite.DEFAULT_SIZE][_x / Sprite.DEFAULT_SIZE] = enemy;
                    }
                } else {
                    map[y / Sprite.DEFAULT_SIZE][x / Sprite.DEFAULT_SIZE] = ' ';
                    map[_y / Sprite.DEFAULT_SIZE][_x / Sprite.DEFAULT_SIZE] = enemy;
                }
                x = _x;
                y = _y;
            }
        }
        return ans;
    }

    public boolean canMove(int x, int y) {
        if (direction == 0) x += Sprite.DEFAULT_SIZE - SPEED;
        if (direction == 2) y += Sprite.DEFAULT_SIZE - SPEED;

        char c = map[y / Sprite.DEFAULT_SIZE][x / Sprite.DEFAULT_SIZE];

        if (c == '-') {
            die = true;
            MainGame.numberEnemies--;
            MainGame.playScore += mark;
            if (BombermanGame.playAudio % 2 == 0) {
                BombermanGame.soundMonsterDead.play();
            }
            return false;
        }

        if (enemy == '5' || enemy == '4') {
            return c != '#' && c != '+';
        }

        if (direction == 0 || direction == 2) {
            return c == ' ' || (c >= '1' && c <= '5' && c != enemy);
        }
        if (direction == 1 || direction == 3) {
            return c == ' ' || (c >= '1' && c <= '5');
        }
        return false;
    }

    public void chooseSprite(int a) {
        switch (a) {
            case 0:
            case 2:
                sprite = Sprite.movingSprite(sprites[0], sprites[1], sprites[2], _animate, 60);
                break;
            case 1:
            case 3:
                sprite = Sprite.movingSprite(sprites[3], sprites[4], sprites[5], _animate, 60);
                break;
        }
    }

    public void afterDie() {
        sprite = Sprite.movingSprite(sprites[6], Sprite.spriteNull, _animate, 30);
        if (finalAnimation > 0) --finalAnimation;
        else isRemove = true;
        map[y / Sprite.DEFAULT_SIZE][x / Sprite.DEFAULT_SIZE] = ' ';
        text = new Text(x + 16, y + 16, "+" + mark, Color.WHITE);
        if (finalAnimation == 0) {
            text = null;
        }
    }

    @Override
    public void update() {
        if (die) {
            afterDie();
        } else {
            moveEnemy();
        }
        set_animate(2000);
        if (!isRemove) img = sprite.getFxImage();
        else img = null;
    }
}

