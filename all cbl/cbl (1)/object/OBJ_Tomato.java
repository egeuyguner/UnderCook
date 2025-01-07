package object;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Tomato extends SuperObject {

    public OBJ_Tomato() {
        name = "Tomato";

        try {
            // image = ImageIO.read
            image = ImageIO.read(new File(GamePanel.URL + "tomato.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getImagePath() {
        return "assets/images/tomato.png"; // Adjust path to your file structure
    }
}