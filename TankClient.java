package TankWar;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class TankClient extends Frame implements ActionListener {

    /**
     *
     */
    // 初始化界面各控件
    public static final int Fram_width = 800;
    public static final int Fram_length = 600;
    public static boolean printable = true;
    MenuBar jmb = null;
    Menu jm1 = null, jm2 = null, jm3 = null, jm4 = null, jm5 = null;
    MenuItem jmi1 = null, jmi2 = null, jmi3 = null, jmi4 = null, jmi5 = null,
            jmi6 = null, jmi7 = null, jmi8 = null, jmi10 = null;
    Image screenImage = null;
    Level level = null;

    //刷新函数
    public void update(Graphics g) {

        screenImage = this.createImage(Fram_width, Fram_length);

        Graphics gps = screenImage.getGraphics();
        Color c = gps.getColor();
        gps.setColor(Color.GRAY);
        gps.fillRect(0, 0, Fram_width, Fram_length);
        gps.setColor(c);
        try {
            level.framePaint(gps);
        } catch (Exception ex) {
            Logger.getLogger(TankClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        g.drawImage(screenImage, 0, 0, null);
    }




    //构造函数，添加监听，启动paint进程

    public TankClient() throws Exception {
        // printable = false;

        jmb = new MenuBar();
        jm1 = new Menu("Game");
        jm2 = new Menu("Pause/Continue");
        jm3 = new Menu("Help");
        jm4 = new Menu("Level");
        jm5 = new Menu("Addition");
        jm1.setFont(new Font("Times New Roman", Font.BOLD, 15));
        jm2.setFont(new Font("Times New Roman", Font.BOLD, 15));
        jm3.setFont(new Font("Times New Roman", Font.BOLD, 15));
        jm4.setFont(new Font("Times New Roman", Font.BOLD, 15));
        jm5.setFont(new Font("Times New Roman", Font.BOLD, 15));

        jmi1 = new MenuItem("New Game");
        jmi2 = new MenuItem("Exit");
        jmi3 = new MenuItem("Stop");
        jmi4 = new MenuItem("Continue");
        jmi5 = new MenuItem("Help");
        jmi6 = new MenuItem("Level1");
        jmi7 = new MenuItem("Level2");
        jmi8 = new MenuItem("Level3");
        jmi10 = new MenuItem("Add Player 2");
        jmi1.setFont(new Font("Times New Roman", Font.BOLD, 15));
        jmi2.setFont(new Font("Times New Roman", Font.BOLD, 15));
        jmi3.setFont(new Font("Times New Roman", Font.BOLD, 15));
        jmi4.setFont(new Font("Times New Roman", Font.BOLD, 15));
        jmi5.setFont(new Font("Times New Roman", Font.BOLD, 15));

        jm1.add(jmi1);
        jm1.add(jmi2);
        jm2.add(jmi3);
        jm2.add(jmi4);
        jm3.add(jmi5);
        jm4.add(jmi6);
        jm4.add(jmi7);
        jm4.add(jmi8);
        jm5.add(jmi10);

       

        jmb.add(jm1);
        jmb.add(jm2);

        jmb.add(jm4);
        jmb.add(jm5);
        jmb.add(jm3);

        jmi1.addActionListener(this);
        jmi1.setActionCommand("NewGame");
        jmi2.addActionListener(this);
        jmi2.setActionCommand("Exit");
        jmi3.addActionListener(this);
        jmi3.setActionCommand("Stop");
        jmi4.addActionListener(this);
        jmi4.setActionCommand("Continue");
        jmi5.addActionListener(this);
        jmi5.setActionCommand("help");
        jmi6.addActionListener(this);
        jmi6.setActionCommand("level1");
        jmi7.addActionListener(this);
        jmi7.setActionCommand("level2");
        jmi8.addActionListener(this);
        jmi8.setActionCommand("level3");
        jmi10.addActionListener(this);
        jmi10.setActionCommand("Player2");


        this.setMenuBar(jmb);
        this.setVisible(true);

        this.setSize(Fram_width, Fram_length);
        this.setLocation(280, 50);
        this.setTitle("Battle City    Final Project for JAVA");

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setResizable(false);
        this.setBackground(Color.GREEN);
        this.setVisible(true);

        this.level = new Level(1, this);
        this.addKeyListener(new KeyMonitor());
        new Thread(new PaintThread()).start();
    }

    //主函数
    public static void main(String[] args) throws Exception {
        new TankClient();
    }
    //Paint 线程

    private class PaintThread implements Runnable {

        public void run() {
            // TODO Auto-generated method stub
            while (printable) {
                repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    // 坦克响应按键

    private class KeyMonitor extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            level.homeTank.keyReleased(e);
            level.homeTank2.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            try {
                level.homeTank.keyPressed(e);
                level.homeTank2.keyPressed(e);
            } catch (Exception ex) {
                Logger.getLogger(TankClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("NewGame")) {
            printable = false;
            Object[] options = {"Confirm", "Cancel"};
            int response = JOptionPane.showOptionDialog(this, "Confirm to start a new game?", "",
                    JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                    options, options[0]);
            if (response == 0) {

                printable = true;
                this.dispose();
                try {
                    new TankClient();
                } catch (Exception ex) {
                    Logger.getLogger(TankClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            } else {
                printable = true;
                new Thread(new PaintThread()).start();
            }

        } else if (e.getActionCommand().endsWith("Stop")) {
            printable = false;

        } else if (e.getActionCommand().equals("Continue")) {

            if (!printable) {
                printable = true;
                new Thread(new PaintThread()).start();
            }
        } else if (e.getActionCommand().equals("Exit")) {
            printable = false;
            Object[] options = {"Confirm", "Cancel"};
            int response = JOptionPane.showOptionDialog(this, "Confirm to exit?", "",
                    JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                    options, options[0]);
            if (response == 0) {
                System.out.println("break down");
                System.exit(0);
            } else {
                printable = true;
                new Thread(new PaintThread()).start();

            }

        } else if (e.getActionCommand().equals("Player2")) {
            printable = false;
            Object[] options = {"Confirm", "Cancel"};
            int response = JOptionPane.showOptionDialog(this, "Confirm to add player2?", "",
                    JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                    options, options[0]);
            if (response == 0) {
                printable = true;
                this.dispose();
                try {
                    TankClient tc = new TankClient();
                    Level newLevel = new Level(this.level.getLevel(), tc);
                    tc.level = newLevel;
                    tc.level.player2 = true;
                } catch (Exception ex) {
                    Logger.getLogger(TankClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                }
            } else {
                printable = true;
                new Thread(new PaintThread()).start();
            }
        } else if (e.getActionCommand().equals("help")) {
            printable = false;
            JOptionPane.showMessageDialog(null, "Use WSAD to control Player1's direction, use F to fire and restart with pressing R\nUse diection key to Control Player2, use slash to fire",
                    "Help", JOptionPane.INFORMATION_MESSAGE);
            this.setVisible(true);
            printable = true;
            new Thread(new PaintThread()).start();
        } else if (e.getActionCommand().equals("level1")) {
            printable = true;
            try {
                this.level = new Level(1, this);
            } catch (Exception ex) {
                Logger.getLogger(TankClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

        } else if (e.getActionCommand().equals("level2")) {
             try {
                this.level = new Level(2, this);
            } catch (Exception ex) {
                Logger.getLogger(TankClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

        } else if (e.getActionCommand().equals("level3")) {
            try {
                this.level = new Level(3, this);
            } catch (Exception ex) {
                Logger.getLogger(TankClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

        } 

    }
}
