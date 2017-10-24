package TankWar;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Home implements Block {

    public static final int width = 43, length = 43;
    private int x, y;
    private Level level;

    private boolean live = true;

    private static Toolkit tk = Toolkit.getDefaultToolkit();
    private static Image[] homeImags = null;

    static {
        homeImags = new Image[]{tk.getImage(BrickWall.class
            .getResource("Images/home.jpg")),};
    }

    public Home(int x, int y, Level level) {
        this.x = x;
        this.y = y;
        this.level = level;
    }

    public void gameOver(Graphics g) {

        level.tanks.clear();
        level.metalWall.clear();
        level.otherWall.clear();
        level.bombTanks.clear();
        level.theRiver.clear();
        level.trees.clear();
        level.bullets.clear();
        level.homeTank.setLive(false);
        if (level.player2 == true) {
            level.homeTank2.setLive(false);
        }
        Color c = g.getColor();
        g.setColor(Color.green);
        Font f = g.getFont();
        g.setFont(new Font(" ", Font.PLAIN, 40));
        g.setFont(f);
        g.setColor(c);

    }

    public void draw(Graphics g) {

        if (live) {
            g.drawImage(homeImags[0], x, y, null);

            for (int i = 0; i < level.homeWall.size(); i++) {
                BrickWall w = level.homeWall.get(i);
                w.draw(g);
            }
        } else {
            gameOver(g);

        }
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    @Override
    public Rectangle getRect() {

        return new Rectangle(x, y, width, length);
    }

    @Override
    public boolean hitedBy(Bullet b) {
        if (b.getAlive() && b.getRect().intersects(this.getRect())) {
            b.setAlive(false);
            b.getLevel().home.setLive(false);
            return true;
        }
        return false;
    }

}
