package Controller.entities.enemy;

import Controller.MainGame;
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
    protected Sprite[] sprites;

    public Enemy(int xUnit, int yUnit, Image img, char e) {
        super(xUnit, yUnit, img);
        sprites = Sprite.setEnemy(e);
        sprite = sprites[0];
        enemy = e;
    }

    public abstract void moveEnemy();

    public abstract int findDirection();

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
        MainGame.numberEnemies--;
        sprite = Sprite.movingSprite(sprites[6], Sprite.grass, _animate, 30);
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

