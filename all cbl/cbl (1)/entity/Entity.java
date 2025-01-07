package entity;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import main.CollisionChecker;
import main.GamePanel;
import main.KeyHandler;

public class Entity {

    // Position of the entity in the game world
    public int worldX, worldY;

    // Movement speed of the entity
    public int speed;

    // Images for different animation frames in each direction
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;

    // Direction the entity is currently facing (e.g., "up", "down", "left",
    // "right")
    public String direction;

    // Area around the entity used for collision detection
    public Rectangle solidArea;

    // Collision status (true if there's a collision, false otherwise)
    public boolean collisionOn = false;

    // Collision checker to detect collisions in the game world
    private CollisionChecker collisionChecker;

    // Key handler to track player input for movement
    private KeyHandler keyH;

    // Constructor for the Entity class
    // Initializes the collision checker, key handler, and solid area (collision
    // area)
    public Entity(GamePanel gp, KeyHandler keyH) {
        this.keyH = keyH; // Assign the key handler for movement control
        this.collisionChecker = new CollisionChecker(gp); // Initialize the collision checker
        this.solidArea = new Rectangle(0, 0, 48, 48); // Default collision area (48x48 pixels), can be adjusted as
                                                      // needed
    }

    // Method to update the entity's position based on input and collision detection
    public void updatePosition() {
        // Check and move up if the "up" key is pressed and there’s no collision in the
        // "up" direction
        if (keyH.upPressed && !collisionChecker.checkTileCollision(this, "up")) {
            worldY -= speed; // Move up by decreasing the y-coordinate
        }

        // Check and move down if the "down" key is pressed and there’s no collision in
        // the "down" direction
        if (keyH.downPressed && !collisionChecker.checkTileCollision(this, "down")) {
            worldY += speed; // Move down by increasing the y-coordinate
        }

        // Check and move left if the "left" key is pressed and there’s no collision in
        // the "left" direction
        if (keyH.leftPressed && !collisionChecker.checkTileCollision(this, "left")) {
            worldX -= speed; // Move left by decreasing the x-coordinate
        }

        // Check and move right if the "right" key is pressed and there’s no collision
        // in the "right" direction
        if (keyH.rightPressed && !collisionChecker.checkTileCollision(this, "right")) {
            worldX += speed; // Move right by increasing the x-coordinate
        }
    }
}
