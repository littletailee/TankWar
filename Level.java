/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TankWar;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import static javax.management.Query.or;

/**
 *
 * @author Administrator
 */
public class Level {

    private final int LAST_LEVEL = 3;
    protected Tank homeTank = new Tank(300, 560, true, Direction.STOP, this, 1);
    protected Tank homeTank2 = new Tank(449, 560, true, Direction.STOP, this, 2);
    protected Boolean player1 = true;
    protected Boolean player2 = false;
    protected TankClient tc = null;
//    protected Heart blood = new Heart();
    protected Home home = new Home(373, 557, this);
    protected Boolean win = false, lose = false;
    protected List<River> theRiver = new ArrayList<River>();
    protected List<Tank> tanks = new ArrayList<Tank>();
    protected List<BombTank> bombTanks = new ArrayList<BombTank>();
    protected List<Bullet> bullets = new ArrayList<Bullet>();
    protected List<Tree> trees = new ArrayList<Tree>();
    protected List<BrickWall> homeWall = new ArrayList<BrickWall>();
    protected List<BrickWall> otherWall = new ArrayList<BrickWall>();
    protected List<MetalWall> metalWall = new ArrayList<MetalWall>();
//    protected Heart heart = null;
    protected int level = 1;

    public Level(int l, TankClient tc) throws Exception {
        this.level = l;
        this.tc = tc;
        switch (l) {

            case 1: {
                for (int i = 0; i < 10; i++) {
                    if (i < 4) {
                        homeWall.add(new BrickWall(350, 580 - 21 * i, this));
                    } else if (i < 7) {
                        homeWall.add(new BrickWall(372 + 22 * (i - 4), 517, this));
                    } else {
                        homeWall.add(new BrickWall(416, 538 + (i - 7) * 21, this));
                    }

                }

                for (int i = 0; i < 32; i++) {
                    if (i < 16) {
                        otherWall.add(new BrickWall(200 + 21 * i, 300, this));
                        otherWall.add(new BrickWall(500 + 21 * i, 180, this));
                        otherWall.add(new BrickWall(200, 400 + 21 * i, this));
                        otherWall.add(new BrickWall(500, 400 + 21 * i, this));
                    } else if (i < 32) {
                        otherWall.add(new BrickWall(200 + 21 * (i - 16), 320, this));
                        otherWall.add(new BrickWall(500 + 21 * (i - 16), 220, this));
                        otherWall.add(new BrickWall(222, 400 + 21 * (i - 16), this));
                        otherWall.add(new BrickWall(522, 400 + 21 * (i - 16), this));
                    }
                }

                for (int i = 0; i < 20; i++) {
                    if (i < 10) {
                        metalWall.add(new MetalWall(140 + 30 * i, 150, this));
                        metalWall.add(new MetalWall(600, 400 + 20 * (i), this));
                    } else if (i < 20) {
                        metalWall.add(new MetalWall(140 + 30 * (i - 10), 180, this));
                    }

                }

                for (int i = 0; i < 4; i++) {
                    if (i < 4) {
                        trees.add(new Tree(0 + 30 * i, 360, this));
                        trees.add(new Tree(220 + 30 * i, 360, this));
                        trees.add(new Tree(440 + 30 * i, 360, this));
                        trees.add(new Tree(660 + 30 * i, 360, this));
                    }

                }

                theRiver.add(new River(85, 100, this));
                for (int i = 0; i <20; i++) {
                    if (i < 9) {
                        tanks.add(new Tank(150 + 70 * i, 40, false, Direction.D, this, 0));
                    } else if (i < 15) {
                        tanks.add(new Tank(700, 140 + 50 * (i - 6), false, Direction.D,
                                this, 0));
                    } else {
                        tanks.add(new Tank(10, 50 * (i - 12), false, Direction.D,
                                this, 0));
                    }
                }
                break;
            }
            case 2: {
                theRiver.add(new River(660, 65, this));
                for (int i = 0; i < 10; i++) {
                    if (i < 9) {
                        tanks.add(new Tank(150 + 70 * i, 40, false, Direction.D, this, 0));
                    } else if (i < 15) {
                        tanks.add(new Tank(700, 140 + 50 * (i - 6), false, Direction.D,
                                this, 0));
                    } else {
                        tanks.add(new Tank(10, 50 * (i - 12), false, Direction.D,
                                this, 0));
                    }
                }

                for (int i = 0; i < 10; i++) {
                    if (i < 4) {
                        homeWall.add(new BrickWall(350, 580 - 21 * i, this));
                    } else if (i < 7) {
                        homeWall.add(new BrickWall(372 + 22 * (i - 4), 517, this));
                    } else {
                        homeWall.add(new BrickWall(416, 538 + (i - 7) * 21, this));
                    }

                }

                for (int i = 0; i < 32; i++) {
                    if (i < 10) {

                        otherWall.add(new BrickWall(275, 400 + 21 * i, this));
                        otherWall.add(new BrickWall(490, 400 + 21 * i, this));

                    } else if (i < 32) {

                        otherWall.add(new BrickWall(620, 120 + 21 * (i - 16), this));
                    }
                }

                for (int i = 0; i < 20; i++) {
                    if (i < 4) {
                        metalWall.add(new MetalWall(220 + 30 * i, 360, this));
                        metalWall.add(new MetalWall(440 + 30 * i, 360, this));
                        metalWall.add(new MetalWall(560, 150 + 30 * i, this));
                        otherWall.add(new BrickWall(340, 240 + 21 * i, this));
                        otherWall.add(new BrickWall(430, 240 + 21 * i, this));
                    } else if (i < 20) {
                        metalWall.add(new MetalWall(140 + 30 * (i - 10), 180, this));
                    }

                }

                for (int i = 0; i < 4; i++) {
                    if (i < 4) {
                        trees.add(new Tree(0 + 30 * i, 360, this));
                        trees.add(new Tree(0 + 30 * i, 220, this));
                        trees.add(new Tree(660 + 30 * i, 220, this));
                        trees.add(new Tree(660 + 30 * i, 360, this));
                    }

                }

                break;
            }
            case 3: {
                for (int i = 0; i < 4; i++) {
                    theRiver.add(new River(280 + 55 * i, 280, this));

                }

                for (int i = 0; i < 1; i++) {
                    if (i < 9) {
                        tanks.add(new Tank(150 + 70 * i, 40, false, Direction.D, this, 0));
                    } else if (i < 15) {
                        tanks.add(new Tank(700, 140 + 50 * (i - 6), false, Direction.D,
                                this, 0));
                    } else {
                        tanks.add(new Tank(10, 50 * (i - 12), false, Direction.D,
                                this, 0));
                    }
                }
                tanks.add(new Tank(400, 150, false, Direction.D, this, 0));
                for (int i = 0; i < 10; i++) {
                    if (i < 4) {
                        homeWall.add(new BrickWall(350, 580 - 21 * i, this));
                    } else if (i < 7) {
                        homeWall.add(new BrickWall(372 + 22 * (i - 4), 517, this));
                    } else {
                        homeWall.add(new BrickWall(416, 538 + (i - 7) * 21, this));
                    }

                }

                for (int i = 0; i < 3; i++) {
                    metalWall.add(new MetalWall(400 + 30 * i, 100, this));
                   // metalWall.add(new MetalWall(400 + 30 * i, 190, this));
                    metalWall.add(new MetalWall(370, 100 + 30 * i, this));
                    metalWall.add(new MetalWall(490, 100 + 30 * i, this));
                    metalWall.add(new MetalWall(620 + 30 * i, 80, this));
                    metalWall.add(new MetalWall(200 + 30 * i, 100, this));
                    metalWall.add(new MetalWall(600 + 30 * i, 290, this));
                    metalWall.add(new MetalWall(180 + 30 * i, 300, this));
                    metalWall.add(new MetalWall(600, 430 + 30 * i, this));
                    metalWall.add(new MetalWall(180, 430 + 30 * i, this));
                }

                metalWall.add(new MetalWall(370, 190, this));
                metalWall.add(new MetalWall(490, 190, this));

                for (int i = 0; i < 4; i++) {
                    if (i < 4) {
                        trees.add(new Tree(0 + 30 * i, 360, this));
                        trees.add(new Tree(0 + 30 * i, 220, this));
                        trees.add(new Tree(660 + 30 * i, 220, this));
                        trees.add(new Tree(660 + 30 * i, 360, this));
                    }

                }

                for (int i = 0; i < 32; i++) {
                    if (i < 10) {

                        otherWall.add(new BrickWall(150, 400 + 21 * i, this));
                        otherWall.add(new BrickWall(575, 400 + 21 * i, this));
                        otherWall.add(new BrickWall(170 + 21 * i, 250, this));
                        otherWall.add(new BrickWall(280 + 21 * i, 460, this));

                    }
                }

                break;
            }
           

            default: {
                throw new Exception("Invalid Level");
            }
        }

    }

