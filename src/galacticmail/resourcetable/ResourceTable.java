package galacticmail.resourcetable;

import galacticmail.GameWorld;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class ResourceTable {

    private static HashMap<String, BufferedImage> imageTable;
    private static HashMap<String, BufferedReader> mapTable;

    private ResourceTable(){}

    public static void init() {
        try {
            imageTable = new HashMap<>();
            mapTable = new HashMap<>();

            ResourceTable.imageTable.put("asteroid", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Asteroid.png"))) );
            ResourceTable.imageTable.put("ship", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("galactic.png"))) );
            ResourceTable.imageTable.put("landed", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Landed.png"))) );
            ResourceTable.imageTable.put("background", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("background.jpg"))) );
            ResourceTable.imageTable.put("base0", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Base1.png"))) );
            ResourceTable.imageTable.put("base1", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Base2.png"))) );
            ResourceTable.imageTable.put("base2", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Base3.png"))) );
            ResourceTable.imageTable.put("base3", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Base4.png"))) );
            ResourceTable.imageTable.put("base4", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Base5.png"))) );
            ResourceTable.imageTable.put("base5", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Base6.png"))) );
            ResourceTable.imageTable.put("base6", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Base7.png"))) );
            ResourceTable.imageTable.put("base7", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Base8.png"))) );
            ResourceTable.imageTable.put("planet0", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Planet1.png"))) );
            ResourceTable.imageTable.put("planet1", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Planet2.png"))) );
            ResourceTable.imageTable.put("planet2", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Planet3.png"))) );
            ResourceTable.imageTable.put("planet3", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Planet4.png"))) );
            ResourceTable.imageTable.put("planet4", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Planet5.png"))) );
            ResourceTable.imageTable.put("planet5", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Planet6.png"))) );
            ResourceTable.imageTable.put("planet6", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Planet7.png"))) );
            ResourceTable.imageTable.put("planet7", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Planet8.png"))) );
            ResourceTable.imageTable.put("planetoid0", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Planetoid1.png"))) );
            ResourceTable.imageTable.put("planetoid1", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Planetoid2.png"))) );
            ResourceTable.imageTable.put("planetoid2", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Planetoid3.png"))) );
            ResourceTable.imageTable.put("planetoid3", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Planetoid4.png"))) );
            ResourceTable.imageTable.put("planetoid4", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Planetoid5.png"))) );
            ResourceTable.imageTable.put("planetoid5", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Planetoid6.png"))) );
            ResourceTable.imageTable.put("planetoid6", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Planetoid7.png"))) );
            ResourceTable.imageTable.put("planetoid7", read(Objects.requireNonNull(GameWorld.class.getClassLoader().getResource("Planetoid8.png"))) );

            ResourceTable.mapTable.put( "map0", new BufferedReader( new InputStreamReader(Objects.requireNonNull(GameWorld.class.getClassLoader().getResourceAsStream("map0.txt"))) ) );
            ResourceTable.mapTable.put( "map1", new BufferedReader( new InputStreamReader(Objects.requireNonNull(GameWorld.class.getClassLoader().getResourceAsStream("map1.txt"))) ) );
            ResourceTable.mapTable.put( "map2", new BufferedReader( new InputStreamReader(Objects.requireNonNull(GameWorld.class.getClassLoader().getResourceAsStream("map2.txt"))) ) );
        } catch(IOException e) {
            e.printStackTrace();
            System.exit(-5);
        }
    }

    public static BufferedImage getImage(String key) {
        //System.out.println(imageTable.get(key));
        return imageTable.get(key);
    }

    public static BufferedReader getMap(String key) {
        return mapTable.get(key);
    }
}
