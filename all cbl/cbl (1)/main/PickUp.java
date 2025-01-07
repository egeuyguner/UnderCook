package main;

import entity.Player;
import object.OBJ_Cola;
import object.OBJ_Doner;
import object.OBJ_Key;
import object.OBJ_Lettuce;
import object.OBJ_Tomato;
import object.OBJ_Wrap;
import object.OBJ_cutTomato;
import object.OBJ_donerl;
import object.OBJ_donerlt;
import object.OBJ_donert;
import object.OBJ_donerw;
import object.OBJ_cutLettuce;
import object.SuperObject;
import main.Inventory;

public class PickUp {

    private GamePanel gp;

    public PickUp(GamePanel gp) {
        this.gp = gp;
    }

    // Method to check if player can pick up any item
    public void checkForPickUp(Player player) {

        // Check if the player is within range.
        int range = 1;
        int playerTileX = player.worldX / gp.tileSize;
        int playerTileY = player.worldY / gp.tileSize;

        // Loop over nearby tiles within the defined range
        for (int x = playerTileX - range; x <= playerTileX + range; x++) {
            for (int y = playerTileY - range; y <= playerTileY + range; y++) {
                if (x >= 0 && y >= 0 && x < gp.maxWorldCol && y < gp.maxWorldRow) {
                    if (gp.tileM.mapTileNum[x][y] == 2) {
                        gp.getInventory().addItem(new OBJ_Tomato());
                        return;
                    }
                    if (gp.tileM.mapTileNum[x][y] == 7) {
                        gp.getInventory().addItem(new OBJ_Cola());
                        return;
                    }
                    if (gp.tileM.mapTileNum[x][y] == 5) {
                        gp.getInventory().addItem(new OBJ_Wrap());
                        return;
                    }
                    if (gp.tileM.mapTileNum[x][y] == 3) {
                        gp.getInventory().addItem(new OBJ_Lettuce());
                        return;
                    }
                }
            }
        }

        for (int x = playerTileX - range; x <= playerTileX + range; x++) {
            for (int y = playerTileY - range; y <= playerTileY + range; y++) {
                // Ensure we’re within map bounds
                if (x >= 0 && y >= 0 && x < gp.maxWorldCol && y < gp.maxWorldRow) {
                    if (gp.tileM.mapTileNum[x][y] == 4) {
                        gp.getInventory().addItem(new OBJ_Doner());
                        return;
                    }
                }
            }
        }

        if (gp.getInventory().hasItem()) { // The inventory already has an item
            // so e is pressed to place item from inventory into the game world
            SuperObject item = gp.getInventory().removeItem();
            item.setPosition(player.worldX, player.worldY);
            // Place item at players location

            // add item to game world
            for (int i = 0; i < gp.obj.length; i++) {
                if (gp.obj[i] == null) {
                    gp.obj[i] = item;
                    break;
                }
            }
        } else { // inventory is empty
            // Original logic to pick up an item
            boolean foundItem = false;
            for (int i = 0; i < gp.obj.length; i++) {
                SuperObject obj = gp.obj[i];
                if (obj != null) {
                    if (Math.abs(player.worldX - obj.worldX) < gp.tileSize
                            && Math.abs(player.worldY - obj.worldY) < gp.tileSize) {
                        gp.getInventory().addItem(obj);
                        gp.obj[i] = null;
                        foundItem = true;
                        break;
                    } else {
                        // else statement for debugging
                    }
                }
            }
            if (!foundItem) {
                // This if statement is left here for possible debugging.
            }
        }
    }

