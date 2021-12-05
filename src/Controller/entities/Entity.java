package Controller.entities;

import Controller.graphics.Board;
import Controller.graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Entity extends Board {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected int xUnit;
    protected int yUnit;
    protected int _animate = 0;
    protected Text text;
    protected boolean isRemove;

    public static final int UP = 0;
    public static final int RIGHT = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int CENTER = -1;

    protected Image img;
    protected Sprite sprite;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(int xUnit, int yUnit, Image img) {
        super();
        this.x = xUnit * Sprite.DEFAULT_SIZE;
        this.y = yUnit * Sprite.DEFAULT_SIZE;
        this.img = img;
        this.xUnit = xUnit;
        this.yUnit = yUnit;
    }

    public int getXUnit() {
        return xUnit;
    }

    public int getYUnit() {
        return yUnit;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Text getText() {
        return text;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    public abstract void update();

    public void set_animate(int MAX_ANIMATE) {
        if (_animate < MAX_ANIMATE) {
            _animate++;
        } else {
            _animate = 0;
        }
    }
}
