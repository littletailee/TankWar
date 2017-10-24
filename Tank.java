package TankWar;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Tank implements Block {

    public static int speedX = 6, speedY = 6;
    public static final int width = 35, length = 35;
    private Direction direction = Direction.STOP;
    private Direction Kdirection = Direction.U;
    private Level level;
    private int player = 0;
    private boolean good;
    private int x, y;
    private int oldX, oldY;
    private boolean live = true;
    private int life = 200;
    private int rate = 1;
    private static Random r = new Random();
    private int step = r.nextInt(10) + 5;
    private boolean bL = false, bU = false, bR = false, bD = false;

    private static Toolkit tk = Toolkit.getDefaultToolkit();
    private static Image[] tankImags = null;

    static {
        tankImags = new Image[]{
            tk.getImage(BombTank.class.getResource("Images/tankD.gif")),
            tk.getImage(BombTank.class.getResource("Images/tankU.gif")),
            tk.getImage(BombTank.class.getResource("Images/tankL.gif")),
            tk.getImage(BombTank.class.getResource("Images/tankR.gif")),
            tk.getImage(BombTank.class.getResource("Images/HtankD.gif")),
            tk.getImage(BombTank.class.getResource("Images/HtankU.gif")),
            tk.getImage(BombTank.class.getResource("Images/HtankL.gif")),
            tk.getImage(BombTank.class.getResource("Images/HtankR.gif")),
            tk.getImage(BombTank.class.getResource("Images/HtankD2.gif")),
            tk.getImage(BombTank.class.getResource("Images/HtankU2.gif")),
            tk.getImage(BombTank.class.getResource("Images/HtankL2.gif")),
            tk.getImage(BombTank.class.getResource("Images/HtankR2.gif")),};

    }

    public Tank(int x, int y, boolean good) {
        this.x = x;
        this.y = y;
        this.oldX = x;
        this.oldY = y;
        this.good = good;
    }

    public Tank(int x, int y, boolean good, Direction dir, Level level, int player) {
        this(x, y, good);
        this.direction = dir;
        this.level = level;
        this.player = player;
    }

    public void draw(Graphics g) {
        if (!live) {
            if (!good) {
                level.tanks.remove(this);
            }
            return;
        }
        //if (good)
        //new DrawBloodbBar().draw(g); 
        switch (Kdirection) {

            case D:
                if (player == 1) {
                    g.drawImage(tankImags[4], x, y, null);
                } else if (level.player2 && player == 2) {
                    g.drawImage(tankImags[8], x, y, null);
                } else {
                    g.drawImage(tankImags[0], x, y, null);
                }
                break;

            case U:
                if (player == 1) {
                    g.drawImage(tankImags[5], x, y, null);
                } else if (level.player2 && player == 2) {
                    g.drawImage(tankImags[9], x, y, null);
                } else {
                    g.drawImage(tankImags[1], x, y, null);
                }
                break;
            case L:
                if (player == 1) {
                    g.drawImage(tankImags[6], x, y, null);
                } else if (level.player2 && player == 2) {
                    g.drawImage(tankImags[10], x, y, null);
                } else {
                    g.drawImage(tankImags[2], x, y, null);
                }
                break;

            case R:
                if (player == 1) {
                    g.drawImage(tankImags[7], x, y, null);
                } else if (level.player2 && player == 2) {
                    g.drawImage(tankImags[11], x, y, null);
                } else {
                    g.drawImage(tankImags[3], x, y, null);
                }
                break;

        }

        move();
    }

    public void move() {

        this.oldX = x;
        this.oldY = y;

        switch (direction) {
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

        if (this.direction != Direction.STOP) {
            this.Kdirection = this.direction;
        }

        if (x < 0) {
            x = 0;
        }
        if (y < 40) {
            y = 40;
        }
        if (x + Tank.width > TankClient.Fram_width) {
            x = TankClient.Fram_width - Tank.width;
        }
        if (y + Tank.length > TankClient.Fram_length) {
            y = TankClient.Fram_length - Tank.length;
        }

        if (!good) {
            Direction[] directons = Direction.values();
            if (step == 0) {
                step = r.nextInt(12) + 3;
                int mod = r.nextInt(9);
                if (playertankaround()) {
                    if (Math.abs(x - level.homeTank.x)<60) {
                        if (y > level.homeTank.y) {
                            direction = directons[1];
                        } else if (y < level.homeTank.y) {
                            direction = directons[3];
                        }
                    } else if (Math.abs(y - level.homeTank.y)<60) {
                        if (x > level.homeTank.x) {
                            direction = directons[0];
                        } else if (x < level.homeTank.x) {
                            direction = directons[2];
                        }
                    } else {
                        int rn = r.nextInt(directons.length);
                        direction = directons[rn];
                    }
                    if(level.player2==true){
                        if (Math.abs(x - level.homeTank2.x)<200) {
                            if (y > level.homeTank2.y) {
                                direction = directons[1];
                            } else if (y < level.homeTank2.y) {
                                direction = directons[3];
                            }
                        } else if (Math.abs(y - level.homeTank2.y)<200) {
                            if (x > level.homeTank2.x) {
                                direction = directons[0];
                            } else if (x < level.homeTank2.x) {
                                direction = directons[2];
                            }
                        } else {
                            int rn = r.nextInt(directons.length);
                            direction = directons[rn];
                        }
                    }
                    rate = 2;
                }  else if (1 <= mod && mod <= 3) {
                    rate = 1;
                } else {
                    int rn = r.nextInt(directons.length);
                    direction = directons[rn];
                    rate = 1;
                }
            }
            step--;
            if (rate == 2) {
                if (r.nextInt(40) > 35) {
                    this.fire();
                }
            } else if (r.nextInt(40) > 38) {
                this.fire();
            }
        }
    }

    public boolean playertankaround() {

        if (Math.abs(this.getX()- level.homeTank.getX()+
            Math.abs(this.getY()- level.homeTank.getY()))<300) {
            return true;
        }
        if (Math.abs(this.getX()- level.homeTank2.getX()+
            Math.abs(this.getY()- level.homeTank2.getY()))<300) {
            return true;
        }
        return false;
    }

    private void changToOldDir() {
        x = oldX;
        y = oldY;
    }

    public void keyPressed(KeyEvent e) throws Exception {
        int key = e.getKeyCode();
        if (player == 1) {
            switch (key) {

                case KeyEvent.VK_D:
                    bR = true;
                    break;

                case KeyEvent.VK_A:
                    bL = true;
                    break;

                case KeyEvent.VK_W:
                    bU = true;
                    break;

                case KeyEvent.VK_S:
                    bD = true;
                    break;
            }
        }
        if (player == 2) {
            switch (key) {
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;

                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;

                case KeyEvent.VK_UP:
                    bU = true;
                    break;

                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
            }
        }
        decideDirection();
    }

    void decideDirection() {
        if (!bL && !bU && bR && !bD) {
            direction = Direction.R;
        } else if (bL && !bU && !bR && !bD) {
            direction = Direction.L;
        } else if (!bL && bU && !bR && !bD) {
            direction = Direction.U;
        } else if (!bL && !bU && !bR && bD) {
            direction = Direction.D;
        } else if (!bL && !bU && !bR && !bD) {
            direction = Direction.STOP;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (player == 1) {
            switch (key) {

                case KeyEvent.VK_F:
                    fire();
                    break;

                case KeyEvent.VK_D:
                    bR = false;
                    break;

                case KeyEvent.VK_A:
                    bL = false;
                    break;

                case KeyEvent.VK_W:
                    bU = false;
                    break;

                case KeyEvent.VK_S:
                    bD = false;
                    break;

            }
        }
        if (player == 2) {
            switch (key) {

                case KeyEvent.VK_SLASH:
                    fire();
                    break;

                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;

                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;

                case KeyEvent.VK_UP:
                    bU = false;
                    break;

                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;

            }
        }
        decideDirection();
    }

    public Bullet fire() {
        if (!live) {
            return null;
        }
        int x = this.x + Tank.width / 2 - Bullet.width / 2;
        int y = this.y + Tank.length / 2 - Bullet.length / 2;
        Bullet m = new Bullet(x, y + 2, good, Kdirection, this.level);
        level.bullets.add(m);
        return m;
    }

    @Override
    public Rectangle getRect() {
        return new Rectangle(x, y, width, length);
    }

    @Override
    public boolean hitedBy(Bullet b) {
        if (b.getAlive() && b.getRect().intersects(this.getRect()) && this.isLive() && b.isGood() != this.isGood()) {

            BombTank e = new BombTank(this.getX(), this.getY(), level);
            level.bombTanks.add(e);
            if (this.isGood()) {
                this.setLife(this.getLife() - 50);
                if (this.getLife() <= 0) {
                    this.setLive(false);
                }
            } else {
                this.setLive(false);
            }

            b.setAlive(false);
            return true;
        }
        return false;

    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public boolean isGood() {
        return good;
    }

    public boolean collideWith(Block b) {
        if (this.live && this.getRect().intersects(b.getRect())) {
            this.changToOldDir();
            return true;
        }
        return false;
    }

    public boolean collideWith(Tank t) {
        if (this != t) {
            if (this.live && t.isLive()
                    && this.getRect().intersects(t.getRect())) {
                this.changToOldDir();
                t.changToOldDir();
                return true;
            }
        }
        return false;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
