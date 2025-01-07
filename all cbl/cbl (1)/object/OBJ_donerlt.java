package object;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_donerlt extends SuperObject{

    public OBJ_donerlt() {
        name = "donerlt";

        try{
            //image = ImageIO.read
            image = ImageIO.read(new File(GamePanel.URL+"donerlt.png"));

        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
