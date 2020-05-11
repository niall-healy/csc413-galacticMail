package galacticmail.gameobject.immovable;

import galacticmail.GameWorld;
import galacticmail.resourcetable.ResourceTable;


import java.awt.*;

public class Explosion extends Immovable {

    private int frameCount;
    private int explosionID;

    public Explosion(int x, int y, int angle, int explosionID) {
        super(x, y, angle, null);

        this.explosionID = explosionID;
        this.frameCount = 0;
    }

    @Override
    public void drawImage(Graphics g) {
        if(frameCount < 35) {
            this.setImage(ResourceTable.getImage("explosion" + frameCount/5));
            frameCount++;
            super.drawImage(g);
        }
        else {
            this.selfDestruct();
        }
    }

    public void selfDestruct() {
        GameWorld.gameObjectArrayListRemove(explosionID);
    }

    public void setExplosionID(int explosionID) {
        this.explosionID = explosionID;
    }
}
