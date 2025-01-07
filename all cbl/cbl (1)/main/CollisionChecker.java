package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public boolean checkTileCollision(Entity entity, String direction) {
        // Calculate entity's world boundaries
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        // Calculate column and row positions in the tile map
        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;
        boolean collisionDetected = false;

        // Determine the direction of movement and adjust rows/columns accordingly
        switch (direction) {
            case "up":
                // When player wants to go up, two different cases are checked for collision
                int entityTopRowUp = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRowUp];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRowUp];
                collisionDetected = gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision;
                break;

            case "down":
                int entityBottomRowDown = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRowDown];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRowDown];
                collisionDetected = gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision;
                break;

            case "left":
                int entityLeftColLeft = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftColLeft][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftColLeft][entityBottomRow];
                collisionDetected = gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision;
                break;

            case "right":
                int entityRightColRight = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightColRight][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightColRight][entityBottomRow];
                collisionDetected = gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision;
                break;
        }

        // Update the entity's collision status and return the result
        entity.collisionOn = collisionDetected;
        return collisionDetected;
    }

    // Original checkTile method retained for additional usage if needed
    public void checkTile(Entity entity) {
        // Reuse checkTileCollision to simplify this method
        checkTileCollision(entity, entity.direction);
    }
}
