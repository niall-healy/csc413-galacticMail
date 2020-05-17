package galacticmail;

import galacticmail.gameobject.immovable.Moon;
import galacticmail.gameobject.movable.Asteroid;
import galacticmail.gameobject.movable.Ship;
import galacticmail.resourcetable.ResourceTable;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

public class MapLoader {
    private int levelNum;
    private int moonCount;
    private Ship player;
    private final String[] moonNames = {"planet", "base", "planetoid"};

    public MapLoader(Ship player) {
        levelNum = 0;
        moonCount = 0;
        this.player = player;
    }

    public void update() {
        if(moonCount == 0) {
            try {
                this.loadNextLevel();
            } catch(IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    public void loadNextLevel() throws IOException {
        if(levelNum == 3) {
            GameWorld.setGameOver(true);
        } else {
            //System.out.println(levelNum);
            BufferedReader bufferedReader = ResourceTable.getTxt("map" + levelNum);
            String line;
            int lineCounter = 0;
            int unit = 32;
            Random random = new Random();

            moonCount = 0;

            while ( (line = bufferedReader.readLine()) != null ) {
                for (int i = 0; i < line.length(); i++) {
                    if(line.charAt(i) == '1') {
                        BufferedImage moonImage = ResourceTable.getImage(moonNames[levelNum] + moonCount);
                        GameWorld.gameObjectArrayListAdd( new Moon(i * unit, lineCounter * unit, 0, GameWorld.getObjectListSize(), moonImage) );
                        if(moonCount == 0 && levelNum == 0) {
                            player.setX(i * unit + (moonImage.getWidth()/2 - player.getImage().getWidth()/2) );
                            player.setY(lineCounter * unit + (moonImage.getHeight()/2 - player.getImage().getHeight()/2));
                        }
                        moonCount++;
                    }
                    else if(line.charAt(i) == '2') {
                        GameWorld.gameObjectArrayListAdd( new Asteroid(i * unit, lineCounter * unit, random.nextInt(360), random.nextBoolean(), GameWorld.getObjectListSize(), ResourceTable.getImage("asteroid")) );
                    }
                }
                lineCounter++;
            }
            levelNum++;
        }
    }

    public void decrementMoonCount() {
        this.moonCount--;
    }

    public int getLevelNum() {
        return levelNum;
    }
}
