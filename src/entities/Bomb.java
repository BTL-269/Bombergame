package entities;

import graphics.Sprite;
import javafx.scene.image.Image;

public class Bomb extends Entity {
    protected int _animate = 0;
    protected final int MAX_ANIMATE = 750;

    protected Sprite sprite = Sprite.bomb;

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }

    public void setXY(int xUnit, int yUnit) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.xUnit = xUnit;
        this.yUnit = yUnit;
    }

    @Override
    public void update() {

        if (_animate < MAX_ANIMATE) {
            _animate++;
        } else {
            _animate = 0;
        }
        sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, _animate, 60);
        img = sprite.getFxImage();
    }
}
