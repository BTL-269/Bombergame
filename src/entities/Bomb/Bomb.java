package entities.Bomb;

import entities.Entity;
import graphics.Sprite;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Bomb extends Entity {

    public long timeStart;  // thời gian bomb bắt đầu được đặt
    public boolean check = false; // kiểm tra bomb đã được đặt chưa.
    public List<Explosion> explosions = new ArrayList<>();

    private boolean powerUp = false;

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
        explosions.add(new Explosion(xUnit, yUnit, null, CENTER));
        explosions.add(new Explosion(xUnit, yUnit - 1, null, UP));
        explosions.add(new Explosion(xUnit + 1, yUnit, null, RIGHT));
        explosions.add(new Explosion(xUnit, yUnit + 1, null, DOWN));
        explosions.add(new Explosion(xUnit - 1, yUnit, null, LEFT));
        explosions.add(new Explosion(xUnit, yUnit - 2, null, Explosion.UP1));
        explosions.add(new Explosion(xUnit + 2, yUnit, null, Explosion.RIGHT1));
        explosions.add(new Explosion(xUnit, yUnit + 2, null, Explosion.DOWN1));
        explosions.add(new Explosion(xUnit - 2, yUnit, null, Explosion.LEFT1));
    }

    /**
     * Cài đặt vị trị của bomb.
     */
    public void setXY(int xUnit, int yUnit) {
        this.x = xUnit * Sprite.DEFAULT_SIZE;
        this.y = yUnit * Sprite.DEFAULT_SIZE;
        this.xUnit = xUnit;
        this.yUnit = yUnit;
        explosions.get(0).setXY(xUnit, yUnit);
        explosions.get(1).setXY(xUnit, yUnit - 1);
        explosions.get(2).setXY(xUnit + 1, yUnit);
        explosions.get(3).setXY(xUnit, yUnit + 1);
        explosions.get(4).setXY(xUnit - 1, yUnit);
        explosions.get(5).setXY(xUnit, yUnit - 2);
        explosions.get(6).setXY(xUnit + 2, yUnit);
        explosions.get(7).setXY(xUnit, yUnit + 2);
        explosions.get(8).setXY(xUnit - 2, yUnit);
    }

    @Override
    public void update() {
        if (check) {
            if (System.currentTimeMillis() - timeStart < 3000) {
                set_animate();
                sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, animate, 80);
                img = sprite.getFxImage();
            } else {
                check = false;
                //map[yUnit][xUnit] = ' ';
                img = null;
                for (Explosion explosion : explosions) {
                    if (powerUp) {
                        explosion.setPowerUp(true);
                    }
                    explosion.timeStart = System.currentTimeMillis();
                    if (explosion.isExplosion()) {
                        explosion.setCheck(true);
                    }
                }
            }
        }
    }

    public void setPowerUp(boolean powerUp) {
        this.powerUp = powerUp;
    }

    public boolean isCheck() {
        return check;
    }
}