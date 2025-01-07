package object;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_cutLettuce extends SuperObject {

    public OBJ_cutLettuce() {
        name = "cutLettuce";

        try {
            // image = ImageIO.read
            image = ImageIO.read(new File(GamePanel.URL + "cutLettuce.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
