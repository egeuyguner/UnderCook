package object;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Cola extends SuperObject {

    public OBJ_Cola() {
        name = "Cola";

        try {
            // image = ImageIO.read
            image = ImageIO.read(new File(GamePanel.URL + "kola.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}