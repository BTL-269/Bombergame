package Controller.entities.enemy.AI;

import Controller.MainGame;
import Controller.entities.enemy.Enemy;
import Controller.graphics.Sprite;
import javafx.scene.image.Image;

import java.util.Random;

public class AILow extends Enemy {

    private Random rd = new Random();

    public AILow(int xUnit, int yUnit, Image img, char enemy) {
        super(xUnit, yUnit, img, enemy);
        direction = rd.nextInt(4);
    }

    public boolean canMove(int x, int y) {
        if (direction == 0) x += Sprite.DEFAULT_SIZE - SPEED;
        if (direction == 2) y += Sprite.DEFAULT_SIZE - SPEED;

        char c = map[y / Sprite.DEFAULT_SIZE][x / Sprite.DEFAULT_SIZE];

        if (c == '-') {
            die = true;
            MainGame.playScore += mark;
            return false;
        }

        if (direction == 0 || direction == 2) {
            return c == ' ' || (c >= '1' && c <= '5' && c != enemy);
        }
        if (direction == 1 || direction == 3) {
            return c == ' ' || (c >= '1' && c <= '5');
        }
        return false;
    }

    @Override
    public void moveEnemy() {
        int _x = x;
        int _y = y;

        if (die) {
            afterDie();
            return;
        }

        if (direction == 0) _x += SPEED;  // right
        if (direction == 1) _x -= SPEED;  // left
        if (direction == 2) _y += SPEED;  // down
        if (direction == 3) _y -= SPEED;  // up
        chooseSprite(direction);

        if (canMove(_x, _y)) {
            map[y / Sprite.DEFAULT_SIZE][x / Sprite.DEFAULT_SIZE] = ' ';
            x = _x;
            y = _y;
            map[y / Sprite.DEFAULT_SIZE][x / Sprite.DEFAULT_SIZE] = enemy;
        } else {
            direction = rd.nextInt(4);
        }
    }
}

