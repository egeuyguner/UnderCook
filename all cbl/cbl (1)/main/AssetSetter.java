package main;

import object.OBJ_Cola;
import object.OBJ_Doner;
import object.OBJ_Key;
import object.OBJ_Lettuce;
import object.OBJ_Tomato;
import object.OBJ_Wrap;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        // Creates new object
        // gp.obj[0] = new OBJ_Key();

        // Sets the coordinates of the object created
        // gp.obj[0].worldX = 0;
        // gp.obj[0].worldY = 2*gp.tileSize;

        // This class would be used for displaying objects in the map but currently all
        // stations and parts of the kitchen map are tiles.

    }
}
