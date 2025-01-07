package object;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_cutTomato extends SuperObject {

    public OBJ_cutTomato() {
        name = "cutTomato";

        try {
            // image = ImageIO.read
            image = ImageIO.read(new File(GamePanel.URL + "cutTomato.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}