package TankWar;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class MetalWall implements Block {

    public static final int width = 36;
    public static final int length = 37;
    private int x, y;
    Level level;
    private static Toolkit tk = Toolkit.getDefaultToolkit();
    private static Image[] wallImags = null;

    static {
        wallImags = new Image[]{tk.getImage(BrickWall.class
            .getResource("Images/metalWall.gif")),};
    }

    public MetalWall(int x, int y, Level level) {
        this.x = x;
        this.y = y;
        this.level = level;
    }

    public void draw(Graphics g) {
        g.drawImage(wallImags[0], x, y, null);
    }

    public Rectangle getRect() {
        return new Rectangle(x, y, width, length);
    }

    public boolean hitedBy(Bullet b) {

        if (b.getAlive() && b.getRect().intersects(this.getRect())) {
            b.setAlive(false);
            return true;
        }
        return false;
    }

}
