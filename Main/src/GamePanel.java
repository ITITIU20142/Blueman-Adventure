// import java.awt.Color;
// import java.awt.Dimension;
// import java.awt.Graphics;
// import java.awt.Graphics2D;

// import src.Player;
// import Player;

import java.awt.event.KeyListener;

import java.awt.*;
import javax.swing.*;

// import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile 
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    // final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    // final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    final int screenWidth = 1920; // 1920 pixels
    final int screenHeight = 1080; // 1080 pixels

    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Player player = new Player(this, keyH);

    // SET PLAYER'S DEAFAULT POSITION AND SPEED
    // int playerX = 100;
    // int playerY = 100;
    // int playerSpeed = 4;

    public GamePanel() {
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        // this.addKeyListener(keyH); **THIS ONE IS THE PRIMARY ONE**
        this.addKeyListener((KeyListener) keyH);
        this.setFocusable(true);

    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();

    }

    // SLEEP METHOD
    @Override
    public void run() {

        double drawInterval = 1000000000/FPS; // 0.0166667 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null) {

            // long currentTime = System.nanoTime();
            // System.out.println("current Time: " + currentTime);
            // long currentTime2 = System.currentTimeMillis();


            // 1 UPDATE: update information such as character positions
            update();

            // 2 DRAW: draw the screen with the update information
            repaint();

            // double remainingTime = nextDrawTime - System.nanoTime();

            try {

                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;

                if(remainingTime < 0) remainingTime = 0; // SET REMAINING_TIME TO 0

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {

                e.printStackTrace();

            }

        }

    }
    
    public void update() {
    
        player.update();

        // if(keyH.upPressed == true) {
        //     playerY -= playerSpeed;
        // }
        // else if(keyH.downPressed == true) {
        //     playerY += playerSpeed;
        // }
        // else if(keyH.leftPressed == true) {
        //     playerX -= playerSpeed;
        // }
        // else if(keyH.rightPressed == true) {
        //     playerX += playerSpeed;
        // }

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2D = (Graphics2D)g;

        player.draw(g2D);

        // g2D.setColor(Color.white);

        // g2D.fillRect(playerX, playerY, tileSize, tileSize);

        g2D.dispose();

    }

}
