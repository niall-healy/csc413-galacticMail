package galacticmail.resourcetable;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class Sprite extends Resource {
    private BufferedImage image;

    public Sprite(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }
}
