package main.tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import object.SuperObject;

public class TileManager {
   GamePanel gp;
   public Tile[] tile;
   public int mapTileNum[][];

   // Coordinates for checking if the item is placed correctly
   private final int TARGET_X = 144;
   private final int TARGET_Y = 40;
   private final int DEFLECTION = 20;

   public TileManager(GamePanel gp) {
      this.gp = gp;
      tile = new Tile[15];
      mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

      getTileImage();
      loadMap();
   }

   public void getTileImage() {
      try {
         tile[0] = new Tile();
         tile[0].image = ImageIO.read(new File(GamePanel.URL + "floor1.png"));

         tile[1] = new Tile();
         tile[1].image = ImageIO.read(new File(GamePanel.URL + "table.png"));
         tile[1].collision = true;

         tile[2] = new Tile();
         tile[2].image = ImageIO.read(new File(GamePanel.URL + "tomato.png"));
         tile[2].collision = true;

         tile[3] = new Tile();
         tile[3].image = ImageIO.read(new File(GamePanel.URL + "marul.png"));
         tile[3].collision = true;

         tile[4] = new Tile();
         tile[4].image = ImageIO.read(new File(GamePanel.URL + "doner.png"));
         tile[4].collision = true;

         tile[5] = new Tile();
         tile[5].image = ImageIO.read(new File(GamePanel.URL + "lavas.png"));
         tile[5].collision = true;

         tile[7] = new Tile();
         tile[7].image = ImageIO.read(new File(GamePanel.URL + "kola.png"));
         tile[7].collision = true;

         tile[6] = new Tile();
         tile[6].image = ImageIO.read(new File(GamePanel.URL + "delivery.png"));
         tile[6].collision = true;

         tile[8] = new Tile();
         tile[8].image = ImageIO.read(new File(GamePanel.URL + "station.png"));
         tile[8].collision = true;

         tile[9] = new Tile();
         tile[9].image = ImageIO.read(new File(GamePanel.URL + "cutting.png"));
         tile[9].collision = true;

      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public void loadMap() {
      try {
         InputStream is = new FileInputStream(new File(GamePanel.URL + "cblmap.txt"));
         BufferedReader br = new BufferedReader(new InputStreamReader(is));
         int col = 0;
         int row = 0;

         while ((col < gp.maxWorldCol) && (row < gp.maxWorldRow)) {
            String line = br.readLine();

            while (col < gp.maxWorldCol) {
               String[] numbers = line.split(" ");
               int num = Integer.parseInt(numbers[col]);
               mapTileNum[col][row] = num;
               col++;
            }
            if (col == gp.maxWorldCol) {
               col = 0;
               row++;
            }
         }
         br.close();

      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public void draw(Graphics2D g2) {
      int col = 0;
      int row = 0;
      int x = 0;
      int y = 0;

      while ((col < gp.maxScreenCol) && (row < gp.maxScreenRow)) {
         int tileNum = mapTileNum[col][row];
         g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
         col++;
         x += gp.tileSize;

         if (col == gp.maxScreenCol) {
            col = 0;
            x = 0;
            row++;
            y += gp.tileSize;
         }
      }
   }

   // Method to check if an item is within the target area
   public boolean isWithinTargetArea(SuperObject item) {
      int itemX = item.getX(); // Assuming the item has getX() for x-coordinate
      int itemY = item.getY(); // Assuming the item has getY() for y-coordinate

      // Check if the item is within the target area with a deflection of 20
      return (itemX >= TARGET_X - DEFLECTION && itemX <= TARGET_X + DEFLECTION) &&
            (itemY >= TARGET_Y - DEFLECTION && itemY <= TARGET_Y + DEFLECTION);
   }
}