    public void placeItem(Player player) {
        if (!gp.getInventory().hasItem()) {
            return; // exit if inventory is empty
        }

        SuperObject item = gp.getInventory().getItem();

        // Calculate position two tiles below the player
        int dropX = player.worldX;
        int dropY = player.worldY;

        // Place the item two tiles below the player's position
        item.setPosition(dropX, dropY);
        gp.getInventory().clearItem(); // Clear the item from inventory

        // Add item back into game world
        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] == null) {
                gp.obj[i] = item;
                break;
            }
        }

        // Check if tile[8] is within range of the drop location
        int range = 1; // Define range as one tile around the dropped item
        int dropTileX = dropX / gp.tileSize;
        int dropTileY = dropY / gp.tileSize;

        for (int x = dropTileX - range; x <= dropTileX + range; x++) {
            for (int y = dropTileY - range; y <= dropTileY + range; y++) {
                // Ensure we’re within map bounds
                if (x >= 0 && y >= 0 && x < gp.maxWorldCol && y < gp.maxWorldRow) {
                    if (gp.tileM.mapTileNum[x][y] == 8) { // Check for tile[8]
                        // Change the item's position to the location of tile[8]
                        int targetX = x * gp.tileSize;
                        int targetY = y * gp.tileSize;
                        item.setPosition(targetX, targetY);
                        // Exit loop early after snapping the item to tile[8]
                        return;
                    } else if (gp.tileM.mapTileNum[x][y] == 9) { // New check for tile[9]
                        // Auto-place the item on top of tile[9]
                        int targetX = x * gp.tileSize;
                        int targetY = y * gp.tileSize;
                        item.setPosition(targetX, targetY);
                        return;
                    }

                }
            }
        }
    }

    public boolean isObjectOnTile8() {
        int tileSize = gp.tileSize;

        // Loop through all objects in the game world
        for (SuperObject obj : gp.obj) {
            if (obj != null) {
                // Calculate object's tile position
                int objTileX = obj.worldX / gp.tileSize;
                int objTileY = obj.worldY / gp.tileSize;

                // Check if the object's tile position corresponds to a tile[8]
                if (gp.tileM.mapTileNum[objTileX][objTileY] == 8) {
                    return true;
                } else {
                    // else statement for debugging
                }
            }
        }
        // If no objects are on tile[8], return false
        return false;
    }

    public void cuttingTomato() {
        int tileSize = gp.tileSize;

        // Loop through all objects in the game world
        for (int i = 0; i < gp.obj.length; i++) {
            SuperObject obj = gp.obj[i];

            if (obj != null) {
                // Calculate object's tile position
                int objTileX = obj.worldX / tileSize;
                int objTileY = obj.worldY / tileSize;

                // Check if the object is on tile[8] and is specifically an OBJ_Tomato
                if (gp.tileM.mapTileNum[objTileX][objTileY] == 9 && obj instanceof OBJ_Tomato) {
                    // Remove the OBJ_Tomato from the game
                    gp.obj[i] = null;

                    // Create a new OBJ_cutTomato and place it at the same position
                    OBJ_cutTomato cutTomato = new OBJ_cutTomato();
                    cutTomato.setPosition(obj.worldX, obj.worldY);
                    gp.obj[i] = cutTomato;
                    return; // Exit after replacement
                } else {
                    // This else statement is left here for possible debugging.
                }
            }
        }
    }

    public void cuttinglettuce() { // same as tomato
        int tileSize = gp.tileSize;
        for (int i = 0; i < gp.obj.length; i++) {
            SuperObject obj = gp.obj[i];
            if (obj != null) {
                int objTileX = obj.worldX / tileSize;
                int objTileY = obj.worldY / tileSize;
                if (gp.tileM.mapTileNum[objTileX][objTileY] == 9 && obj instanceof OBJ_Lettuce) {
                    gp.obj[i] = null;
                    OBJ_cutLettuce cutLettuce = new OBJ_cutLettuce();
                    cutLettuce.setPosition(obj.worldX, obj.worldY);
                    gp.obj[i] = cutLettuce;
                    return;
                } else {
                }
            }
        }
    }

    public void replaceItemsWithDonerWOnTile8() {
        // adding wrap and doner to make a doner-wrap item
        int tileSize = gp.tileSize;
        boolean foundDoner = false;
        boolean foundWrap = false;
        int donerIndex = -1;
        int wrapIndex = -1;
        int targetX = -1;
        int targetY = -1;

        // Loop through all objects in the game world to find OBJ_Doner and OBJ_Wrap on
        // tile[8]
        for (int i = 0; i < gp.obj.length; i++) {
            SuperObject obj = gp.obj[i];

            if (obj != null) {
                // Calculate object's tile position
                int objTileX = obj.worldX / tileSize;
                int objTileY = obj.worldY / tileSize;

                // Check if the object is on tile[8]
                if (gp.tileM.mapTileNum[objTileX][objTileY] == 8) {
                    if (obj instanceof OBJ_Doner) {
                        foundDoner = true;
                        donerIndex = i;
                        targetX = obj.worldX;
                        targetY = obj.worldY;
                    } else if (obj instanceof OBJ_Wrap) {
                        foundWrap = true;
                        wrapIndex = i;
                        targetX = obj.worldX;
                        targetY = obj.worldY;
                    }
                }
            }
        }

        // If both OBJ_Doner and OBJ_Wrap are found on tile[8], replace them with
        // OBJ_donerw
        if (foundDoner && foundWrap) {
            // Remove OBJ_Doner and OBJ_Wrap from the game world
            gp.obj[donerIndex] = null;
            gp.obj[wrapIndex] = null;

            // Create a new OBJ_donerw and place it at the target location
            OBJ_donerw donerw = new OBJ_donerw();
            donerw.setPosition(targetX, targetY);

            // Add the new OBJ_donerw to the game world in an available slot
            for (int i = 0; i < gp.obj.length; i++) {
                if (gp.obj[i] == null) {
                    gp.obj[i] = donerw;
                    break;
                }
            }

        } else {
            // This else statement is left here for possible debugging.
        }
    }

    // Same logic as replaceItemsWithDonerWOnTile8()
    public void replaceItemsWithDonerLT() {
        // Creates wraps with doner, tomato and/or lettuce
        int tileSize = gp.tileSize;
        boolean foundDoner = false;
        boolean foundWrap = false;
        boolean foundCutTomato = false;
        boolean foundCutLettuce = false;
        boolean foundDonerw = false;
        boolean foundDonerl = false;
        boolean foundDonert = false;

        int donerIndex = -1;
        int wrapIndex = -1;
        int CutTomatoIndex = -1;
        int CutLettuceIndex = -1;
        int donerwIndex = -1;
        int donerlIndex = -1;
        int donertIndex = -1;

        int targetX = -1;
        int targetY = -1;

        // Loop through all objects in the game world to find OBJ_Doner and OBJ_Wrap on
        for (int i = 0; i < gp.obj.length; i++) {
            SuperObject obj = gp.obj[i];
            if (obj != null) {
                // Calculate object's tile position
                int objTileX = obj.worldX / tileSize;
                int objTileY = obj.worldY / tileSize;
                // Check if the object is on tile[8]
                if (gp.tileM.mapTileNum[objTileX][objTileY] == 8) {
                    if (obj instanceof OBJ_Doner) {
                        foundDoner = true;
                        donerIndex = i;
                        targetX = obj.worldX;
                        targetY = obj.worldY;
                    } else if (obj instanceof OBJ_Wrap) {
                        foundWrap = true;
                        wrapIndex = i;
                        targetX = obj.worldX;
                        targetY = obj.worldY;
                    } else if (obj instanceof OBJ_cutTomato) {
                        foundCutTomato = true;
                        CutTomatoIndex = i;
                        targetX = obj.worldX;
                        targetY = obj.worldY;
                    } else if (obj instanceof OBJ_cutLettuce) {
                        foundCutLettuce = true;
                        CutLettuceIndex = i;
                        targetX = obj.worldX;
                        targetY = obj.worldY;
                    } else if (obj instanceof OBJ_donerw) {
                        foundDonerw = true;
                        donerwIndex = i;
                        targetX = obj.worldX;
                        targetY = obj.worldY;
                    } else if (obj instanceof OBJ_donerl) {
                        foundDonerl = true;
                        donerlIndex = i;
                        targetX = obj.worldX;
                        targetY = obj.worldY;
                    } else if (obj instanceof OBJ_donert) {
                        foundDonert = true;
                        donertIndex = i;
                        targetX = obj.worldX;
                        targetY = obj.worldY;
                    }
                }
            }
        }

        // Makes doner with tomatoes
        if (foundDonerw && foundCutTomato) {
            // Check if indices are valid before attempting to access gp.obj
            if (donerwIndex != -1 && CutTomatoIndex != -1) {

                // Remove OBJ_Doner and OBJ_Wrap from the game world
                gp.obj[donerwIndex] = null;
                gp.obj[CutTomatoIndex] = null;

                // Create a new OBJ_donert and place it at the target location
                OBJ_donert donert = new OBJ_donert();
                donert.setPosition(targetX, targetY);

                // Add the new OBJ_donert to the game world in an available slot
                for (int i = 0; i < gp.obj.length; i++) {
                    if (gp.obj[i] == null) {
                        gp.obj[i] = donert;
                        break;
                    }
                }
            }

        } else if (foundDonerw && foundCutLettuce) { // creates doner with lettuce
            if (donerwIndex != -1 && CutLettuceIndex != -1) {
                gp.obj[donerwIndex] = null;
                gp.obj[CutLettuceIndex] = null;
                OBJ_donerl donerl = new OBJ_donerl();
                donerl.setPosition(targetX, targetY);
                for (int i = 0; i < gp.obj.length; i++) {
                    if (gp.obj[i] == null) {
                        gp.obj[i] = donerl;
                        break;
                    }
                }

            }
        } else if (foundDonerl && foundCutTomato && donerlIndex != -1 && CutTomatoIndex != -1) {
            // creates doner with both tomatoes and lettuce
            gp.obj[donerlIndex] = null;
            gp.obj[CutTomatoIndex] = null;

            OBJ_donerlt donerlt = new OBJ_donerlt();
            donerlt.setPosition(targetX, targetY);
            for (int i = 0; i < gp.obj.length; i++) {
                if (gp.obj[i] == null) {
                    gp.obj[i] = donerlt;
                    break;
                }
            }

        } else if (foundDonert && foundCutLettuce && donertIndex != -1 && CutLettuceIndex != -1) {
            // creates doner with both tomatoes and lettuce
            gp.obj[donertIndex] = null;
            gp.obj[CutLettuceIndex] = null;

            OBJ_donerlt donerlt = new OBJ_donerlt();
            donerlt.setPosition(targetX, targetY);
            for (int i = 0; i < gp.obj.length; i++) {
                if (gp.obj[i] == null) {
                    gp.obj[i] = donerlt;
                    break;
                }
            }
        }
    }
}
