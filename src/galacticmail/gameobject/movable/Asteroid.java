package galacticmail.gameobject.movable;

import galacticmail.resourcetable.Resource;

import java.awt.image.BufferedImage;

public class Asteroid extends Movable {
    public Asteroid(int x, int y, int vx, int vy, int angle, BufferedImage image) {
        super(x, y, vx, vy, angle, image);
    }

    @Override
    public void update() {

    }
}
