package TankWar;

import java.awt.*;

public class BrickWall implements Block {

    private static final int width = 22;
    private static final int length = 21;
    private int x, y;

    private Level level;
    private static Toolkit tk = Toolkit.getDefaultToolkit();
    private static Image[] wallImags = null;

    static {
        wallImags = new Image[]{
            tk.getImage(BrickWall.class.getResource("Images/commonWall.gif")),};
    }

    public BrickWall(int x, int y, Level level) {
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
            b.getLevel().otherWall.remove(this);
            b.getLevel().homeWall.remove(this);
            return true;
        }
        return false;
    }

}
