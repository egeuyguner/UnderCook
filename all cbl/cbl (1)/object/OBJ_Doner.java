package object;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Doner extends SuperObject {

    public OBJ_Doner() {
        name = "Doner";

        try {
            // image = ImageIO.read
            image = ImageIO.read(new File(GamePanel.URL + "doner.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}