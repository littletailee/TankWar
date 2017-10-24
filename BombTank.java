package TankWar;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import TankWar.BombTank;

public class BombTank {

    private int x, y;
    private boolean alive = true;
    private Level level;
    private static Toolkit tk = Toolkit.getDefaultToolkit();
    private static Image[] imgs = {
        tk.getImage(BombTank.class.getResource(
        "images/1.gif")),
        tk.getImage(BombTank.class.getResource(
        "images/2.gif")),
        tk.getImage(BombTank.class.getResource(
        "images/3.gif")),
        tk.getImage(BombTank.class.getResource(
        "images/4.gif")),
        tk.getImage(BombTank.class.getResource(
        "images/5.gif")),
        tk.getImage(BombTank.class.getResource(
        "images/6.gif")),
        tk.getImage(BombTank.class.getResource(
        "images/7.gif")),
        tk.getImage(BombTank.class.getResource(
        "images/8.gif")),
        tk.getImage(BombTank.class.getResource(
        "images/9.gif")),
        tk.getImage(BombTank.class.getResource(
        "images/10.gif")),};
    private static int step = 0;

    public BombTank(int x, int y, Level level) {
        this.x = x;
        this.y = y;
        this.level = level;
    }

    public void draw(Graphics g) {

        if (!alive) {
            level.bombTanks.remove(this);
            return;
        }
        if (step == imgs.length) {
            alive = false;
            step = 0;
            return;
        }

        g.drawImage(imgs[step], x, y, null);
        step++;
    }
}
