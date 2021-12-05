package Controller.entities.enemy;

import Controller.entities.Entity;
import Controller.entities.Text;
import Controller.graphics.Sprite;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public abstract class Enemy extends Entity {

    protected static final int SPEED = 1;
    protected boolean die = false;
    protected int finalAnimation = 90;
    protected int mark = 100;
    protected int direction;
    protected char enemy;

    public Enemy(int xUnit, int yUnit, Image img, char enemy) {
        super(xUnit, yUnit, img);
        this.enemy = enemy;
        Sprite.setEnemy(enemy);
        sprite = Sprite.enemy_right1;
    }

    public abstract void moveEnemy();

    public void chooseSprite(int a) {
        switch (a) {
            case 0:
            case 2:
                sprite = Sprite.movingSprite(Sprite.enemy_right1, Sprite.enemy_right2, Sprite.enemy_right3, _animate, 60);
                break;
            case 1:
            case 3:
                sprite = Sprite.movingSprite(Sprite.enemy_left1, Sprite.enemy_left2, Sprite.enemy_left3, _animate, 60);
                break;
        }
    }

    public void afterDie() {
        sprite = Sprite.movingSprite(Sprite.enemy_dead, Sprite.grass, _animate, 30);
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
        set_animate(2000);
        if (!isRemove) img = sprite.getFxImage();
        else img = null;
        moveEnemy();
    }
}

