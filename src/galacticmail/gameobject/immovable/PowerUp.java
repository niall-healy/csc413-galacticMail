package galacticmail.gameobject.immovable;

import galacticmail.resourcetable.Resource;

import java.awt.image.BufferedImage;

public abstract class PowerUp extends Immovable {
    public PowerUp(int x, int y, int angle, BufferedImage image) {
        super(x, y, angle, image);
    }
}
