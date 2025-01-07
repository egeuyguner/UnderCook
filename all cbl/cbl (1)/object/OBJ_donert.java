package object;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_donert extends SuperObject{

    public OBJ_donert() {
        name = "donert";

        try{
            //image = ImageIO.read
            image = ImageIO.read(new File(GamePanel.URL+"donert.png"));

        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}