package main;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.ImageIcon;
import object.SuperObject;
import object.OBJ_Tomato;
import object.OBJ_donerl;
import object.OBJ_donerlt;
import object.OBJ_donert;
import object.OBJ_donerw;
import object.OBJ_Cola;

public class CustomerRequest {

    private List<SuperObject> requestList;
    private List<SuperObject> currentRequest; // List to hold multiple items in one request
    private List<SuperObject> fulfilledItems; // Track which items have been delivered

    public CustomerRequest() {
        // Initialize possible requests and the fulfilled items list
        requestList = new ArrayList<>();
        fulfilledItems = new ArrayList<>();
        
        requestList.add(new OBJ_donerw());
        requestList.add(new OBJ_donerl());
        requestList.add(new OBJ_donert());
        requestList.add(new OBJ_donerlt());

        // Generate the first request when the game starts
        generateNewRequest();
    }

    // Randomly pick a request, sometimes with cola as a standalone or combined item
    public void generateNewRequest() {
        Random rand = new Random();
        currentRequest = new ArrayList<>();
        fulfilledItems.clear(); // Clear fulfilled items for the new request

        // 50% chance to add cola by itself or alongside other items
        boolean addCola = rand.nextBoolean();
        if (addCola) {
            currentRequest.add(new OBJ_Cola());
        }

        // Optionally add another item if it's not just cola
        if (!addCola || rand.nextBoolean()) { // 50% chance to add another item if cola is alone
            SuperObject randomItem = requestList.get(rand.nextInt(requestList.size()));
            currentRequest.add(randomItem);
        }
    }

    // Check if the player's item matches any items in the current request
    public boolean checkRequest(SuperObject playerItem) {
        if (playerItem == null) return false;

        boolean itemMatched = false;
        for (SuperObject item : currentRequest) {
            if (playerItem.getClass() == item.getClass() && !fulfilledItems.contains(item)) {
                fulfilledItems.add(item); // Track the item as delivered
                itemMatched = true;
                break;
            }
        }

        // Return true and clear both fulfilled and current requests if all items are matched
        if (itemMatched && fulfilledItems.size() == currentRequest.size()) {
            currentRequest.clear(); // Clear the request to cancel images
            fulfilledItems.clear(); // Clear fulfilled items
            return true; // Both items have been delivered
        }
        return false;
    }

    // Render the current request as images on the screen
    public void renderRequest(Graphics g, int x, int y) {
        if (currentRequest.isEmpty()) return; // Skip rendering if request is fulfilled

        int offsetX = 0; // To space out images horizontally

        for (SuperObject item : currentRequest) {
            Image itemImage = loadImage(item.getClass().getSimpleName() + ".png"); // Assuming image names match class names
            if (itemImage != null) {
                g.drawImage(itemImage, x + offsetX, y, null);
                offsetX += 50; // Adjust spacing as needed
            }
        }
    }

    // Helper method to load images
    private Image loadImage(String fileName) {
        return new ImageIcon(getClass().getResource(GamePanel.URL + fileName)).getImage();
    }

    // Get the current request as a string for debugging or display purposes
    public String getCurrentRequest() {
        if (currentRequest.isEmpty()) return "No request";
        
        StringBuilder requestStr = new StringBuilder();
        for (SuperObject item : currentRequest) {
            requestStr.append(item.getClass().getSimpleName()).append(" ");
        }
        return requestStr.toString().trim();
    }
}
