package entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Text {

    private int x, y;
    private String text;
    private Color color;

    public Text(int x, int y, String text, Color color) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.color = color;
    }

    public void renderText(GraphicsContext gc) {
        gc.fillText(text, x, y);
    }
}
