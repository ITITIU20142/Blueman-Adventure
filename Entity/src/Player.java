// package Entity.src;

// import GamePanel.java;
// import KeyHandler.java;

// import java.awt.event.KeyListener;

import java.awt.*;
import javax.swing.*;

public class Player extends Entity {
    
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();

    }

    public void setDefaultValues() {

        x = 100;
        y = 100;
        speed = 4;

    }

    public void update() {

        if(keyH.upPressed == true) {
            y -= speed;
        }
        else if(keyH.downPressed == true) {
            y += speed;
        }
        else if(keyH.leftPressed == true) {
            x -= speed;
        }
        else if(keyH.rightPressed == true) {
            x += speed;
        }

    }

    public void draw(Graphics2D g2D) {

        g2D.setColor(Color.white);

        g2D.fillRect(x, y, gp.tileSize, gp.tileSize);

    }

}
