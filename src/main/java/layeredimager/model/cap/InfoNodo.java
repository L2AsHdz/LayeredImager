package layeredimager.model.cap;

/**
 *
 * @date 11/04/2021
 * @time 15:14:55
 * @author asael
 */
public class InfoNodo {

    private int x;
    private int y;
    private int color;

    public InfoNodo() {
    }

    public InfoNodo(int color, int x, int y) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
