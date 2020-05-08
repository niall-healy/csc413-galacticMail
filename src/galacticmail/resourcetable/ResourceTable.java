package galacticmail.resourcetable;

import galacticmail.GameWorld;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class ResourceTable {

    private static HashMap<String, BufferedImage> imageTable;

    private ResourceTable(){}

    public static void init() {
        try {
            imageTable = new HashMap<>();

            ResourceTable.imageTable.put("ship", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("galactic.png"))) );
            ResourceTable.imageTable.put("background", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("background.jpg"))) );
            ResourceTable.imageTable.put("base1", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Base1.jpg"))) );
            ResourceTable.imageTable.put("base2", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Base2.jpg"))) );
            ResourceTable.imageTable.put("base3", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Base3.jpg"))) );
            ResourceTable.imageTable.put("base4", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Base4.jpg"))) );
            ResourceTable.imageTable.put("base5", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Base5.jpg"))) );
            ResourceTable.imageTable.put("base6", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Base6.jpg"))) );
            ResourceTable.imageTable.put("base7", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Base7.jpg"))) );
            ResourceTable.imageTable.put("base8", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Base8.jpg"))) );
            ResourceTable.imageTable.put("planet1", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Planet1.jpg"))) );
            ResourceTable.imageTable.put("planet2", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Planet2.jpg"))) );
            ResourceTable.imageTable.put("planet3", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Planet3.jpg"))) );
            ResourceTable.imageTable.put("planet4", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Planet4.jpg"))) );
            ResourceTable.imageTable.put("planet5", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Planet5.jpg"))) );
            ResourceTable.imageTable.put("planet6", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Planet6.jpg"))) );
            ResourceTable.imageTable.put("planet7", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Planet7.jpg"))) );
            ResourceTable.imageTable.put("planet8", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Planet8.jpg"))) );
            ResourceTable.imageTable.put("planetoid1", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Planetoid1.jpg"))) );
            ResourceTable.imageTable.put("planetoid2", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Planetoid2.jpg"))) );
            ResourceTable.imageTable.put("planetoid3", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Planetoid3.jpg"))) );
            ResourceTable.imageTable.put("planetoid4", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Planetoid4.jpg"))) );
            ResourceTable.imageTable.put("planetoid5", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Planetoid5.jpg"))) );
            ResourceTable.imageTable.put("planetoid6", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Planetoid6.jpg"))) );
            ResourceTable.imageTable.put("planetoid7", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Planetoid7.jpg"))) );
            ResourceTable.imageTable.put("planetoid8", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Planetoid8.jpg"))) );
        } catch(IOException e) {
            e.printStackTrace();
            System.exit(-5);
        }
    }

    public static BufferedImage getImage(String key) {
        //System.out.println(imageTable.get(key));
        return imageTable.get(key);
    }
}
