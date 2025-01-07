package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;

    public void draw(Graphics2D g2, GamePanel gp) {

        int screenX = worldX;
        int screenY = worldY;

        //As previously mentioned, screen and world coordinates are treated as different values
        //to make it easy in the future to implement a camera and use larger maps.

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        if (image != null) {
            //System.out.println("Image loaded successfully for station at (0,0)");
        } else{
            System.out.println("image is null");
        }

    }

    public Rectangle getBounds() {
        return new Rectangle(worldX, worldY, 32, 32);
    }

    public void setPosition(int posX, int posY) {
        this.worldX = posX;
        this.worldY = posY;
    }

    // Getter for x-coordinate
    public int getX() {
        return worldX;
    }

    // Getter for y-coordinate
    public int getY() {
        return worldY;
    }
}
