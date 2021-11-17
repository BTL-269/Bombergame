package entities.emeny;

import entities.Entity;
import graphics.Map;
import graphics.Sprite;
import javafx.scene.image.Image;

public abstract class Enemy extends Entity {

    protected char[][] map;
    protected int direction;
    protected Sprite sprite;
    protected int animate;
    private final int MAX_ANIMATE = 750;
    private static final int SPEED = 1;

    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public abstract void chooseSprite(int a);

    public void moveEnemy() {
        chooseSprite(direction);
        img = sprite.getFxImage();
        switch (direction) {
            case 0: // right
                xUnit = x / Sprite.SCALED_SIZE;
                yUnit = y / Sprite.SCALED_SIZE;
                //System.out.print("Case 0: " + x + " " + y + " " + xUnit + " " + yUnit + '\n');
                if (map[yUnit][xUnit + 1] == ' ') {
                    map[yUnit][xUnit] = ' ';
                    x += SPEED;
                    map[yUnit][x / Sprite.SCALED_SIZE] = '1';
                }
                else direction = 1;
                break;
            case 1: // left
                xUnit = (x - 1) / Sprite.SCALED_SIZE;
                yUnit = y / Sprite.SCALED_SIZE;
                //System.out.print("Case 1: " + x + " " + y + " " + xUnit + " " + yUnit + '\n');
                if (map[yUnit][xUnit] == ' ' || map[yUnit][xUnit] == '1') {
                    map[yUnit][x / Sprite.SCALED_SIZE] = ' ';
                    x -= SPEED;
                    map[yUnit][xUnit] = '1';
                }
                else direction = 2;
                break;
            case 2: // down
                xUnit = x / Sprite.SCALED_SIZE;
                yUnit = y / Sprite.SCALED_SIZE;
                //System.out.print("Case 2: " + x + " " + y + " " + xUnit + " " + yUnit + '\n');
                if (map[yUnit + 1][xUnit] == ' ') {
                    map[yUnit][xUnit] = ' ';
                    y += SPEED;
                    map[y / Sprite.SCALED_SIZE][xUnit] = '1';
                }
                else direction = 3;
                break;
            case 3: //up
                xUnit = x / Sprite.SCALED_SIZE;
                yUnit = (y - 1) / Sprite.SCALED_SIZE;
                //System.out.print("Case 3: " + x + " " + y + " " + xUnit + " " + yUnit + '\n');
                if (map[yUnit][xUnit] == ' ' || map[yUnit][xUnit] == '1') {
                    map[y / Sprite.SCALED_SIZE][xUnit] = ' ';
                    y -= SPEED;
                    map[yUnit][xUnit] = '1';
                }
                else direction = 0;
                break;
        }
    }

    @Override
    public void update() {
        if(animate < MAX_ANIMATE) {
            animate++;
        }
        else {
            animate = 0;
        }
        moveEnemy();
    }
}
