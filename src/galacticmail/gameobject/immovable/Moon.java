package galacticmail.gameobject.immovable;

import galacticmail.GameWorld;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Moon extends Immovable {
    private boolean hasDestructed;
    private int moonID;

    public Moon(int x, int y, int angle, int moonID, BufferedImage image) {
        super(x, y, angle, image);

        this.moonID = moonID;
        this.setHitBox(new Rectangle(this.getX() + this.getImage().getWidth()/3,
                this.getY() + this.getImage().getHeight()/3,
                this.getImage().getHeight()/3,
                this.getImage().getHeight()/3));
    }

    public void setMoonID(int moonID) {
        this.moonID = moonID;
    }

    public void selfDestruct() {
        if( !hasDestructed ) {
            GameWorld.gameObjectArrayListRemove(moonID);
        }

        hasDestructed = true;
    }
}
