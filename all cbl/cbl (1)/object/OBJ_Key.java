package object;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Key extends SuperObject {

    public OBJ_Key() {
        name = "Key";

        try {
            // image = ImageIO.read
            image = ImageIO.read(new File(GamePanel.URL + "station.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
