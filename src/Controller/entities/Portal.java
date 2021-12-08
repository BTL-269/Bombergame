package Controller.entities;

import Controller.graphics.Sprite;
import javafx.scene.image.Image;

public class Portal extends Tiles {

    public Portal(int xUnit, int yUnit, Image img) {
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
                show = true;
            }
        }
        if (show) {
            map[yUnit][xUnit] = 'X';
            sprite = Sprite.portal;
            img = sprite.getFxImage();
        }
    }
}
