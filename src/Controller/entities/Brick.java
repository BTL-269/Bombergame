package Controller.entities;

import javafx.scene.image.Image;

public class Brick extends Tiles {
    public Brick(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        if (isCollide() && !show) {
            if (System.currentTimeMillis() - sTime < 1000) {
                set_animate(1000);
                broken();
                img = sprite.getFxImage();
            } else {
                map[yUnit][xUnit] = ' ';
                show = true;
                img = null;
            }
        }
    }
}
