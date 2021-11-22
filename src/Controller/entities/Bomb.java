package Controller.entities;


import Controller.graphics.Sprite;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;


public class Bomb extends Entity {

    protected Sprite sprite = Sprite.bomb;

    public long timeStart;
    public boolean check = false; // kiểm tra bomb đã được đặt chưa.
    public List<Explosion> explosions = new ArrayList<>();

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
        explosions.add(new Explosion(xUnit, yUnit - 1, null, 0));
        explosions.add(new Explosion(xUnit + 1, yUnit, null, 1));
        explosions.add(new Explosion(xUnit, yUnit + 1, null, 2));
        explosions.add(new Explosion(xUnit - 1, yUnit, null, 3));
        explosions.add(new Explosion(xUnit, yUnit, null, 4));
    }

    public void setXY(int xUnit, int yUnit) {
        this.x = xUnit * Sprite.DEFAULT_SIZE;
        this.y = yUnit * Sprite.DEFAULT_SIZE;
        this.xUnit = xUnit;
        this.yUnit = yUnit;
        explosions.get(0).setXY(xUnit, yUnit - 1);
        explosions.get(1).setXY(xUnit + 1, yUnit);
        explosions.get(2).setXY(xUnit, yUnit + 1);
        explosions.get(3).setXY(xUnit - 1, yUnit);
        explosions.get(4).setXY(xUnit, yUnit);
    }

    @Override
    public void update() {
        if (check == true) {
            if (System.currentTimeMillis() - timeStart < 3000) {
                set_animate(2000);
                sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, _animate, 80);
                img = sprite.getFxImage();
            } else {
                check = false;
                map[yUnit][xUnit] = ' ';
                img = null;
                for (Explosion explosion : explosions) {
                    explosion.setCheck(true);
                    explosion.timeStart = System.currentTimeMillis();
                }
            }
        }
    }
}