    public void framePaint(Graphics g) throws Exception {
        //鍒锋柊鍦轰笂淇℃伅锛屽潶鍏嬫暟锛岀敓鍛界瓑
        Color c = g.getColor();
        g.setColor(Color.green);

        Font f1 = g.getFont();
        g.setFont(new Font("Times New Roman", Font.BOLD, 20));
        if (!player2) {
            g.drawString("Tanks left in the field: ", 200, 70);
        } else {
            g.drawString("Tanks left in the field: ", 100, 70);
        }
        g.setFont(new Font("Times New Roman", Font.ITALIC, 30));
        if (!player2) {
            g.drawString("" + tanks.size(), 400, 70);
        } else {
            g.drawString("" + tanks.size(), 300, 70);
        }
        g.setFont(new Font("Times New Roman", Font.BOLD, 20));
        if (!player2) {
            g.drawString("Health: ", 580, 70);
        } else {
            g.drawString("Health: ", 380, 70);
        }
        g.setFont(new Font("Times New Roman", Font.ITALIC, 30));
        if (!player2) {
            g.drawString("" + homeTank.getLife(), 650, 70);
        } else {
            g.drawString("Player1: " + homeTank.getLife() + "    Player2:" + homeTank2.getLife(), 450, 70);
        }
        g.setFont(f1);

        //鍒ゆ柇杈撹耽骞跺鐞�
        if (!player2) {
            // 鑳滃埄
            if (tanks.size() == 0 && home.isLive() && homeTank.isLive() && lose == false && level == LAST_LEVEL) {
                Font f = g.getFont();
                g.setFont(new Font("Times New Roman", Font.BOLD, 60));
                this.otherWall.clear();
                this.home.gameOver(g);
                g.drawString("Congratulations! ", 200, 300);
                
                g.setFont(f);
                win = true;
            }
            //銆�杩囧叧
            if (level != LAST_LEVEL && tanks.size() == 0 && home.isLive() && homeTank.isLive() && lose == false) {
                this.tc.level = new Level(this.getLevel() + 1, this.tc);
            }

            // 澶辫触
            if (!home.isLive() || (homeTank.isLive() == false && win == false)) {
                Font f = g.getFont();
                g.setFont(new Font("Times New Roman", Font.BOLD, 40));
                tanks.clear();
                bullets.clear();
                this.home.gameOver(g);
                g.drawString("Sorry. You lose!", 200, 300);
                
                lose = true;
                g.setFont(f);
            }
        } else {
            // 鑳滃埄
            if (tanks.size() == 0 && home.isLive() && (homeTank.isLive() || homeTank2.isLive()) && lose == false && level == LAST_LEVEL) {
                Font f = g.getFont();
                g.setFont(new Font("Times New Roman", Font.BOLD, 60));
                this.otherWall.clear();
                this.home.gameOver(g);
                g.drawString("Congratulations! ", 200, 300);
                
                g.setFont(f);
                win = true;
            }
            //銆�杩囧叧
            if (level != LAST_LEVEL && tanks.size() == 0 && home.isLive() && (homeTank.isLive() || homeTank2.isLive()) && lose == false) {
                this.tc.level = new Level(this.getLevel() + 1, this.tc);
                this.tc.level.player2 = true;
                if (!homeTank.isLive()) {
                    this.tc.level.player1 = false;
                }
                if (!homeTank2.isLive()) {
                    this.tc.level.player2 = false;
                    this.player2 = false;
                }
            }
            // 澶辫触
            if (!home.isLive() || (homeTank.isLive() == false && homeTank2.isLive() == false && win == false)) {
                Font f = g.getFont();
                g.setFont(new Font("Times New Roman", Font.BOLD, 40));
                tanks.clear();
                bullets.clear();
                this.home.gameOver(g);
                g.drawString("Sorry. You lose!", 200, 300);
                
                g.setFont(f);
                lose = true;
            }
        }
        g.setColor(c);

//        if(heart!=null)
//        {
//        	heart.draw(g);
//        }
        //鍧﹀厠涓嶈兘鍐嶆渤閲岋紝鎵�浠ョ敾娌宠妫�鏌�
        for (int i = 0; i < theRiver.size(); i++) {
            River r = theRiver.get(i);
            homeTank.collideWith(r);
            if (player2) {
                homeTank2.collideWith(r);
            }
            r.draw(g);
        }

        //鐢诲 鍜� 宸辨柟鍧﹀厠 hometank 鍥炶100 200鏄笂闄�
        home.draw(g);
        homeTank.draw(g);
//        homeTank.eat(blood);
        if (player2) {
            homeTank2.draw(g);
//            homeTank2.eat(blood);
        }
        // 澶勭悊瀛愬脊锛岃绠楁槸鍚﹀彂鐢熺鎾�
        for (int i = 0; i < bullets.size(); i++) {
            Bullet m = bullets.get(i);
            //m.hitTanks(tanks);
            m.hit(homeTank);
            m.hit(homeTank2);
            m.hit(home);
            for (int j = 0; j < tanks.size(); j++) {
                m.hit(tanks.get(j));
            }

            for (int j = 0; j < bullets.size(); j++) {
                if (i == j) {
                    continue;
                }
                m.hit(bullets.get(j));
            }
            for (int j = 0; j < metalWall.size(); j++) {
                m.hit(metalWall.get(j));
            }

            for (int j = 0; j < otherWall.size(); j++) {
                m.hit(otherWall.get(j));
            }

            for (int j = 0; j < homeWall.size(); j++) {
                m.hit(homeWall.get(j));
            }
            m.draw(g);
        }
        // 澶勭悊鍧﹀厠锛� 璁＄畻鏄惁鍙戠敓纰版挒
        for (int i = 0; i < tanks.size(); i++) {
            Tank t = tanks.get(i);

            for (int j = 0; j < homeWall.size(); j++) {
                BrickWall cw = homeWall.get(j);
                t.collideWith(cw);
                cw.draw(g);
            }
            for (int j = 0; j < otherWall.size(); j++) {
                BrickWall cw = otherWall.get(j);
                t.collideWith(cw);
                cw.draw(g);
            }
            for (int j = 0; j < metalWall.size(); j++) {
                MetalWall mw = metalWall.get(j);
                t.collideWith(mw);
                mw.draw(g);
            }
            for (int j = 0; j < theRiver.size(); j++) {
                River r = theRiver.get(j);
                t.collideWith(r);
                r.draw(g);
                // t.draw(g);
            }
            for (int j = 0; j < tanks.size(); j++) {
                t.collideWith(tanks.get(j));
            }

            t.collideWith(home);

            t.draw(g);
        }

        //blood.draw(g);
        for (int i = 0; i < trees.size(); i++) {
            Tree tr = trees.get(i);
            tr.draw(g);
        }

        for (int i = 0; i < bombTanks.size(); i++) {
            BombTank bt = bombTanks.get(i);
            bt.draw(g);
        }

        for (int i = 0; i < otherWall.size(); i++) {
            BrickWall cw = otherWall.get(i);
            cw.draw(g);
        }

        for (int i = 0; i < metalWall.size(); i++) {
            MetalWall mw = metalWall.get(i);
            mw.draw(g);
        }
        //澶勭悊宸辨柟鍧﹀厠
        for (int j = 0; j < tanks.size(); j++) {
            homeTank.collideWith(tanks.get(j));
        }
        homeTank.collideWith(home);
        if (player2) {
            for (int j = 0; j < tanks.size(); j++) {
                homeTank2.collideWith(tanks.get(j));
            }
            homeTank2.collideWith(homeTank);
            homeTank2.collideWith(home);
        }

        for (int i = 0; i < metalWall.size(); i++) {
            MetalWall w = metalWall.get(i);
            homeTank.collideWith(w);
            if (player2) {
                homeTank2.collideWith(w);
            }
            w.draw(g);
        }

        for (int i = 0; i < otherWall.size(); i++) {
            BrickWall cw = otherWall.get(i);
            homeTank.collideWith(cw);
            if (player2) {
                homeTank2.collideWith(cw);
            }
            cw.draw(g);
        }

        for (int i = 0; i < homeWall.size(); i++) {
            BrickWall w = homeWall.get(i);
            homeTank.collideWith(w);
            if (player2) {
                homeTank2.collideWith(w);
            }
            w.draw(g);
        }

    }

    public int getLevel() {
        return this.level;
    }

}
