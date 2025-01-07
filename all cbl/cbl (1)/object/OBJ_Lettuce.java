package object;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Lettuce extends SuperObject {

    public OBJ_Lettuce() {
        name = "Lettuce";

        try {
            // image = ImageIO.read
            image = ImageIO.read(new File(GamePanel.URL + "marul.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
