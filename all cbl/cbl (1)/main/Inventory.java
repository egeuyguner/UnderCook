package main;

import java.awt.Color;
import java.awt.Graphics2D;
import object.SuperObject;

public class Inventory {

    public SuperObject slot; // Holds the current item in the inventory slot
    private final int slotSize; // Size of the slot
    private final int posX; // X position of the inventory slot on the screen
    public int posY; // Y position of the inventory slot on the screen

    public Inventory(int slotSize, int posX, int posY) {
        this.slotSize = slotSize;
        this.posX = posX;
        this.posY = posY;
    }

    // Adds an item to the inventory slot
    public void addItem(SuperObject item) {
        slot = item;
    }

    // Clears the inventory slot (removes the item)
    public void clearItem() {
        slot = null;
    }

    public void setPosition(int x, int y) {
        // this.x = posX;
        this.x = x;
        this.y = y;
    }

    public SuperObject removeItem() {
        SuperObject item = slot;
        slot = null;
        return item;
    }

    // Draws the inventory slot on the screen
    public void draw(Graphics2D g2, GamePanel gp) {

        // Draws inventory slot background
        g2.setColor(Color.white);
        g2.fillRect(posX, posY, slotSize, slotSize);

        // If an item is in the slot, draw it in the center of the slot
        if (slot != null) {
            // Set x and y to inventory position and draw
            slot.setPosition(posX, posY); // set position on the superobject
            slot.draw(g2, gp); // Adjusted to fit the existing `draw` method
        }

        // Draws a border around the slot
        g2.setColor(Color.black);
        g2.drawRect(posX, posY, slotSize, slotSize);
    }

    // Checks if inventory has an item
    public boolean hasItem() {
        return slot != null;
    }

    // Retrieves the current item
    public SuperObject getItem() {
        return slot;
    }
}
