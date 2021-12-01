package entities;

import graphics.Board;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Entity extends Board {

    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int CENTER = -1;
    public static final int MAX_ANIMATE = 2000;

    protected int x; // toa do pixel x tinh tu ben trai sang
    protected int y; // toa do pixel y tinh tu tren xuong
    protected int xUnit; // toa do x tren map
    protected int yUnit; // toa do y tren map
    protected Image img;
    protected Text text;
    protected int animate = 0;
    protected boolean isRemove;
    protected Sprite sprite;

    public int getXUnit() {
        return xUnit;
    }

    public int getYUnit() {
        return yUnit;
    }

    public Text getText() {
        return text;
    }

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(int xUnit, int yUnit, Image img) {
        super();
        this.x = xUnit * Sprite.DEFAULT_SIZE;
        this.y = yUnit * Sprite.DEFAULT_SIZE;
        this.img = img;
        this.xUnit = xUnit;
        this.yUnit = yUnit;
        isRemove = false;
    }

    public void setImg() {
        if (isRemove) img = null;
    }

    public void render(GraphicsContext gc) {
        setImg();
        gc.drawImage(img, x, y);
    }

    public abstract void update();

    public void set_animate() {
        if (animate < MAX_ANIMATE) {
            animate++;
        } else {
            animate = 0;
        }
    }
}
