package object;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_donerw extends SuperObject{

    public OBJ_donerw() {
        name = "donerw";

        try{
            //image = ImageIO.read
            image = ImageIO.read(new File(GamePanel.URL+"donerw.png"));

        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}