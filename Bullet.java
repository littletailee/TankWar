package TankWar;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import TankWar.Direction;
import TankWar.TankClient;

public class Bullet implements Block {

    private static int speedX = 12;
    private static int speedY = 12;

    public static final int width = 10;
    public static final int length = 10;

    private int x, y;
    private Direction diretion;

    private boolean good;
    private boolean alive = true;

    private Level level;

    private static Toolkit tk = Toolkit.getDefaultToolkit();
    private static Image[] bulletImages = null;
    private static Map<String, Image> imgs = new HashMap<String, Image>();

    static {
        bulletImages = new Image[]{
            tk.getImage(Bullet.class.getResource(
            "Images/bulletL.gif")),
            tk.getImage(Bullet.class.getResource(
            "Images/bulletU.gif")),
            tk.getImage(Bullet.class.getResource(
            "Images/bulletR.gif")),
            tk.getImage(Bullet.class.getResource(
            "Images/bulletD.gif")),};

        imgs.put("L", bulletImages[0]);

        imgs.put("U", bulletImages[1]);

        imgs.put("R", bulletImages[2]);

        imgs.put("D", bulletImages[3]);

    }

    public Bullet(int x, int y, Direction dir) {
        this.x = x;
        this.y = y;
        this.diretion = dir;
    }

    public Bullet(int x, int y, boolean good, Direction dir, Level level) {
        this(x, y, dir);
        this.good = good;
        this.level = level;
    }

    private void move() {

        switch (diretion) {
            case L:
                x -= speedX;
                break;

            case U:
                y -= speedY;
                break;

            case R:
                x += speedX;
                break;

            case D:
                y += speedY;
                break;

            case STOP:
                break;
        }

        if (x < 0 || y < 0 || x > TankClient.Fram_width
                || y > TankClient.Fram_length) {
            alive = false;
        }
    }

    public void draw(Graphics g) {
        if (!alive) {
            level.bullets.remove(this);
            return;
        }
        switch (diretion) {
            case L:
                g.drawImage(imgs.get("L"), x, y, null);
                break;

            case U:
                g.drawImage(imgs.get("U"), x, y, null);
                break;

            case R:
                g.drawImage(imgs.get("R"), x, y, null);
                break;

            case D:
                g.drawImage(imgs.get("D"), x, y, null);
                break;

        }

        move();
    }

    public boolean isLive() {
        return alive;
    }

    @Override
    public Rectangle getRect() {
        return new Rectangle(x, y, width, length);
    }

    @Override
    public boolean hitedBy(Bullet b) {
        if (this.getAlive() && this.getRect().intersects(b.getRect()) && b.isLive()) {
            this.level.bullets.remove(this);
            this.level.bullets.remove(b);
            return true;
        }
        return false;
    }

    public boolean hit(Block b) {
        return b.hitedBy(this);
    }

    public boolean getAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isGood() {
        return good;
    }

    public Level getLevel() {
        return this.level;
    }
}
