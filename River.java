package TankWar;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class River implements Block {

    public static final int riverWidth = 55;
    public static final int riverLength = 154;
    private int x, y;
    Level level;

    private static Toolkit tk = Toolkit.getDefaultToolkit();
    private static Image[] riverImags = null;

    static {
        riverImags = new Image[]{
            tk.getImage(BrickWall.class.getResource("Images/river.jpg")),};
    }

    public River(int x, int y, Level level) {
        this.x = x;
        this.y = y;
        this.level = level;
    }

    public void draw(Graphics g) {
        g.drawImage(riverImags[0], x, y, null);
    }

    public static int getRiverWidth() {
        return riverWidth;
    }

    public static int getRiverLength() {
        return riverLength;
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

    public Rectangle getRect() {
        return new Rectangle(x, y, riverWidth, riverLength);
    }

    @Override
    public boolean hitedBy(Bullet b) {
        if (b.getAlive() && b.getRect().intersects(this.getRect())) {
            return true;
        }
        return false;
    }
}
