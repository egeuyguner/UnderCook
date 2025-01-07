package entity;

import main.GamePanel;
import main.Inventory;
import main.KeyHandler;
import object.SuperObject;
import javax.swing.*;
import java.awt.*;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Entity {

    private GamePanel gp;
    private KeyHandler keyH;
    private Inventory inventory;
    public final int screenX;
    public final int screenY;
    private int speed;
    private Rectangle fullArea;

    private BufferedImage up1, down1, left1, right1;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp, keyH);
        this.gp = gp;
        this.keyH = keyH;
        this.inventory = gp.getInventory();

        this.screenX = gp.screenWidth;
        this.screenY = gp.screenHeight;

        solidArea = new Rectangle(8, 16, 32, 32);
        fullArea = new Rectangle(worldX, worldY, gp.tileSize, gp.tileSize);

        setDefaultValues();
        getPlayerImage();
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setDefaultValues() {
        worldX = 100;
        worldY = 100;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(new File(GamePanel.URL + "chefu1.png"));
            right1 = ImageIO.read(new File(GamePanel.URL + "r1.png"));
            down1 = ImageIO.read(new File(GamePanel.URL + "chefu1.png"));
            left1 = ImageIO.read(new File(GamePanel.URL + "l1.png"));
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void update() {
        // Variables to store independent movement flags for each direction
        boolean canMoveUp = !gp.cChecker.checkTileCollision(this, "up");
        boolean canMoveDown = !gp.cChecker.checkTileCollision(this, "down");
        boolean canMoveLeft = !gp.cChecker.checkTileCollision(this, "left");
        boolean canMoveRight = !gp.cChecker.checkTileCollision(this, "right");

        // Move in each direction independently if the respective key is pressed and no
        // collision is detected
        if (keyH.upPressed && canMoveUp) {
            direction = "up";
            worldY -= speed;
        }
        if (keyH.downPressed && canMoveDown) {
            direction = "down";
            worldY += speed;
        }
        if (keyH.leftPressed && canMoveLeft) {
            direction = "left";
            worldX -= speed;
        }
        if (keyH.rightPressed && canMoveRight) {
            direction = "right";
            worldX += speed;
        }

        // Update fullArea to follow player's position
        fullArea.x = worldX;
        fullArea.y = worldY;

        // Handle pick-up and place actions
        if (keyH.ePressed) {
            if (inventory.hasItem()) {
                gp.getPickUp().placeItem(this);
            } else {
                gp.getPickUp().checkForPickUp(this);
            }
            keyH.ePressed = false;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        //Change the images according to players direction
        switch (direction) {
            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
                break;
            case "left":
                image = left1;
                break;
            case "right":
                image = right1;
                break;
        }

        g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
    }

    public void addItemToInventory(SuperObject item) {
        inventory.addItem(item);
    }

    public boolean intersects(SuperObject obj) {
        return this.fullArea.intersects(obj.getBounds());
    }
}
