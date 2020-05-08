package galacticmail.resourcetable;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class SpriteList extends Resource {
    private ArrayList<BufferedImage> imageList;

    public SpriteList(URL gif) {
        imageList = this.getFrames(gif);
    }

    public BufferedImage getImage(int index) {
        return imageList.get(index);
    }

    public int getImageListSize() {
        return imageList.size();
    }

    public ArrayList<BufferedImage> getFrames(URL gif)  {
        ArrayList<BufferedImage> imageList = new ArrayList<>();

        try {
            ImageReader reader = ImageIO.getImageReadersByFormatName("gif").next();
            File input = new File(String.valueOf(gif));
            ImageInputStream stream = ImageIO.createImageInputStream(input);
            reader.setInput(stream);

            int count = reader.getNumImages(true);
            for (int i = 0; i < count; i++) {
                BufferedImage frame = reader.read(i);
                imageList.add(frame);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        return imageList;
    }
}
