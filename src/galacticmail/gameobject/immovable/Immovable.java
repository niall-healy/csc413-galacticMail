package galacticmail.gameobject.immovable;

import galacticmail.gameobject.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Immovable extends GameObject {

    public Immovable(int x, int y, int angle, BufferedImage image) {
        super(x, y, angle, image);
    }

    public void update() { }

    @Override
    public void drawImage(Graphics g) {
        super.drawImage(g);
    }

}
